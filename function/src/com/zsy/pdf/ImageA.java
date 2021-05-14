package com.zsy.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.imageio.plugins.common.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @Title com.zsy.pdf
 * @date 2021/5/8
 * @autor Zsy
 */

class ImageA {

    /**
     * 1：
     * 获取组件名，以组件为中心进行插入。
     * 同时可以将图片大小适应组件大小（比如我获取pdf一个签章域的名字）
     */
    public static void main(String[] args) throws Exception {
        imageTest();
    }

    public static void imageTest(){
        Rectangle rectangle = new Rectangle(PageSize.A4);       // 设置 PDF 纸张矩形，大小采用 A4
        rectangle.setBackgroundColor(BaseColor.WHITE);         // 设置背景色
        //创建一个文档对象，设置初始化大小和页边距
        Document document = new Document(rectangle, 10, 10, 10, 10);     // 上、下、左、右页间距

        String pdfPath = "D:/abc/pdf/zzxt_pdf/123.pdf";   // PDF 的输出位置
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        } catch (DocumentException e) {
             System.out.println("将文档对象设置到文件输出流中 - 出错了！");
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定的文件！");
        }
        document.open();    // 打开文档对象

        String imagePath = "D:/abc/pdf/zzxt_pdf/Image.png";      // 图片的绝对路径
        Image image = null;     // 声明图片对象
        try {
            image = Image.getInstance(imagePath);       // 取得图片对象
        } catch (BadElementException | IOException e) {
            System.out.println("实例化【图片】 - 失败！");
            return;
        }
        //1100 1080
        image.scaleAbsolute(500,500);
        image.setAbsolutePosition(10, 20);      // （以左下角为原点）设置图片的坐标

        try {
            document.add(image);
        } catch (DocumentException e) {
            System.out.println("将图片对象加入到文档对象中时 - 出错了！");
        }
        document.close();       // 关闭文档
    }


    protected static void creatPDF() throws DocumentException, FileNotFoundException, Exception, IOException, BadElementException, MalformedURLException {
        // Document document = new Document(PageSize.A4);
       /* Rectangle r = new Rectangle(0, 0, 600, 600);
        Document document = new Document(PageSize.A4, 15, 15, 15, 15);
        PdfWriter.getInstance(document, new FileOutputStream(new File("D:\\test.pdf")));
        document.open();
        document.newPage();
        PdfPTable ptable = new PdfPTable(6);
        System.out.println(document.getPageSize().getWidth() + "," + document.getPageSize().getWidth() / 8);
        String imagetemp = "D:\\temp.jpg";
        for (int j = 0; j < 100; j++) {
            OutputStream stream = new FileOutputStream(imagetemp);
            BufferedImage bufimage = QRCodeUtil.createImage("1018043486", "http://localhost/images/tb_top_logo.png", 270, 300, 4, true, "1018043486");
            ImageIO.write(bufimage, QRCodeUtil.FORMAT_NAME, stream);
            Image jpeg = Image.getInstance(imagetemp);
            jpeg.scaleAbsolute(100, 100);
            jpeg.setAlignment(Image.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell(jpeg);
            ptable.addCell(cell);
            stream.flush();
            stream.close();
        }
        ptable.setWidthPercentage(100);//去掉table边距全屏操作
        document.add(ptable);
        document.close();*/
    }


    private static void imageTest1() throws IOException, DocumentException {
        // 模板文件路径
        String templatePath = "D:/abc/pdf/zzxt_pdf/123.pdf";
        // 生成的文件路径
        String targetPath = "D:/abc/pdf/zzxt_pdf/out.pdf";
        // 关键字名
        String fieldName = "SignatureField1";
        // 图片路径
        String imagePath = "D:/abc/pdf/zzxt_pdf/Image.jpg";


        FileOutputStream fos = new FileOutputStream(targetPath);
        // 读取模板文件
        PdfReader reader = new PdfReader(new FileInputStream(new File(templatePath)));
        PdfStamper stamper = new PdfStamper(reader, fos);
        // 提取pdf中的表单
        AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));


        // 通过域名获取所在页和坐标，左下角为起点
//        int pageNo = form.getFieldPositions(fieldName).get(0).page;

        //
        Map<String, AcroFields.Item> fields = form.getFields();
        Iterator<Map.Entry<String, AcroFields.Item>> iterator = fields.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, AcroFields.Item> next = iterator.next();
            System.out.println(next.getKey()+" : "+next.getValue());
        }

        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        //x = 20f;
        //y = 40f;
        // 读图片
        Image image = Image.getInstance(imagePath);
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(1);//pageNo
        // 根据域的大小缩放图片
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // 添加图片
        image.setAbsolutePosition(x, y);
        under.addImage(image);
        fos.flush();
        fos.close();
        /*stamper.close();*/
        reader.close();
    }

    /**
     * 2：
     * 关键字签章，
     * 该方式是读取pdf里面的文字，以文字为中心进行定位。
     * 其实和上面差不多，只是定位方式变化了而已。
     */
    public static byte[] signPdfByStampKeyNocert(Object source, URL imagePath, String stampKey) throws Exception {
        /*//source:待插入图片的pdf ,imagePath ：待插入图片  ， stampKey：关键字（比如 “图片插入在我这”）
        // 临时文件路径
        String targetPath = "E://source.pdf";
        // 读取模板文件
        PdfReader reader = null;
        if (source instanceof String) {
            reader = new PdfReader((String) source);
        } else if (source instanceof byte[]) {
            reader = new PdfReader((byte[]) source);
        } else if (source instanceof URL) {
            reader = new PdfReader((URL) source);
        }
        byte[] by1 = null;
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
        // 则使用座标签章，获取关键字的各类参数 （这个方法我整合了，各位可自行查找）
        Map<String, Object> params = PDFUtil.getSignaturePostionInfo(reader, imagePath, stampKey);
        // 关键字
        Rectangle signRect = (Rectangle) params.get("sign8PositionRectangle");
        // 获取图片的绝对位置，距离
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        // 获取关键字所在页码
        int pageno = (int) params.get("sign8PositionKeywordsPageIndex");
        // 获取操作的页面
        PdfContentByte overContent = stamper.getOverContent(pageno);
        Image image = Image.getInstance(imagePath);
        // 设置图片宽高
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // image.scaleAbsolute(x, y);
        // 设置图片位置
        image.setAbsolutePosition(x, y);// 左边距、底边距
        overContent.addImage(image);
        overContent.stroke();
        stamper.close();
        reader.close();
        byte[] bytes = FileUtil.getFile(targetPath);
        FileUtil.delFile(targetPath);
        return bytes;*/
        return null;
    }


}
