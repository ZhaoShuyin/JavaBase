package com.zsy.pdf.test;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * 改变复杂表格的宽度和对齐方式.
 */
public class TableWidthAlignment {
 
	/**
	 * Changing the width and alignment of the complete table.
	 * 
	 * param args no arguments needed throws IOException no arguments needed
	 * throws IOException
	 * 
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException,
			IOException {
		// 定义中文字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontCN = new Font(bfChinese, 12,
				Font.NORMAL);
		System.out.println("table width and alignment");
		// 步骤1：创建一个大小为A4的文档
		Document document = new Document(PageSize.A4);
		try {
			// 步骤 2:
			// 我们为document创建一个监听，并把PDF流写到文件中
			PdfWriter.getInstance(document, new FileOutputStream(
					"D:/abc/pdf/报告模板/1.pdf"));
			// 步骤 3:打开文档
			document.open();
			// 创建一个有3列的表格
			PdfPTable table = new PdfPTable(3);
			// 定义一个表格单元
			PdfPCell cell = new PdfPCell(new Paragraph("header with colspan 3"));
			// 定义一个表格单元的跨度
			cell.setColspan(3);
			// 把单元加到表格中
			table.addCell(cell);
			// 把下面这9项顺次的加入到表格中，当一行充满时候自动折行到下一行
			table.addCell("1.1");
			table.addCell("2.1");
			table.addCell("3.1");
			table.addCell("1.2");
			table.addCell("2.2");
			table.addCell("3.2");
			table.addCell("1.3");
			table.addCell("2.3");
			table.addCell("3.3");
			// 重新定义单元格
			cell = new PdfPCell(new Paragraph("cell test1"));
			// 定义单元格的框颜色
			cell.setBorderColor(new BaseColor(255, 0, 0));
			// 把单元格加到表格上，默认为一个单元
			table.addCell(cell);
			// 重新定义单元格
			cell = new PdfPCell(new Paragraph("cell test2"));
			// 定义单元格的跨度
			cell.setColspan(2);
			// 定义单元格的背景颜色
			cell.setBackgroundColor(new BaseColor(0xC0, 0xC0, 0xC0));
			// 增加到表格上
			table.addCell(cell);
			document.add(new Paragraph("默认情况下的大小---居中 80%", fontCN));
			// 增加到文档中
			document.add(table);
			document.add(new Paragraph("居中 100%", fontCN));
			// 设置表格大小为可用空白区域的100%
			table.setWidthPercentage(100);
			// 增加到文档中2
			document.add(table);
			document.add(new Paragraph("居右 50%", fontCN));
			// 设置表格大小为可用空白区域的50%
			table.setWidthPercentage(50);
			// 设置水平对齐方式为 居右
			table.setHorizontalAlignment(Element.ALIGN_RIGHT);
			document.add(new Paragraph("居左 50%", fontCN));
			// 增加到文档中3
			document.add(table);
			// 设置水平对齐方式为 居左
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			document.add(table);
		} catch (Exception de) {
			de.printStackTrace();
		}
		// 步骤 5:关闭文档
		document.close();
	}
}