package test.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title com.zsy.test
 * @date 2020/3/17
 * @autor Zsy
 */
public class Test20 {

    public static String string="管理员adminpengyangmecg管理员=" +
            "赵广贺zgh123456开发=" +
            "周一彬zyb123456开发=" +
            "张全zq123456开发=" +
            "张光旭zgx123456开发=" +
            "赵树印zsy123456开发=" +
            "陈晓雪cxx123456开发=" +
            "张永红zyh123456开发=" +
            "方丹丹fdd123456测试=" +
            "王小宇wxy123456产品=" +
            "白杰bj123456产品=" +
            "赵亮zl123456工程师=" +
            "张保龙zbl123456工程师=" +
            "夭志国yzg123456工程师=" +
            "徐军华xjh123456工程师=" +
            "马宇剑myj123456工程师=" +
            "许金阳xjy123456工程师=" +
            "卢思恒lsh123456工程师=" +
            "刘淼lm123456工程师=" +
            "魏柯文wkw123456工程师=" +
            "王姝ws123456工程师=" +
            "何泰鹏htp123456工程师=" +
            "李晨光lcg123456工程师=" +
            "黄聿强hyq123456工程师=" +
            "刘炯lj123456工程师=" +
            "张薛伟zxw123456工程师=" +
            "李永ly123456销售=" +
            "王刚wg123456销售=" +
            "张富强zfq123456销售=" +
            "黄飞hf123456销售=" +
            "谭道书tds123456销售=" +
            "方玉镯fyz123456销售=" +
            "苑鲜yx123456销售=" +
            "黄义红hyh123456销售=" +
            "麦海鸿mhh123456销售=" +
            "项鹰xy123456销售=" +
            "安伟aw123456销售=" +
            "逯通阔ltk123456销售=" +
            "周扬zy123456销售=" +
            "周延鑫zyx123456销售=" +
            "何立明hlm123456销售";

    public static void main(String[] args) {
//        Patch();
//        IO();
        Pattern pattern = Pattern.compile("([\\u4e00-\\u9fa5]{1,3})([a-z]{1,3})([0-9]{6})([\\u4e00-\\u9fa5]{1,6})");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String s = matcher.group(1) +"  "+ matcher.group(2) +"  "+ matcher.group(3)+" "+matcher.group(4);
            System.out.println(s);
        }
        /*Pattern pattern = Pattern.compile("([\\u4e00-\\u9fa5]{1,3})([a-z]{1,3})([0-9]{1})");
        Matcher matcher = pattern.matcher("郭靖gj1 张三丰zsf5");
        while (matcher.find()) {
            String s = matcher.group(1) +"  "+ matcher.group(2) +"  "+ matcher.group(3);
            System.out.println(s);
        }*/
    }

    private static void Patch() {
        Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
        String s = "123aa-34345bb-234cc-00";
        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println("m.group():" + m.group());       // 打印所有
            System.out.println("m.group(1):" + m.group(1));     // 打印数字的
            System.out.println("m.group(2):" + m.group(2));     // 打印字母的

            System.out.println("捕获个数:groupCount()=" + m.groupCount());
            System.out.println();
        }
    }

    private static void IO() {
        try {
            FileReader fileReader = new FileReader("D:/123/test.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = "";
            while (s != null) {
                s = bufferedReader.readLine();
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
