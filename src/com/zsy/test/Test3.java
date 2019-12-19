package com.zsy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title com.zsy.test
 * @date 2019/12/9
 * @autor Zsy
 */

class Test3 {

    public static void main(String[] args) {
        /*String s = paramMainList("101");
        System.out.println(s);*/
        Calendar ca = Calendar.getInstance();
        System.out.println(ca.get(Calendar.YEAR)+" - " +ca.get(Calendar.MONTH) +" - "+ca.get(Calendar.DAY_OF_MONTH));
        ca.add(Calendar.DAY_OF_YEAR, -31);
        System.out.println(ca.get(Calendar.YEAR)+" - " +ca.get(Calendar.MONTH) +" - "+ca.get(Calendar.DAY_OF_MONTH));
    }
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }
    /**
     * 主页列表参数
     */
    public static String paramMainList(String hospital) {
        return paramMainList(
                hospital,
                "", "",
                "", "",
                "", "",
                "", "");
    }

    /**
     * 主页列表
     *
     * @param hospitalId
     * @param patientName
     * @param reportStatus
     * @param hNumber
     * @param hItemNumber
     * @param deparment
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    public static String paramMainList(String hospitalId,
                                       String patientName, String reportStatus,
                                       String hNumber, String hItemNumber,
                                       String deparment, String type,
                                       String startDate, String endDate) {
        String param = "{\"Token\":null,\"Command\":\"E00052\",\"Param\":" +
                "{\"hospital_Code\":\"" + hospitalId
                + "\",\"EXAM_CLASS\":\"" + "ECG" +
                "\",\"Type\":\"Search\"," +
                "\"hospitalPatientName\":\"" + (patientName != null ? patientName : "" )        //患者姓名
                + "\",\"hospitalReportStatus\":\"" + (reportStatus != null ? reportStatus : "") //报告状态
                + "\",\"hospitalNumber\":\"" + (hNumber != null ? hNumber : "")                  //病历号1
                + "\",\"hospitalItemNumber\":\"" + (hItemNumber != null ? hItemNumber : "")      //病历号
                + "\",\"hospitalApplyDepartment\":\"" + (deparment != null ? deparment : "")     //科室
                + "\",\"hospitalPatientSource\":\"" + (type != null ? type : "")                 //患者来源
                + "\",\"hospitalDateTime\":\"" + (startDate != null ? startDate : "")            //开始日期
                + "\",\"hospitalItemDateTime\":\"" + (endDate != null ? endDate : "")            //截止日期
                + "\"},\"RequestModel\":null}";
        return param;
    }
}
