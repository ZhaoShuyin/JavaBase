package com.zsy.pdf.test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.awt.Font;
import java.util.List;

class PdfUtil {


    public static void TableBule(int total, Font textFont, List data, Document doc) throws DocumentException {

//        // 创建一个有N列的表格
//
//        PdfPTable table = new PdfPTable(total);
//
//        table.setPaddingTop(20);
//
//        table.setSpacingAfter(20);
//
//        table.setTotalWidth(530); //设置列宽
//
//        // table.setTotalWidth(new float[]{ 100, 165, 100, 165 }); //设置列宽
//
//        table.setLockedWidth(true); //锁定列宽
//
//        PdfPCell cell;
//
//        for (int i = 0; i < data.size(); i++) {  //遍历数据行   每个数据行都是一个list
//
//            Iterator it = data.get(i).iterator();
//
//            int count = 0;
//
//            while (it.hasNext()) {               //遍历每行数据，每个数据都是一个单元格
//
//                cell = new PdfPCell(new Phrase(it.next(), textFont));
//
//                cell.setMinimumHeight(17); //设置单元格高度
//
//                cell.setUseAscender(true); //设置可以居中
//
//                //第一个单元格背景色
//
//                if (count % 2 == 0) {
//
//                    cell.setBackgroundColor(new BaseColor(231, 230, 230));
//
//                }
//
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT); //左对齐
//
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //设置垂直居中
//
//                table.addCell(cell);
//
//                count++;
//
//            }
//
//        }
//
//        doc.add(table);

    }

    /**
     * 生成一个表格
     *
     * @param total    总列数
     * @param textFont 字体
     * @param data     表格数据     X行    Y列
     * @param doc      PDF文档对象
     * @param colspan  第几列
     * @param rowspan  第几行
     * @param number   跨几列
     * @throws DocumentException
     * @author hou_fx
     */
    public static void TableColspan(int total, Font textFont, List<List<String>> data, Document doc, int[] rowspan, int[] colspan, int[] number) throws DocumentException {
        // 创建一个有N列的表格
       /* PdfPTable table = new PdfPTable(total);
        table.setPaddingTop(20);
        table.setSpacingAfter(20);
        table.setTotalWidth(530); //设置列宽
        // table.setTotalWidth(new float[]{ 100, 165, 100, 165 }); //设置列宽
        table.setLockedWidth(true); //锁定列宽
        PdfPCell cell;
        //数组下标
        int cos = 0;
        for (int i = 0; i < data.size(); i++) {  //遍历数据行   每个数据行都是一个list
            Iterator<String> it = data.get(i).iterator();
            int count = 0;
            while (it.hasNext()) {               //遍历每行数据，每个数据都是一个单元格
                cell = new PdfPCell(new Phrase(it.next(), textFont));
                cell.setMinimumHeight(17); //设置单元格高度
                cell.setUseAscender(true); //设置可以居中
                if (cos < rowspan.length && i == rowspan[cos] - 1 && count == colspan[cos] - 1) {
//					if (i==rowspan[cos]-1) {//行
//						if (count==colspan[cos]-1) {//列
                    cell.setColspan(number[cos]);//跨单元格
                    cos++;
//						}
//					}
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //设置垂直居中
                table.addCell(cell);
                count++;
            }
        }
        doc.add(table);
    }*/
    }
}