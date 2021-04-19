package com.zsy.function.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.*;

/**
 * DOM解析整个文件导入内存
 *
 */
public class DOMTest {

    public static String resultD = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
            "<string xmlns=\"http://tempuri.org/\">" +
            "{" +
            "\"?xml\":{\"@version\":\"1.0\",\"@encoding\":\"gb2312\"}," +
            "\"WorkList\":" +
            "{" +
            "\"@xmlns:xsi\":\"http://www.w3.org/2001/XMLSchema-instance\"," +
            "\"@xmlns:xsd\":\"http://www.w3.org/2001/XMLSchema\"," +
            "\"ErrorCode\":\"0\",\"Error\":null," +
            "\"Item\":" +
            "{" +
            "\"PatientName\":\"测试\",\"PatientID\":\"ECG20201019155652377\"," +
            "\"PatientBirthDate\":\"20131019\",\"PatientSex\":\"男\"," +
            "\"PatientHeight\":null,\"PatientWeight\":null," +
            "\"StudyInstanceUID\":\"20101915565237755A42F097A2D3435D\"," +
            "\"AccessionNumber\":\"SW202027621\"," +
            "\"AdmissionID\":\"ECG20201019155652377\"," +
            "\"RequestedProcedureID\":null," +
            "\"SPSStartDateTime\":\"20201019155525\"," +
            "\"SPSLocation\":\"蓬阳心电\"," +
            "\"DeviceID\":\"10000\"" +
            "}" +
            "}" +
            "}" +
            "</string>";

    public static void main(String[] args) {
        /*try {
            String dom = Dom(new File("D:/abc/test2.xml"),
                    "MDC_ECG_LEAD_III");
            System.out.println("dom : " + dom);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(resultD.getBytes());
            Document document = builder.parse(is);
            Element documentElement = document.getDocumentElement();

            String textContent = documentElement.getTextContent();

           /* String tagName = documentElement.getTagName();
            System.out.println(tagName);

            String attribute = documentElement.getAttribute("string");
            System.out.println(attribute);

            NodeList nodeList = documentElement.getElementsByTagName("string");


            String textContent = documentElement.getTextContent();

            Node item = nodeList.item(0);

            Element element = document.getElementById("string");*/
//            document.
//            String tagName = element.getTagName();
            System.out.println("1");
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
