package com.zsy.xml;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @Title SAX接口也被称作事件驱动接口
 * @Date 2019/11/1
 * @Autor Zsy
 */
public class SAXTest {


    public static void main(String[] args) {
        try {
            SAX(new File("D:/abc/test2.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SAX(File file) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(file, new MyHandler());
    }


    public static class MyHandler extends DefaultHandler {

        //首先设定解析器
        @Override
        public void setDocumentLocator(Locator locator) {
            super.setDocumentLocator(locator);
        }

        //开始解析文档
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        //结束解析文档
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            System.out.println("=========开始 startElement() uri:"+uri
                    +", localName:"+localName+" qName:"+qName+" attributes:"+attributes.getLength());
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            System.out.println("========文本 characters() ch:"+String.valueOf(ch).length()+", start:"+start +", length:"+length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            System.out.println("========结束 endElement() uri:"+uri+", localName:"+localName+" qName:"+qName);
        }

        /////////////////////////////////
        @Override
        public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
            System.out.println("resolveEntity() 解析实例");
            return super.resolveEntity(publicId, systemId);
        }

        @Override
        public void notationDecl(String name, String publicId, String systemId) throws SAXException {
            super.notationDecl(name, publicId, systemId);
            System.out.println("notationDecl() name:"+name+", publicId:"+publicId+", systemId:"+systemId);
        }

        @Override
        public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
            super.unparsedEntityDecl(name, publicId, systemId, notationName);
            System.out.println("unparsedEntityDecl() name:"+name+", publicId:"+publicId+", systemId:"+systemId+" notationName:"+notationName);
        }
        /////////////////////////////////


        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            super.startPrefixMapping(prefix, uri);
            System.out.println("startPrefixMapping() prefix:"+prefix+" , uri:"+uri);
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            super.endPrefixMapping(prefix);
            System.out.println("endPrefixMapping() prefix:"+prefix);
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
            super.ignorableWhitespace(ch, start, length);
            System.out.println("ignorableWhitespace() 忽略空格 ch:"+String.valueOf(ch)+", start:"+start +", length:"+length);
        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {
            super.processingInstruction(target, data);
            System.out.println("processingInstruction target:"+target+", data:"+data);
        }

        @Override
        public void skippedEntity(String name) throws SAXException {
            super.skippedEntity(name);
            System.out.println("skippedEntity()  name:"+name);
        }

    }




    /*//遇到元素节点开始时候的处理方法
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            tagName = localName;
            //如果遇到<person>标记，则创建一个person
            if ("person".equals(tagName)) {
                person = new Person();
                person.setId(new Integer(attributes.getValue(0)));//取出标记内的属性
            }
        }

        //遇到文本节点时的操作
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            if (tagName != null) {//文本节点必须前面要有元素节点开始标记
                String data = new String(ch, start, length);//取出文本节点的值
                if ("name".equals(tagName)) {//如果前面的元素节点开始标记是name
                    person.setName(data);//则将文本节点的值赋值给person的Name
                } else if ("age".equals(tagName)) {//如果前面元素节点开始标记是age
                    person.setAge(new Short(data));//则将本节点的值赋值给person的Age
                }
            }
        }

        //遇到元素节点结束时候的操作
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if ("person".equals(localName)) {//如果遇到</person>标记
                persons.add(person);//则将创建完成的person加入到集合中去
                person = null;//置空下一个person
            }
            tagName = null;//置空已有标记，因为要解析下一个节点了
        }*/

  /*  public List<Person> getPersons(InputStream instream) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();//创建SAX解析工厂
        SAXParser paser = factory.newSAXParser();//创建SAX解析器
        PersonPaser personPaser = new PersonPaser();//创建事件处理程序
        paser.parse(instream, personPaser);//开始解析
        instream.close();//关闭输入流
        return personPaser.getPersons();//返回解析后的内容

    }

    public final class PersonPaser extends DefaultHandler {//创建事件处理程序，也就是编写ContentHandler的实现类，一般继承自DefaultHandler类

        public List<Person> getPersons() {
            return persons;
        }

        private List<Person> persons = null;
        private String tagName = null;
        private Person person = null;

        {
            //遇到文档开始标记的时候创建person集合
            public void startDocument () throws
            SAXException persons = new ArrayList<Person>();
        }


    }*/


}
