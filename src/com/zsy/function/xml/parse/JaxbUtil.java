package com.zsy.function.xml.parse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbUtil {

    public static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<persons>\n" +
            "    <person >\t\n" +
            "        <name>张三</name>\n" +
            "        <age>33</age>\t\t\n" +
            "    </person>\t\n" +
            "    <person>\n" +
            "        <name>李四</name>\n" +
            "        <age>44</age>\n" +
            "    </person>\n" +
            "</persons>";

    public static void main(String[] args) {
        Person person = converyToJavaBean(xml, Person.class);
        System.out.println(person);
    }

    /**
     * JavaBean装换成xml
     * 默认编码UTF-8
     *
     * @param obj
     * @return
     */
    public static String converTomXml(Object obj) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * xml装换成JavaBean
     *
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;

    }

}