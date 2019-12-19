package com.zsy.test;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @Title com.zsy.test
 * @Date 2019/11/18
 * @Autor Zsy
 */
public class Test2 {


    public static void main(String[] args) {
//        int[] ints = new int[]{1, 2, 3};
//        String sql = "create table " + "abc " + //" if not exists " +
////                "(_id integer primary key autoincrement," +
////                "account varchar(50)," +
////                "password varchar(20)," +
////                "type varchar(20)," +
////                "stamp integer" +
////                ");";
////        System.out.println(sql);

      /*  InputStream in = null;
        try {
            in = new FileInputStream(new File("D:/abc/BECG_advice.xml"));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(in);
            NodeList nodeList = document.getElementsByTagName("Result").item(0).getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("Advice")){
                    System.out.println(node.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*int[] a = new int[]{0, 1, 2, 3, 4, 5};
        String[] aa = new String[]{"未检查","待上传", "未报告", "已报告", "以审核", "已退回"};
        int[] b = new int[]{0, 1, 2};
        String[] bb = new String[]{"未申请", "未会诊", "已会诊"};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                System.out.println(aa[i] + " / " + bb[j] + " = " + (a[i] + b[j]));
            }
        }*/
        System.out.println("123"=="123");

        String a = "abc";
        String b = "abc";
        System.out.println(a==b);

    }


    static class A {
       String name = "张三";
       int age = 123;
    }

    /**
     * 创建病例参数
     */
    public static String paramCreat(String hospital, String doctor,
                                    String name, String sex, String age, String brithday,
                                    String type, String department,
                                    String outpaint, String inoapital, String ward, String bedin) {
        String param = "{\"Token\":null,\"Command\":\"E00051\",\"Param\":{\"Type\":\"ADD \"," +
                "\"hospital_Name\":\"" + hospital        // 医院名称
                + "\"},\"RequestModel\":" +
                "{\"NAME\":\"" + name + "\"," +               //姓名**********
                "\"NAME_PHONETIC\":\"\"," +                //英文名
                "\"SEX\":\"" + sex + "\"," +                   //性别***********
                "\"HEIGHT\":\"\"," +                       //身高
                "\"PATIENT_ID \":\"\"," +                  //病例号
                "\"EXAM_AGE\":\"" + age + "\"," +              //年龄*********
                "\"BIRTHDAY \":\"" + brithday + "\"," +        //生日*********
                "\"WEIGHT \":\"\"," +                      //体重
                "\"CROWD_TYPE \":\"\",\"CHARGE_TYPE \":\"\",\"NATIVE_PLACE\":\"\",\"ZIP_CODE \":\"\",\"INSURANCE_TYPE \":\"\",\"INSURANCE_CARD_NO \":\"\",\"CONTACT_TEL \":\"\",\"ID_NUMBER \":\"\",\"HOME_ADDRESS \":\"\",\"MAILING_ADDRESS \":\"\",\"EXAM_NO \":\"\",\"ACCESSIONNUMBER \":\"\"," +
                "\"PATIENT_SOURCE \":\"" + type + "\","        //患者来源
                + "\"EXAM_CLASS \":\"ECG \"," +            //检查类型 ECG
                "\"REQ_DEPT_CODE \":\"" + department + "\"," + //申请科室**********
                "\"OUTP_NO \":\"" + outpaint + "\"," +         //门诊号
                "\"ITEM_NAME \":\"\",\"EXAM_SUB_CLASS \":\"\"," +
                "\"REQ_DOCTOR_CODE \":\"" + doctor + "\"," +    //申请医生
                "\"INP_NO\":\"" + inoapital + "\"," +           //住院号
                "\"BED_NO\":\"" + bedin + "\"," +               //床号
                "\"ROOM_NO\":\"" + ward + "\"," +               //病区
                "\"EXAM_DEPT_NAME\":\"\",\"EXAM_ROOM\":\"\",\"CHARGES\":\"\",\"REQ_DATETIME\":\"\",\"CLIN_DIAG\":\"\",\"PHYS_SIGN\":\"\",\"CASE_HISTORY\":\"\",\"CREATE_USER_UID\":\"\",\"IMAGE_STATUS\":0,\"RIS_UPLOAD_STATUS\":0,\"PACS_UPLOAD_STATUS\":0,\"EXAM_STATUS\":\"0 \",\"PRINT_NUM\":\"0 \"}}";
        return param;
    }
}
