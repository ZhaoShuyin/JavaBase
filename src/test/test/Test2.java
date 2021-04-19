package test.test;

import java.io.File;

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



        File f =new File("Test.txt");
        String fileName=f.getName();
        String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println(prefix);
    }


}
