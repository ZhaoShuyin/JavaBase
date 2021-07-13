package com.zsy.pdf;

import java.io.File;

/**
 * @Title com.zsy.pdf
 * @date 2021/5/20
 * @autor Zsy
 */

public class PDFInfo {

    public String mainTitle; //主标题
    public String subTitle;  //副标题

    public String date;      //记录时间

    public String reporter;    //报告人签名图片
    public String auditor;     //审核人签名图片

    public String name;      //姓名
    public String gender;    //性别
    public String age;       //年龄
    public String department;//科室
    public String number;    //编号
    public String inNo;      //住院号
    public String outNo;     //门诊号
    public String bedNo;     //床号

    public void setInfo(String name, String gender, String age, String department, String number, String inNo, String outNo, String bedNo) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.number = number;
        this.inNo = inNo;
        this.outNo = outNo;
        this.bedNo = bedNo;
    }

    public String heartrate;      //心律
    public String QT_I;           //QT间期
    public String P_W;            //P波宽度
    public String QTC_I;          //QTC间期
    public String QRS_W;          //QRS波宽度
    public String QRS_TAxis;      //QRS轴
    public String PR_I;           //PR间期
    public String RV5SV1;      //RV5+SV1 (mv)

    public void setParam(String heartrate,String QT_I, String p_W, String QTC_I, String QRS_W, String QRS_TAxis, String PR_I, String RV5SV1) {
        this.heartrate = heartrate;
        this.QT_I = QT_I;
        this.P_W = p_W;
        this.QTC_I = QTC_I;
        this.QRS_W = QRS_W;
        this.QRS_TAxis = QRS_TAxis;
        this.PR_I = PR_I;
        this.RV5SV1 = RV5SV1;
    }

    public String conclusion;//检查结论

    public String picture;     //心电图片

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }


}
