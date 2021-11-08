package com.zsy.json;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Title 报告/会诊 参数Bean
 * @date 2020/3/16
 * @autor Zsy
 */
public class ParamEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = -3556791626806538094L;

    /**
     * 心博数据v
     */
    public class BEAT {
        public int HeartBeat;
        public int Position;
    }

    public int[][] getBEATS() {
        if (BEATS_POSITION == null) {
            return new int[0][2];
        }
        Type listType = new TypeToken<LinkedList<BEAT>>() {
        }.getType();
        Gson gson = new Gson();
        LinkedList<BEAT> linkedList = gson.fromJson(BEATS_POSITION, listType);
        int size = linkedList.size();
        int[][] ints = new int[size][2];
        for (int i = 0; i < size; i++) {
            BEAT beat = linkedList.get(i);
            ints[i][0] = beat.Position;
            ints[i][1] = beat.HeartBeat;
        }
        return ints;
    }

    public String getBEATS_POSITION() {
        return BEATS_POSITION;
    }

    public void setBEATS_POSITION(String BEATS_POSITION) {
        this.BEATS_POSITION = BEATS_POSITION;
    }

    //心博数据
    @Expose(serialize = false,deserialize = false)
    public String BEATS_POSITION;

    public String AUTOMATIC_DIAGNOSIS;    //自动诊断结论

    public int HEARTRATE;                 //心率
    public int PDURATION;                 //P波宽度

    public int QRSDURATION;               //QRS波宽度
    public int TDURATION;                 //T波宽度

    public int PRINTERAL;                 //PR间期
    public int RRINTERAL;                 //RR间期

    public int QTINTERAL;                 //QT间期
    public int QTCINTERAL;                //QTC间期

    public int PAXIS;
    public int QRSAXIS;                   //QRS轴
    public int TAXIS;

    public double RV5;
    public double SV1;
    public double RV5SV1;                //

    public String RHYTHM;                 //心律


    public Map<String,String> getParams(){
        Map<String,String> map = new HashMap<>();

        return map;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public String getAUTOMATIC_DIAGNOSIS() {
        return AUTOMATIC_DIAGNOSIS;
    }

    public void setAUTOMATIC_DIAGNOSIS(String AUTOMATIC_DIAGNOSIS) {
        this.AUTOMATIC_DIAGNOSIS = AUTOMATIC_DIAGNOSIS;
    }

    public int getHEARTRATE() {
        return HEARTRATE;
    }

    public void setHEARTRATE(int HEARTRATE) {
        this.HEARTRATE = HEARTRATE;
    }

    public int getPDURATION() {
        return PDURATION;
    }

    public void setPDURATION(int PDURATION) {
        this.PDURATION = PDURATION;
    }

    public int getQRSDURATION() {
        return QRSDURATION;
    }

    public void setQRSDURATION(int QRSDURATION) {
        this.QRSDURATION = QRSDURATION;
    }

    public int getTDURATION() {
        return TDURATION;
    }

    public void setTDURATION(int TDURATION) {
        this.TDURATION = TDURATION;
    }

    public int getPRINTERAL() {
        return PRINTERAL;
    }

    public void setPRINTERAL(int PRINTERAL) {
        this.PRINTERAL = PRINTERAL;
    }

    public int getRRINTERAL() {
        return RRINTERAL;
    }

    public void setRRINTERAL(int RRINTERAL) {
        this.RRINTERAL = RRINTERAL;
    }

    public int getQTINTERAL() {
        return QTINTERAL;
    }

    public void setQTINTERAL(int QTINTERAL) {
        this.QTINTERAL = QTINTERAL;
    }

    public int getQTCINTERAL() {
        return QTCINTERAL;
    }

    public void setQTCINTERAL(int QTCINTERAL) {
        this.QTCINTERAL = QTCINTERAL;
    }

    public int getPAXIS() {
        return PAXIS;
    }

    public void setPAXIS(int PAXIS) {
        this.PAXIS = PAXIS;
    }

    public int getQRSAXIS() {
        return QRSAXIS;
    }

    public void setQRSAXIS(int QRSAXIS) {
        this.QRSAXIS = QRSAXIS;
    }

    public int getTAXIS() {
        return TAXIS;
    }

    public void setTAXIS(int TAXIS) {
        this.TAXIS = TAXIS;
    }

    public double getRV5() {
        return RV5;
    }

    public void setRV5(double RV5) {
        this.RV5 = RV5;
    }

    public double getSV1() {
        return SV1;
    }

    public void setSV1(double SV1) {
        this.SV1 = SV1;
    }

    public double getRV5SV1() {
        return RV5SV1;
    }

    public void setRV5SV1(double RV5SV1) {
        this.RV5SV1 = RV5SV1;
    }

    public String getRHYTHM() {
        return RHYTHM;
    }

    public void setRHYTHM(String RHYTHM) {
        this.RHYTHM = RHYTHM;
    }
}