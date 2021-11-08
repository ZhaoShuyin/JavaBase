package com.zsy.dicom.test;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Title Dicom(dcm4che)工具类
 * @date 2020/5/11
 * @autor Zsy
 */
public class DicomUtil {

    public static int tag(int group, int element) {
        return group << 16 | element;
    }

    /**
     * show("DcmCollectDate", 0x0008, 0x002a);//  采集时间  年月日
     * show("DcmData", 0x5400, 0x0100);       //  数据
     * show("DcmLeadsNumber", 0x003a, 0x0005);// 导联数量  12导联
     * show("DcmDataNumber", 0x003a, 0x0010); //  总采集点数  数据的总点数
     * show("DcmSampling", 0x003a, 0x001a);   //  采样点  康泰一般是1000
     * show("DcmTagKey", 0x003a, 0x0200);     //
     * show("DcmLeadName", 0x003a, 0x0203);   //  导联名称
     * show("DcmGainValue", 0x003a, 0x0210);  //  每毫伏对应的值   暂时定1000
     * show("DcmBitsStored", 0x003a, 0x021a); //  存储位数  一般是16位   2个字节对应一个值
     * show("DcmWaveData", 0x5400, 0x1010);
     */
    public static boolean dicomFile(DicomInfo info, File dcmFile) {
        try {
            Attributes meta = new Attributes();
            meta.setBytes(tag(0x0002, 0x0003), VR.OB, "1.2.8".getBytes());
            Attributes att = new Attributes();
            att.setString(tag(0x0008, 0x0005), VR.CS, "ISO_IR 6");
            att.setString(tag(0x0008, 0x0060), VR.CS, "ECG");               //形态
            att.setString(tag(0x0008, 0x002a), VR.DT, info.date);                          //采集日期时间

            Sequence sequence = att.newSequence(tag(0x5400, 0x0100), 0);      //二级Attributes,DicomAttributes
            Attributes attributes = new Attributes();
            attributes.setString(tag(0x003a, 0x0005), VR.CS, info.leadsNumber);            //0x003a, 0x0005 导联数量
            attributes.setInt(tag(0x003a, 0x0010), VR.UL, info.dataLength);                //0x003a, 0x0010 数据个数(单个导联)
            attributes.setFloat(tag(0x003a, 0x001a), VR.DS, info.sampling);                  //0x003a, 0x001a  数据采样率(每秒数据个数)

            attributes.setString(tag(0x003A, 0x0004), VR.CS, "ORIGINAL");               //
            attributes.setInt(tag(0x5400, 0x1004), VR.US, 16);                        //
            attributes.setString(tag(0x5400, 0x1006), VR.CS, "SS");                     //

            attributes.setBytes(tag(0x5400, 0x1010), VR.OW, info.bytes);                   //<<<数据>>>
            sequence.add(attributes);                                                                     //添加二级数据

            Sequence sData = attributes.newSequence(tag(0x003A, 0x0200), 0);//三级Attributes(每个导联属性信息)
            for (int i = 0; i < info.leads.length; i++) {
                Attributes a = new Attributes();                         //三级数据
                a.setString(tag(0x003a, 0x0203), VR.SH, info.leads[i]);      //导联名称
                a.setFloat(tag(0x003a, 0x0210), VR.DS, info.leadsGain[i]);   //导联增益
                a.setInt(tag(0x003a, 0x021a), VR.US, 16);                //导联数据位数
                sData.add(a);
                //添加三级数据
                System.out.println("添加+ " + a.getString(tag(0x003a, 0x0203)) + "  === > toString(): \n" + sData.get(i).toString());
            }


            DicomOutputStream dicomOutputStream = new DicomOutputStream(dcmFile);
            dicomOutputStream.writeFileMetaInformation(meta);
            att.writeTo(dicomOutputStream);
            dicomOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取dicom文件中的导联标签属性值
     *
     * @param file
     * @return
     */
    public static String readModeName(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            DicomInputStream dis = new DicomInputStream(file);
            Attributes attrs = dis.readDataset(-1, -1);
            Attributes dataset = attrs.getNestedDataset(tag(0x5400, 0x0100));
            String string = dataset.getString(tag(0x003A, 0x0005), 0);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从文件中读取Dicom属性数据
     *
     * @param file
     * @return
     */
    public static DicomInfo readDicomFile(File file) {
        DicomInfo dicomInfo = new DicomInfo();
        try {
            DicomInputStream dis = new DicomInputStream(file);
            Attributes attrs = dis.readDataset(-1, -1);
            Attributes dataset = attrs.getNestedDataset(tag(0x5400, 0x0100));
            String string = dataset.getString(tag(0x003A, 0x0005));
            dicomInfo.leadsNumber = string;                                    //导联个数
            dicomInfo.dataLength = dataset.getInt(tag(0x003a, 0x0010), 0); //数据个数(单个导联)
            dicomInfo.sampling = (int) dataset.getFloat(tag(0x003a, 0x001a), 0);   //采样率
            dicomInfo.bytes = dataset.getBytes(tag(0x5400, 0x1010));              //数据

            Sequence sequence = dataset.getSequence(tag(0x003A, 0x0200));

            int size = sequence.size();
            dicomInfo.leads = new String[size];
            dicomInfo.leadsGain = new float[size];
            for (int i = 0; i < size; i++) {
                Attributes attributes = sequence.get(i);
                dicomInfo.leads[i] = attributes.getString(tag(0x003a, 0x0203));
                dicomInfo.leadsGain[i] = attributes.getFloat(tag(0x003a, 0x0210), 1);
                int anInt = attributes.getInt(tag(0x003a, 0x021a), 16);
            }
            return dicomInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解析出可以画图的数据
     *
     * @param dicomInfo
     * @return
     */
    public static int[][] turnInts(DicomInfo dicomInfo) {
        int number = Integer.parseInt(dicomInfo.leadsNumber);
//        int length = dicomInfo.dataLength;
        int length = dicomInfo.bytes.length / 2 / number;
        byte[] bytes = dicomInfo.bytes;

//        bytes[0] = 1;
//        bytes[1] = 0;
//
//        bytes[24] = 2;
//        bytes[25] = 0;
//
//        bytes[48] = 3;
//        bytes[49] = 0;

       /* if (bytes.length != (number * length * 2)) {
            return null;
        }*/
        System.out.println("length:" + length + ", number:" + number + ", bytes:" + bytes.length);
        int[][] ints = new int[number][length];

        float[] temp = new float[number];

        int index = 0;
        System.out.println("===============================================================");
        for (int j = 0; j < length; j++) {
            index = number * 2 * j;
            for (int i = 0; i < number; i++) {
                ints[i][j] = (bytes[index + 2 * i] & 255 | bytes[index + 2 * i + 1] << 8);
                temp[i] = (int) (ints[i][j] / 185f);
            }
            System.out.println(Arrays.toString(temp));
        }
        System.out.println("===============================================================");

        return ints;
    }


    public static void main(String[] args) throws Exception {


        System.out.println(Integer.valueOf('a'));
        System.out.println(Integer.valueOf('b'));
        System.out.println(Integer.valueOf('c'));

      /*  File file = new File("D:/abc/dicom/gain/test29.dat");
        int length = (int) file.length();
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        byte[] turn = turn(bytes, "sHo&30(*uY");

        FileOutputStream outputStream = new FileOutputStream("D:/abc/dicom/gain/test29-转.dat");
        outputStream.write(turn);
        outputStream.close();

        DicomInfo info = new DicomInfo(turn, 12, 5.4f);
        File dcmFile = new File("D:/abc/dicom/gain/"+System.currentTimeMillis()+".dcm");
        boolean b = dicomFile(info, dcmFile);
        System.out.println("创建 dicom  " + b+"  "+dcmFile.getAbsolutePath());*/


//        File origin = new File("D:/abc/dicom/gain/1630035152322.dat");
//        byte[] bytes = new byte[(int) origin.length()];
//        FileInputStream inputStream = new FileInputStream(origin);
//        inputStream.read(bytes);
//        DicomInfo info = new DicomInfo(bytes, 12, 5.4f);
//        File dcmFile = new File("D:/abc/dicom/gain/"+System.currentTimeMillis()+".dcm");
//        boolean b = dicomFile(info, dcmFile);
//        System.out.println("创建 dicom  " + b+"  "+dcmFile.getAbsolutePath());

       /* DicomInfo dicomInfo = readDicomFile(new File("D:/abc/dicom/gain/1630035152322.dcm"));
//
        FileOutputStream outputStream = new FileOutputStream("D:/abc/dicom/gain/1630035152322.dat");
        byte[] bytes = turn(dicomInfo.bytes, "sHo&30(*uY");

        outputStream.write(bytes);
        outputStream.close();

        System.out.println("dicomInfo: " + dicomInfo.toString());
        int[][] ints = turnInts(dicomInfo);
        System.out.println("长度: " + ints[0].length);
        System.out.println("1导:");
        System.out.println(Arrays.toString(ints[0]));
        System.out.println("2导:");
        System.out.println(Arrays.toString(ints[1]));*/

    }

    public static byte[] turn(byte[] bytes, String s) {
        int length = bytes.length;
        int size = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) (bytes[i] ^ Integer.valueOf(chars[i % size]));
        }
        return bytes;
    }


}
