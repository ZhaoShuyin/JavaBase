package test.util.test;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;

import java.io.File;
import java.io.IOException;

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
            attributes.setString(tag(0x003a, 0x0005), VR.US, info.leadsNumber);            //0x003a, 0x0005 导联数量
            attributes.setInt(tag(0x003a, 0x0010), VR.UL, info.dataLength);                //0x003a, 0x0010 数据个数(单个导联)
            attributes.setInt(tag(0x003a, 0x001a), VR.UL, info.sampling);                  //0x003a, 0x001a  数据采样率(每秒数据个数)

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
                sData.add(a);                                            //添加三级数据
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
            String string = dataset.getString(tag(0x003A, 0x0005));
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
