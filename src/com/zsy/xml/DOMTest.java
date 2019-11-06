package com.zsy.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * DOM解析整个文件导入内存
 *
 */
public class DOMTest {
    public static void main(String[] args) {
        try {
            String dom = Dom(new File("D:/abc/test2.xml"),
                    "MDC_ECG_LEAD_III");
            System.out.println("dom : " + dom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String Dom(File file, String tag) throws Exception {
        InputStream in = new FileInputStream(file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(in);
        NodeList nodeList = document.getElementsByTagName("AnnotatedECG");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String digits = node(node, tag);
            if (digits != null) {
                return digits;
            }
        }
        return null;
    }


    private static String node(Node node, String tag) {
        String nodeName = node.getNodeName();
        System.out.println("-- " + nodeName);
        if (nodeName.equals("code")) {
            Node code = node.getAttributes().getNamedItem("code");
            if (code.getTextContent().equals(tag)) {
                Node parentNode = node.getParentNode();
                return digit(parentNode);
            }
        }
        NodeList childNodes = node.getChildNodes();
        String result = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String s = node(item, tag);
            if (s != null) {
                result = s;
                break;
            }
        }
        return result;
    }


    private static String digit(Node node) {
        String result = null;
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            if (item.getNodeName().equals("value")) {
                NodeList childNodes = item.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node digit = childNodes.item(j);
                    if (digit.getNodeName().equals("digits")) {
                        result = digit.getTextContent();
                    }
                }
                break;
            }
        }
        return result;
    }

}
