package com.zsy.pdf;

import java.io.File;

/**
 * @Title com.zsy.pdf
 * @date 2021/5/21
 * @autor Zsy
 */

public class Test {


    public static void main(String[] args) throws Exception {
        PDFInfo info = new PDFInfo();
        info.mainTitle = "解放军总医院第一医学中心--12导联同步心电图报告";
        info.subTitle = "副标题动态心电入报告";
        info.date = "2020-08-10 14:11";
        info.picture = "D:/abc/pdf/Image.png";
        info.reporter = "D:/abc/pdf/001.png";
        info.auditor = "D:/abc/pdf/001.png";
        info.name = "白杰";
        info.gender = "男";
        info.age = "35";
        info.department = "门诊心电图室";
        info.number = "ID00008";
        info.inNo = "251442562";
        info.outNo = "11020100824";
        info.bedNo = "56";
        info.bedNo = "56";
        info.setParam(
                "99 bpm", "303 ms",
                "49 ms", "389 ms",
                "75 ms", "64",
                "115ms", "1.94mV");
        info.conclusion = "这是检查结论";
        File file = new File("D:/abc/pdf/报告模板/" + System.currentTimeMillis() + ".pdf");
        boolean b = PDFUtil.create(info, false, file);
        System.out.println("创建 " + file.getName());
    }
}
