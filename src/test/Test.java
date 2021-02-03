package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title test
 * @date 2020/6/6
 * @autor Zsy
 */
public class Test {

    private static String TAG = "million";

//    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());
////        linkedQueue = new ConcurrentLinkedQueue<>();
////        for (int i = 0; i < 100; i++) {
////            for (int j = 0; j < 18; j++) {
////                linkedQueue.add((short) (i + 1));
////            }
////        }
////        calpoint(5);       // 5 : 40 8取2 计算出 10个点
////        calpoint(10);// 10 : 40 4取2 计算出 20个点
//
//
////        calpoint(4);  //32 (8取2) 计算出4个点
////        calpoint(8);  //32 (8取2) 计算出8个点
////        calpoint(16);  //32 (8取2) 计算出8个点
//    }

    public static ConcurrentLinkedQueue<Short> linkedQueue;
    static int[][] datasReal = new int[18][40];            //波形数据[哪个导联][一次刷新数据个数]
    static int[][] datasRefresh = new int[18][16];         //波形数据[哪个导联][一次刷新数据个数]

    //40取20
    private static void calpoint(int target) {
        if (linkedQueue.size() < 32 * 18) {
            return;
        }
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                datasReal[j][i] = (linkedQueue.isEmpty()) ? 0 : (int) ((Short) linkedQueue.poll());
            }
        }
        System.out.println(Arrays.toString(datasReal[0]));
        int max = -32767;
        int min = 32766;
        int temp = 0;
        int ecg_max_idx = 0;
        int ecg_min_idx = 0;
        int refresh = target / 2;
        int scale = 32 / refresh;       //8
        System.out.println("比例scale: " + scale);
        int count = 0;
        for (int i = 0; i < refresh; i++) {
            for (int j = 0; j < 18; j++) {
                int position = (int) (i * scale);//获取索引值
                for (int k = 0; k < scale; k++) {
                    temp = datasReal[j][position + k];
                    if (temp >= max) {
                        max = temp;
                        ecg_max_idx = k;
                    }
                    if (temp <= min) {
                        min = temp;
                        ecg_min_idx = k;
                    }
                }
                if (ecg_max_idx < ecg_min_idx) { //降序
                    datasRefresh[j][2 * i] = max;
                    datasRefresh[j][2 * i + 1] = min;
                } else {                         //升序
                    datasRefresh[j][2 * i] = min;
                    datasRefresh[j][2 * i + 1] = max;
                }
                if (j == 0) {
                    count += 2;
                }
                max = -32767;
                min = 32766;
            }
        }
        System.out.println("计算出" + count + "个点");
        drawWave(refresh);                                       //绘制波形
    }


    /**
     * 根据 datasRefresh 数据绘制波形
     *
     * @param refresh
     */
    private static void drawWave(int refresh) {


    }

    //https://www.cnblogs.com/ou-pc/p/7668079.html
    public static void main(String[] args) {
      /*  Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String abc = properties.getProperty("abc");
        System.out.println(abc);
        properties.setProperty("pwd", "hahaha");
        try {
            properties.store(new FileWriter("src/config.properties"),"db配置");
        } catch (IOException e) {
            e.printStackTrace();
        };*/
        /*String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";

//可以在中括号内加上任何想要替换的字符，实际上是一个正则表达式

        String aa = " ";//这里是将特殊字符换为aa字符串," "代表直接去掉

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher("?!哈哈");//这里把想要替换的字符串传进来

        String newString = m.replaceAll(aa).trim();
        System.out.println(newString);*/
        String regEx1 = "[\\u4e00-\\u9fa5]";
        String regEx2 = "[a-z||A-Z]";
        String regEx3 = "[0-9]";

        String reg = "[\\u4e00-\\u9fa5a-zA-Z0-9]";
        String str = "1? 冫?>;!{\2fdAsz我是hhhZ大123";
        String s1 = matchResult(Pattern.compile(reg), str);
        System.out.println(s1);
    }

    public static String matchResult(Pattern p, String str) {
        StringBuilder sb = new StringBuilder();
        Matcher m = p.matcher(str);
        while (m.find())
            for (int i = 0; i <= m.groupCount(); i++) {
                sb.append(m.group());
            }
        return sb.toString();
    }

}
