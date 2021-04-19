package test.net;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.junit.Assert;
//import org.junit.Test;

public class GetRedirectUrlTest {

    public static String longurl = "https://u.jd.com/jda?e=&p=AyIGZRhaFwYRDlASXxYyEgZUGloQBhAOVxNSJUZNXwtEa0xHV0YXEEULWldTCQQHCllHGAdFBwtEQkQBBRxNVlQYBUkeTVxNCRNLGEF6RwtVGloUAxcDVxJZHQsiTgJPJE9eWQA2UA9ick5XA2lYUHxSd1kXaxQyEgZUGlsTAhoCUStrFQUiUTsbWhQDEwZWE1IdMhM3VR9TFQsSAFcdWhECETdSG1IlARMHUBxfEAIVG1MfXBMBGjdlK1glMiIHZRhrV2xBUwUcC0dVRQJcT1oQABMABU8IFVcXA1AeUhUHEAQGH2sXAxMDXA%3D%3D&a=fCg9UgoiAwwHO1BcXkQYFFlgf3t3eVdfQV0zVRBSUll%2bAQAPDSwjLw%3d%3d&refer=http%3a%2f%2fu.jd.com%2fmNKCNH&d=mNKCNH";

    public static String url = "http://www.baidu.com/link?url=ByBJLpHsj5nXx6DESXbmMjIrU5W4Eh0yg5wCQpe3kCQMlJK_RJBmdEYGm0DDTCoTDGaz7rH80gxjvtvoqJuYxK";
    public static String expectUrl = "http://www.zhihu.com/question/20583607/answer/16597802";


   /* public static void main(String[] args) {
        // 查找的字符串
        String line = "（乙方）:xxx科技股份有限公司     （乙方）:xxx有限公司     （乙方）:xxx技术股份有限公司     ";
        //正则表达式
        String pattern = "(（乙方）:)(.*?)( )"; //Java正则表达式以括号分组，第一个括号表示以"（乙方）:"开头，第三个括号表示以" "(空格)结尾，中间括号为目标值，
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 创建 matcher 对象
        Matcher m = r.matcher(line);
        while (m.find()) {
            *//*
             自动遍历打印所有结果   group方法打印捕获的组内容，以正则的括号角标从1开始计算，我们这里要第2个括号里的
             值， 所以取 m.group(2)， m.group(0)取整个表达式的值，如果越界取m.group(4),则抛出异常
           *//*
            System.out.println("Found value: " + m.group(2));
        }

          Pattern pattern = Pattern.compile("http(.*?)9");
        Matcher matcher = pattern.matcher("55http12349';");
        boolean b = matcher.find();
        System.out.println(b);
        String group = matcher.group(0);
        System.out.println(group);

       String patt = "^http(.*?)\\\\d$";
        Pattern matcher = Pattern.compile(patt);
        String line = "12423556http://www.baidu.com?eklwsfjealkadsjfk&111111";
        Matcher m = matcher.matcher(line);
        String group = m.group(0);
        System.out.println(group);
    }*/

    public static void main(String[] args) {

        String shorturl = "https://u.jd.com/mNKCNH";
        try {
            String redirectUrl = getRedirectUrl(shorturl);
            System.out.println("重定向网址: "+redirectUrl);
            String parsing = parsing(shorturl);
            System.out.println("获取重定向::: "+parsing);
        } catch (Exception e) {
            e.printStackTrace();
        }

     /*   //http(?:(?!http).)*(\\,|\\。|\\！|\\？|(\\……))999"
        Pattern pattern1 = Pattern.compile("http(?:(?!http).)*999");
        Matcher matcher1 = pattern1.matcher("2134http:123315http://www.baidu.com,999';");
        while (matcher1.find()){
            System.out.println(matcher1.group());
        }
        Matcher matcher2 = pattern1.matcher("2134http:123315http://www.baidu.com,999';");
        while (matcher2.find()){
            System.out.println(matcher2.group());
        }

        Matcher matcher3 = pattern1.matcher("111http：e}};CookieAds.initjda();var hrl='https://u.jd.com/jda?d=mNKCNH999';var ua='Java/;");
        while (matcher3.find()){
            System.out.println(matcher3.group());
        }

        Matcher matcher4 = pattern1.matcher("1241535http://www.baidu.com999';");
        while (matcher4.find()){
            System.out.println(matcher4.group());
        }*/



           /*     Pattern pattern = Pattern.compile("http(?:(?!http).)*(\\,|\\。|\\！|\\？|(\\……))mNKCNH");
        Matcher matcher1 = pattern.matcher(html);
        while (matcher1.find()){
            System.out.println(matcher1.group());
        }
        Matcher matcher2 = pattern.matcher("easdfgljhalkf,http:1232,http:www.baidu.com?a=mNKCNH';1257547");
        while (matcher2.find()){
            System.out.println(matcher2.group());
        }*/
      /*  String shorturl = "http://u.jd.com/mNKCNH";
        try {
            String redirectUrl = getRedirectUrl(shorturl);
            System.out.println("重定向网址: "+redirectUrl);
            String parsing = parsing(redirectUrl);
            System.out.println(parsing);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


//        String str="“小耳朵”说：“春天到了！”  妈妈笑着说：“是呀。”"; //自定义一个字符串
//        System.out.println("\n---- 提示：原字符串 ----\n\n"+str);
//        //Pattern p=Pattern.compile("\\“(.*?)(\\,|\\。|\\！|\\？|(\\……))”");//创建正则懒惰匹配，匹配每行出现的对话
//
//        //从“开始匹配，直到，”或。”或？”或！”或……”结束匹配，(?:(?!\\”).)*负责匹配是否有”，有则匹配无效
//        Pattern p=Pattern.compile("\\“(?:(?!\\”).)*(\\,|\\。|\\！|\\？|(\\……))”");
//        Matcher m=p.matcher(str);			//matches()用Pattern创建的匹配模式对整个字符串进行匹配
//        System.out.println("\n---- 提示：字符串中的对话内容 ----\r\n");
//        while(m.find()){					   //当匹配结果返回true（即匹配到对话）时,执行计数器
//            System.out.println(m.group());	   //打印匹配到的内容
//        }
    }

    private static String parsing(String shorturl) {
        String end = shorturl.substring(shorturl.lastIndexOf("/") + 1);
        System.out.println("end: " + end);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL url = new URL(shorturl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5 * 1000);
            urlConnection.setReadTimeout(5 * 1000);
            //设置请求方式，默认是get
            urlConnection.setRequestMethod("GET");//大写的POST
            int code = urlConnection.getResponseCode();
            System.out.println("code: " + code);
            if (code == 200 || code == 302) {
                InputStream inputStream = urlConnection.getInputStream();

                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    stringBuffer.append(new String(bytes, 0, len));
                }
                String html = stringBuffer.toString();
                System.out.println("html:\n" + html);
                Pattern pattern = Pattern.compile("http(?:(?!http).)*" + end);
                Matcher matcher = pattern.matcher(html);
                if (matcher.find()) {
                    return matcher.group(0);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void test_getRedirectUrl() throws Exception {
        String redictURL = getRedirectUrl(longurl);
        System.out.println(redictURL);
    }

    /**
     * 获取重定向地址
     */
    private static String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        return conn.getHeaderField("Location");
    }

}