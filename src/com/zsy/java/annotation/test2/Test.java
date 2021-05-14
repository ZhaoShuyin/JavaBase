package com.zsy.java.annotation.test2;

import java.util.Arrays;

/**
 * @Title com.zsy.java.annotation.test2
 * @date 2021/5/7
 * @autor Zsy
 */

class Test {

    public static void main(String[] args) {
        Test test = new Test();
        // com.zsy.java.annotation.test2.Test,
        // getname,
        // Test.java,
        // 23,
        // false,
        // com.zsy.java.annotation.test2.Test.getname(Test.java:23)
        System.out.println(Arrays.toString(test.getname()));
    }

    //第一级 getStackTrace
    //第二级 getnam
    //第三级 main
    private String[] getname() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        boolean nativeMethod = stackTraceElement.isNativeMethod();
        String s = stackTraceElement.toString();
        return new String[]{className,methodName,fileName,String.valueOf(lineNumber),String.valueOf(nativeMethod),s};
    }
}
