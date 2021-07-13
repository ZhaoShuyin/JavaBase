package com.zsy.pdf.test2;

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

    public void setParam(String heartrate, String QT_I, String p_W, String QTC_I, String QRS_W, String QRS_TAxis, String PR_I, String RV5SV1) {
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

    public String reportDate;

    /**
     *
     */
    public void formatting() {
        if (mainTitle == null || mainTitle.length() == 0) {
            mainTitle = "无";
        }
        if (subTitle == null || subTitle.length() == 0) {
            subTitle = "无";
        }
        if (date == null || date.length() == 0) {
            date = "无";
        }
        if (name == null || name.length() == 0) {
            name = "无";
        }
        if (gender == null || gender.length() == 0) {
            gender = "无";
        }
        if (age == null || age.length() == 0) {
            age = "无";
        }
        if (department == null || department.length() == 0) {
            department = "无";
        }
        if (number == null || number.length() == 0) {
            number = "无";
        }
        if (inNo == null || inNo.length() == 0) {
            inNo = "无";
        }
        if (outNo == null || outNo.length() == 0) {
            outNo = "无";
        }
        if (bedNo == null || bedNo.length() == 0) {
            bedNo = "无";
        }
        if (heartrate == null || heartrate.length() == 0) {
            heartrate = "0";
        }
        if (QT_I == null || QT_I.length() == 0) {
            QT_I = "0";
        }
        if (P_W == null || P_W.length() == 0) {
            P_W = "0";
        }
        if (QTC_I == null || QTC_I.length() == 0) {
            QTC_I = "0";
        }
        if (QRS_W == null || QRS_W.length() == 0) {
            QRS_W = "0";
        }
        if (QRS_TAxis == null || QRS_TAxis.length() == 0) {
            QRS_TAxis = "0";
        }
        if (PR_I == null || PR_I.length() == 0) {
            PR_I = "0";
        }
        if (RV5SV1 == null || RV5SV1.length() == 0) {
            RV5SV1 = "0";
        }
        if (conclusion == null || conclusion.length() == 0) {
            conclusion = "无";
        }
    }

}
