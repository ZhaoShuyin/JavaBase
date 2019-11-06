package com.zsy.xml.xstream;

import com.thoughtworks.xstream.XStream;

/**
 * @Title com.zsy.xml.xstream
 * @Date 2019/11/4
 * @Autor Zsy
 */
public class Test {


    public static  String xmlStr = "<china dn=\"day\">\n" +
            "    <city prov= \"西安\">\n" +
            "        <name>陕西</name>\n" +
            "        <age>18</age>\n" +
            "        <year>2016</year>\n" +
            "    </city>\n" +
            "    <city prov= \"郑州\">\n" +
            "        <name>河南</name>\n" +
            "        <age>20</age>\n" +
            "        <year>2016</year>\n" +
            "    </city>\n" +
            "    <city prov= \"石家庄\">\n" +
            "        <name>河北</name>\n" +
            "        <age>22</age>\n" +
            "        <year>2016</year>\n" +
            "    </city>\n" +
            "    <city prov= \"三亚\">\n" +
            "        <name>海南</name>\n" +
            "        <age>88</age>\n" +
            "        <year>2016</year>\n" +
            "    </city>\n" +
            "</china>";

    public static void main(String[] args) {

        XStream stream = new XStream();

        XStream.setupDefaultSecurity(stream);
        stream.allowTypes(new Class[]{ChinaBean.class});
        stream.processAnnotations(ChinaBean.class);//这里需要注解是你自己根据xml写的bean类(下面附代码解释xml)
        ChinaBean result = (ChinaBean) stream.fromXML(xmlStr);

        for (int i = 0; i < result.city.size(); i++) {
            ChinaBean.CityBean cityBean = result.city.get(i);
            System.out.println("cityBean: { "+cityBean.name+", "+cityBean.prov+" }");
        }
        System.out.println();

    }

}
