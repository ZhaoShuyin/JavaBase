package com.zsy.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Title com.zsy.json
 * @date 2021/7/21
 * @autor Zsy
 */

public class GsonTest {

    public static String json = "{" +
            "\"code\":\"1\"," +
            "\"data\":{" +
            "\"HEARTRATE\":80," +
            "\"PDURATION\":54," +
            "\"QRSDURATION\":102," +
            "\"TDURATION\":176," +
            "\"RRINTERAL\":745," +
            "\"PRINTERAL\":128," +
            "\"QTINTERAL\":398," +
            "\"QTCINTERAL\":460," +
            "\"PAXIS\":11," +
            "\"QRSAXIS\":-98," +
            "\"TAXIS\":-75," +
            "\"RV5\":1625," +
            "\"SV1\":-375," +
            "\"RV5SV1\":1250," +
            "\"RHYTHM\":\"12\"," +
            "\"BEATS_POSITION\":\"[" +
            "{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0}," +
            "{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0}," +
            "{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0}," +
            "{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0},{\\\"Position\\\":0,\\\"HeartBeat\\\":0}" +
            "]\"," +
            "\"AUTOMATIC_DIAGNOSIS\":\"房室交界性心律\\r\\n异常心电图\\r\\n交界性心动过速\\r\\n电轴右偏\\r\\n异常Q波：V3  V4  V5  \\n冠状T波：II  III  avF  V4  V5  " +
            "\\n\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\"}}";

    public static void main(String[] args) {

        json = json.replace("\\u0000","");

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new SpecificClassExclusionStrategy())
                .create();

        /*gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()//启用	@Expose
                .create();*/

        ExclusionStrategy excludeStrings = new SpecificClassExclusionStrategy();
       /* gson = new GsonBuilder()
                .setLenient()// json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .serializeNulls() //智能null
                .setPrettyPrinting()// 调整格式 ，使对齐
                .disableHtmlEscaping() //默认是GSON把HTML 转义的
                //.setExclusionStrategies(excludeStrings) //自定义排除转json的字段或者类名
                .excludeFieldsWithoutExposeAnnotation()//启用	@Expose
                .create();*/

        ParamBean bean = gson.fromJson(json, ParamBean.class);

        System.out.println(bean.data.BEATS_POSITION);

        System.out.println(gson.toJson(bean.data));
    }

}
