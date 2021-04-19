package test.test;

import java.io.FileReader;

/**
 * @Title com.zsy.test
 * @date 2020/1/9
 * @autor Zsy
 */

class Test16 {

    public static void main(String[] args) {
        String filePath = "C:/Users/pengyang/Desktop/数据/SQYY19123100001/analysis.xml";
        System.out.println(getAnalysis(filePath));
    }


    /**
     * 解析XML获取分析结果
     */
    public static String getAnalysis(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            char[] chs = new char[16];
            int len = 0;                // 作用: 记录读取到的有效的字符个数
            StringBuilder builder = new StringBuilder();
            while ((len = fileReader.read(chs)) != -1) {
                String s = new String(chs, 0, len);
                builder.append(s);
            }
            fileReader.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
