package com.zsy.pdf;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Java2PDF {
    // 定义全局的字体静态变量
    private static Font titlefont;
    private static Font keyfont;

    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 16, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建单元格（指定字体、水平..）
     */
    private static PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    public static void main(String[] args) {
        try {
            // 1.新建document对象
            Document document = new Document(PageSize.A4);// 建立一个A4大小
            // 2.建立一个书写器(Writer)与document对象关联
            File dirFile = new File("D:/abc/pdf/zzxt_pdf");
            File file = new File(dirFile + File.separator + "001.pdf");
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            file.createNewFile();
            PdfWriter.getInstance(document, new FileOutputStream(file));

            // 3.打开文档
            document.open();
            // 段落
            Paragraph paragraph = new Paragraph("运维单", titlefont);
            paragraph.setAlignment(1); // 设置文字居中 0靠左 1，居中 2，靠右
            paragraph.setIndentationLeft(12); // 设置左缩进
            paragraph.setIndentationRight(12); // 设置右缩进
            paragraph.setFirstLineIndent(24); // 设置首行缩进
            paragraph.setLeading(20f); // 行间距
            paragraph.setSpacingBefore(5f); // 设置段落上空白
            paragraph.setSpacingAfter(10f); // 设置段落下空白
            // 表格
            PdfPTable table = new PdfPTable(new float[]{100, 90, 90});
            table.addCell(createCell("设备名称：", keyfont, Element.ALIGN_LEFT));
            table.addCell(createCell("规格型号：", keyfont, Element.ALIGN_LEFT));
            table.addCell(createCell("设备编号：", keyfont, Element.ALIGN_LEFT));
            table.addCell(createCell("维护管理单位：", keyfont, Element.ALIGN_LEFT));
            table.addCell(createCell("安装地点：", keyfont, Element.ALIGN_LEFT));
            table.addCell(createCell("维护保养人：", keyfont, Element.ALIGN_LEFT));
            PdfPTable table2 = new PdfPTable(new float[]{140, 140});
            table2.addCell(createCell("本次巡检人：", keyfont, Element.ALIGN_LEFT));
            table2.addCell(createCell("本次时间：", keyfont, Element.ALIGN_LEFT));
            PdfPTable table3 = new PdfPTable(new float[]{280});
            table3.addCell(createCell("设备巡检内容、情况、及处理情况说明", keyfont, Element.ALIGN_CENTER));
            PdfPTable table4 = new PdfPTable(new float[]{10, 30, 90, 50, 50, 50});
            table4.addCell(createCell("序号", keyfont, Element.ALIGN_CENTER));
            table4.addCell(createCell("子系统", keyfont, Element.ALIGN_CENTER));
            table4.addCell(createCell("巡检部件及事项说明", keyfont, Element.ALIGN_CENTER));
            table4.addCell(createCell("巡检部件情况说明", keyfont, Element.ALIGN_CENTER));
            table4.addCell(createCell("处理情况", keyfont, Element.ALIGN_CENTER));
            table4.addCell(createCell("处理后结果说明", keyfont, Element.ALIGN_CENTER));
            PdfPTable table5_1 = new PdfPTable(new float[]{90, 50, 50, 50});
            table5_1.addCell(createCell("查看外标响应是否正常，若异常，排查原因", keyfont, Element.ALIGN_CENTER));
            table5_1.addCell(createCell("", keyfont, Element.ALIGN_CENTER));
            table5_1.addCell(createCell("", keyfont, Element.ALIGN_CENTER));
            table5_1.addCell(createCell("", keyfont, Element.ALIGN_CENTER));
            table5_1.addCell(createCell("查看ZF-PKU-1007、GCMS运行是否正常", keyfont, Element.ALIGN_CENTER));
            table5_1.addCell(createCell("", keyfont, Element.ALIGN_CENTER));
            table5_1.addCell(createCell("", keyfont, Element.ALIGN_CENTER));
            table5_1.addCell(createCell("", keyfont, Element.ALIGN_CENTER));

            PdfPTable table5 = new PdfPTable(new float[]{10, 30, 240});
            table5.addCell(createCell("1", keyfont, Element.ALIGN_CENTER));
            table5.addCell(createCell("voc在线监测系统", keyfont, Element.ALIGN_CENTER));
            table5.addCell(new PdfPCell(table5_1));

            document.add(paragraph);
            document.add(table);
            document.add(table2);
            document.add(table3);
            document.add(table4);
            document.add(table5);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
