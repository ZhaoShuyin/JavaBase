package com.zsy.java.net;

import java.io.Serializable;
import java.util.List;

/**
 * @Title 病例 Bean
 * @date 2019/10/14
 * @autor Zsy
 */
public class CasesBean implements Serializable {

    public List<Case> caseList;  //病例集合

    public static class Case implements Serializable {
        public String hospital;   //医院
        public String name;       //名称
        public int age;           //性别
        public boolean genders;
        public String brithday;
        public boolean hasUpload; //是否已上传
        public String date;         //时间
        public String filePath;   //数据文件路径


        public Case(String hospital, String name, int age, boolean genders, String brithday, boolean hasUpload, String date, String filePath) {
            this.hospital = hospital;
            this.name = name;
            this.age = age;
            this.genders = genders;
            this.brithday = brithday;
            this.hasUpload = hasUpload;
            this.date = date;
            this.filePath = filePath;
        }
    }
}
