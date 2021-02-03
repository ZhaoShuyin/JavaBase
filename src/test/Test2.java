package test;

import java.util.Calendar;

/**
 * @Title test
 * @date 2020/9/6
 * @autor Zsy
 */
public class Test2 {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int w = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(w);
    }
}
