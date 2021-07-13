package com.zsy.pdf.test2;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title com.zsy.pdf
 * @date 2021/5/20
 * @autor Zsy
 */

public class PDFUtil {

    // 定义全局的字体静态变量
    private static Font font12;//主标题字体
    private static Font font10;//副标题字体
    private static Font font9_b;//副标题字体
    private static Font font8_b;//副标题字体
    private static Font font8_n;//副标题字体
    private static Font font6_b;//副标题字体
    private static Font font6_n;//副标题字体
    private static Font fontInfoA;//病例信息A
    private static Font fontInfoB;//病例信息B
    private static float width = 595.0f;
    private static float height = 842.0f;
    private static float padding = 32f;
    private static float topH = 35;
    private static float paramH = 15;
    private static float[] signSize = new float[]{82.5f, 35};
    private static float[] pictureSize = new float[]{550f, 600f};

    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            font12 = new Font(bfChinese, 13, Font.BOLD);
            font10 = new Font(bfChinese, 11, Font.NORMAL);
            font9_b = new Font(bfChinese, 10, Font.BOLD);    //检查结论
            font8_b = new Font(bfChinese, 10, Font.BOLD);    //记录时间/报告人/审核人1
            font8_n = new Font(bfChinese, 10, Font.NORMAL);  //记录时间/报告人/审核人2
            font6_b = new Font(bfChinese, 9, Font.BOLD);
            font6_n = new Font(bfChinese, 9, Font.NORMAL);
            fontInfoA = new Font(bfChinese, 8, Font.BOLD);    //信息标题
            fontInfoB = new Font(bfChinese, 8, Font.NORMAL);  //信息内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pdfInfo
     * @param vertical 是否是竖版
     * @param file
     * @return
     */
    public static boolean create(PDFInfo pdfInfo, boolean vertical, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            //大小A4纸
            Rectangle rectangle = PageSize.A4;
            if (vertical) {
                rectangle = PageSize.A4;
                padding = 32f;
                paramH = 15;
                pictureSize = new float[]{531f, 592f};        //180*200
            } else {
                rectangle = PageSize.A4.rotate();
                padding = 22.5f;
                paramH = 13;
                pictureSize = new float[]{796.5f, 368.75f};   //270*125
            }
            Rectangle rect = new Rectangle(rectangle);
            //设置背景颜色
            rect.setBackgroundColor(BaseColor.WHITE);
            //
            rect.setBorderColor(BaseColor.BLUE);
            //
            rect.setBorderWidth(5);

            Document document = new Document(rect);// 建立一个A4大小文档
            document.setMargins(padding, padding, padding, padding);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            //主标题
            document.add(PDFUtil.createTitle(pdfInfo.mainTitle, font12));
            //副标题
            document.add(PDFUtil.createTitle(pdfInfo.subTitle, font10));
            //顶部布局
            document.add(createTop(pdfInfo));
            //信息布局
            document.add(createInfo(pdfInfo));
            //参数/结论布局
            PdfPTable table = new PdfPTable(new float[]{5, 1.5f, 5});
            table.setWidthPercentage(100);
            table.addCell(creatParam(pdfInfo)); //属性数据

            Paragraph tag = new Paragraph("检查结论：", font9_b);
            tag.setPaddingTop(10);
            PdfPCell cell = new PdfPCell(tag);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.disableBorderSide(Rectangle.RIGHT);
            table.addCell(cell);

            Paragraph phrase = new Paragraph(pdfInfo.conclusion, font9_b);
            phrase.setPaddingTop(10);
            PdfPCell consultion = new PdfPCell(phrase);
            consultion.setVerticalAlignment(Element.ALIGN_MIDDLE);
            consultion.disableBorderSide(Rectangle.LEFT);
            table.addCell(consultion);
            document.add(table);

            Image image = Image.getInstance(pdfInfo.picture);
            image.scaleAbsolute(pictureSize[0], pictureSize[1]);
            System.out.println("PDFTest vertical:"+vertical+" pdf create: " + pictureSize[0] + " , " + pictureSize[1]);
            document.add(image);

            Paragraph paragraph = new Paragraph("日期：" + pdfInfo.reportDate, font10);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraph);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DicomUtil  create: " + e.toString());
            return false;
        }
        return true;
    }

    /**
     * 段落
     */
    public static Paragraph createTitle(String string, Font font) throws IOException, DocumentException {
        Paragraph paragraph = new Paragraph(string, font);
        paragraph.setAlignment(1);  //设置居中
        return paragraph;
    }

    /**
     * 顶部文字
     */
    public static PdfPTable createTop(PDFInfo info) throws Exception {  //595.0f
        PdfPTable pTable = new PdfPTable(new float[]{3, 8, 2, 3, 2, 3});
        pTable.setWidthPercentage(100);
        PdfPCell c1 = new PdfPCell(new Paragraph("记录时间：", font8_b));
        c1.setFixedHeight(topH);
        c1.setVerticalAlignment(Element.ALIGN_BOTTOM + Element.ALIGN_LEFT);

        PdfPCell c2 = new PdfPCell(new Paragraph(info.date, font8_n));
        c2.setFixedHeight(topH);
        c2.setVerticalAlignment(Element.ALIGN_BOTTOM);

        PdfPCell c3 = new PdfPCell(new Paragraph("报告人：", font8_b));
        c3.setFixedHeight(topH);
        c3.setVerticalAlignment(Element.ALIGN_BOTTOM);

        PdfPCell c4 = new PdfPCell();
        c4.setFixedHeight(topH);
        c4.setVerticalAlignment(Element.ALIGN_BOTTOM);
        if (info.reporter != null && new File(info.reporter).exists()) {
            Image image1 = Image.getInstance(info.reporter);
            image1.scaleAbsolute(signSize[0], signSize[1]);
            c4.setImage(image1);
        } else {
            c4.setRowspan((int) signSize[0]);
        }
        PdfPCell c5 = new PdfPCell(new Paragraph("审核人：", font8_b));
        c5.setVerticalAlignment(Element.ALIGN_BOTTOM);
        c5.setFixedHeight(topH);

        PdfPCell c6 = new PdfPCell();
        c6.setVerticalAlignment(Element.ALIGN_BOTTOM);
        c6.setFixedHeight(topH);
        if (info.auditor != null && new File(info.auditor).exists()) {
            Image image2 = Image.getInstance(info.auditor);
            image2.scaleAbsolute(signSize[0], signSize[1]);
            c6.setImage(image2);
        } else {
            c6.setRowspan((int) signSize[0]);
        }

        c1.setBorder(0);
        c2.setBorder(0);
        c3.setBorder(0);
        c4.setBorder(0);
        c5.setBorder(0);
        c6.setBorder(0);

        pTable.addCell(c1);
        pTable.addCell(c2);
        pTable.addCell(c3);
        pTable.addCell(c4);
        pTable.addCell(c5);
        pTable.addCell(c6);

        return pTable;
    }

    /**
     * 顶部文字
     */
    public static PdfPTable createInfo(PDFInfo info) throws Exception {  //595.0f
//        PdfPTable pTable = new PdfPTable(new float[]{1, 2, 1, 1, 1, 1, 1, 3, 1, 2, 2, 2, 2, 2, 1, 1});
        PdfPTable pTable = new PdfPTable(new float[]{
                3, info.name.length(),
                3, info.gender.length() + 1,
                3, info.age.length() + 1,
                3, info.department.length(),
                3, info.number.length(),
                4, info.inNo.length() + 1,
                4, info.outNo.length() + 1,
                3, info.bedNo.length() + 1
        });
        pTable.setWidthPercentage(100);  //宽度100%
        pTable.setSpacingBefore(5);      //
        pTable.addCell(getInfoCell(0, "姓名：", fontInfoA));
        pTable.addCell(getInfoCell(1, info.name, fontInfoB));
        pTable.addCell(getInfoCell(2, "性别：", fontInfoA));
        pTable.addCell(getInfoCell(3, info.gender, fontInfoB));
        pTable.addCell(getInfoCell(4, "年龄：", fontInfoA));
        pTable.addCell(getInfoCell(5, info.age, fontInfoB));
        pTable.addCell(getInfoCell(6, "科室：", fontInfoA));
        pTable.addCell(getInfoCell(7, info.department, fontInfoB));
        pTable.addCell(getInfoCell(8, "编号：", fontInfoA));
        pTable.addCell(getInfoCell(9, info.number, fontInfoB));
        pTable.addCell(getInfoCell(10, "住院号：", fontInfoA));
        pTable.addCell(getInfoCell(11, info.inNo, fontInfoB));
        pTable.addCell(getInfoCell(12, "门诊号：", fontInfoA));
        pTable.addCell(getInfoCell(13, info.outNo, fontInfoB));
        pTable.addCell(getInfoCell(14, "床号：", fontInfoA));
        pTable.addCell(getInfoCell(15, info.bedNo, fontInfoB));
        return pTable;
    }

    //
    private static PdfPCell getInfoCell(int index, String s, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(s, font));
        cell.setFixedHeight(20);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if (index == 0) {
            cell.disableBorderSide(Rectangle.RIGHT);
        } else if (index == 15) {
            cell.disableBorderSide(Rectangle.LEFT);
        } else {
            cell.disableBorderSide(Rectangle.LEFT + Rectangle.RIGHT);
        }
        return cell;
    }


    public static PdfPTable creatParam(PDFInfo info) {
        PdfPTable pTable = new PdfPTable(4);
        pTable.setWidthPercentage(90);
        pTable.addCell(getParam("心率：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.heartrate), font6_n));
        pTable.addCell(getParam("QT间期：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.QT_I), font6_n));
        pTable.addCell(getParam("P波：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.P_W), font6_n));
        pTable.addCell(getParam("QTC间期：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.QTC_I), font6_n));
        pTable.addCell(getParam("QRS宽：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.QRS_W), font6_n));
        pTable.addCell(getParam("QRS轴：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.QRS_TAxis), font6_n));
        pTable.addCell(getParam("PR间期：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.PR_I), font6_n));
        pTable.addCell(getParam("RV5+SV1:：", font6_b));
        pTable.addCell(getParam(String.valueOf(info.RV5SV1), font6_n));
        return pTable;
    }

    private static PdfPCell getParam(String s, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(s, font));
        cell.disableBorderSide(Rectangle.BOX);
        cell.setFixedHeight(paramH);
        return cell;
    }


}
