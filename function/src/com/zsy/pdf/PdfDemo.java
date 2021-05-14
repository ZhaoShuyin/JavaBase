package com.zsy.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;


/**
 * @Title com.zsy.pdf
 * @date 2021/5/8
 * @autor Zsy
 */

public class PdfDemo {

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
        //设置边框
        rect.setBorder(Rectangle.BOX);
        //
        rect.setBorderColor(BaseColor.BLUE);
        //
        rect.setBorderWidth(5);

        System.out.println("A4 PDF 宽: "+rect.getWidth()+" , 高: "+rect.getHeight());

        Document document = new Document(rect);// 建立一个A4大小文档

        try {
            long millis = System.currentTimeMillis();
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("D:/abc/pdf/zzxt_pdf/"+ millis +".pdf"));
            document.addTitle("标题");
            document.addAuthor("作者");
            document.addCreationDate();
            document.addCreator("创建者");
            document.addSubject("Subject");
            document.addKeywords("Keywords");
            document.addHeader("key","value");
            document.open();
            document.add(new Paragraph("段落文字显示",titlefont));


            Image image = Image.getInstance("D:/abc/pdf/zzxt_pdf/Image.png");
            image.scaleAbsolute(500,500);
            image.setAbsolutePosition(10, 20);      // （以左下角为原点）设置图片的坐标
            document.add(image);

            document.close();

            System.out.println("创建 "+millis+".pdf 完毕");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
