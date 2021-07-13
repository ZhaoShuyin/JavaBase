package com.zsy.pdf;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title com.zsy.pdf
 * @date 2021/5/20
 * @autor Zsy
 */

public class ReportPdf {

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

    public static void main(String[] args) {
        //大小A4纸
        Rectangle rect = new Rectangle(PageSize.A4);
        //设置背景颜色
        rect.setBackgroundColor(BaseColor.WHITE);
        ////宽: 595.0 , 高: 842.0
        System.out.println("默认 A4 PDF 宽: " + rect.getWidth() + " , 高: " + rect.getHeight());
        //
        rect.rotate();

        Document document = new Document(rect);// 建立一个A4大小文档
        document.addTitle("标题");
        document.setPageCount(1);

        String pdfName = "D:/abc/pdf/报告模板/" + System.currentTimeMillis() + ".pdf";
        File file = new File(pdfName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            //Chunk对象: a String, a Font, and some attributes
            document.add(new Chunk("China"));
            document.add(new Chunk(" "));
            Font font = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
            Chunk id = new Chunk("chinese", font);
            id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
            id.setTextRise(6);
            document.add(id);
            document.add(Chunk.NEWLINE);

            document.add(new Chunk("Japan"));
            document.add(new Chunk(" "));
            Font font2 = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
            Chunk id2 = new Chunk("japanese", font2);
            id2.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
            id2.setTextRise(6);
            id2.setUnderline(0.2f, -2f);
            document.add(id2);
            document.add(Chunk.NEWLINE);

//Phrase对象: a List of Chunks with leading
            document.newPage();
            document.add(new Phrase("Phrase page"));

            Phrase director = new Phrase();
            Chunk name = new Chunk("China");
            name.setUnderline(0.2f, -2f);
            director.add(name);
            director.add(new Chunk(","));
            director.add(new Chunk(" "));
            director.add(new Chunk("chinese"));
            director.setLeading(24);
            document.add(director);

            Phrase director2 = new Phrase();
            Chunk name2 = new Chunk("Japan");
            name2.setUnderline(0.2f, -2f);
            director2.add(name2);
            director2.add(new Chunk(","));
            director2.add(new Chunk(" "));
            director2.add(new Chunk("japanese"));
            director2.setLeading(24);
            document.add(director2);

//Paragraph对象: a Phrase with extra properties and a newline
            document.newPage();
            document.add(new Paragraph("Paragraph page"));

            Paragraph info = new Paragraph();
            info.add(new Chunk("China "));
            info.add(new Chunk("chinese"));
            info.add(Chunk.NEWLINE);
            info.add(new Phrase("Japan "));
            info.add(new Phrase("japanese"));
            document.add(info);

//List对象: a sequence of Paragraphs called ListItem
            document.newPage();
            List list = new List(List.ORDERED);
            for (int i = 0; i < 10; i++) {
                ListItem item = new ListItem(String.format("%s: %d movies",
                        "country" + (i + 1), (i + 1) * 100), new Font(
                        Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE));
                List movielist = new List(List.ORDERED, List.ALPHABETICAL);
                movielist.setLowercase(List.LOWERCASE);
                for (int j = 0; j < 5; j++) {
                    ListItem movieitem = new ListItem("Title" + (j + 1));
                    List directorlist = new List(List.UNORDERED);
                    for (int k = 0; k < 3; k++) {
                        directorlist.add(String.format("%s, %s", "Name1" + (k + 1),
                                "Name2" + (k + 1)));
                    }
                    movieitem.add(directorlist);
                    movielist.add(movieitem);
                }
                item.add(movielist);
                list.add(item);
            }
            document.add(list);


            document.close();
//            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
