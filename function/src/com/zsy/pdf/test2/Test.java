package com.zsy.pdf.test2;

import java.io.File;

/**
 * @Title com.zsy.pdf.test2
 * @date 2021/6/17
 * @autor Zsy
 */

public class Test {

    public static void main(String[] args) {
        PDFInfo pdfInfo = new PDFInfo();
        pdfInfo.picture= "D:/abc/pdf/test/report-h.png";
        pdfInfo.formatting();
        File file = new File("D:/abc/pdf/test/" + System.currentTimeMillis() + ".pdf");
        boolean b = PDFUtil.create(pdfInfo, false, file);
        System.out.println(file.getAbsolutePath()+"  "+b);
    }

}
