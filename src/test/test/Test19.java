package test.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title com.zsy.test
 * @date 2020/3/26
 * @autor Zsy
 */

public class Test19 {
    private static SimpleDateFormat formatT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
       /* String s1 = "2020-03-30T16:45:09:123";
        String s2 = "2020-01-30 16:45:09";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat simpleDateFormat2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse1 = simpleDateFormat1.parse(s1);
            Date parse2 = simpleDateFormat2.parse(s2);
            System.out.println(parse2.compareTo(parse1));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
       /* File file = new File("D:/123/123/123.txt");
        System.out.println(file.length()+"");
        File file2 = new File("D:/123/123/789.txt");
        if (!file2.exists()){
            try {
                file2.createNewFile();
                System.out.println("创建新文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file2.length()+"");
        }
        try {
            Files.copy(file.toPath(),new FileOutputStream(file2));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println(getAge("2000-09-30T16:45:09:123"));
        System.out.println(getAge(0, 1, 1));
    }

    public static String getAge(String brithday) {
        try {
            Date parse = formatT.parse(brithday);
            long l = new Date().getTime() - parse.getTime();
            return String.valueOf(l / 365 / 24 / 3600 / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static int getAge(int year, int month, int day) {
        Date date = new Date();
        Date date1 = new Date(year, month, day);
        System.out.println(date.toString() + "\n" + date1.toString());
        long l = date.getTime() - date1.getTime();
        return (int) (l / 365 / 24 / 3600 / 1000);
    }

}
