package test.util.dicom;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title com.zsy.app2.dicom
 * @date 2020/5/11
 * @autor Zsy
 */
public class DicomInfo {

    /**
     * 导联名称
     */
    public String[] leads = new String[]{"I", "II", "III", "avR", "avL", "avF", "V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8", "V9", "V3R", "V4R", "V5R"};

    /**
     * 每个导联增益
     */
    public float[] leadsGain = new float[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
    /**
     * 每个导联
     */
    public int[] leadsW = new int[]{16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16};

    public byte[] bytes;              //dicom文件数据
    public String leadsNumber = "12"; //导联个数
    public int dataLength;            //数据长度(单个导联)
    public int sampling = 500;        //采样率(每秒数据个数)

    public String date;               //采集时间

    public int model;                 //导联模式/导联个数

    public DicomInfo(byte[] bytes, int model, float gain) {
        this.bytes = bytes;
        this.dataLength = bytes.length / model / 2;       //数据长度
        this.date = getDateString();
        this.model = model;
        this.leadsNumber = String.valueOf(model);
        String[] leads = new String[model];
        float[] leadsGain = new float[model];
        for (int i = 0; i < model; i++) {
            leads[i] = this.leads[i];
            leadsGain[i] = this.leadsGain[i] * gain;
        }
        this.leads = leads;
        this.leadsGain = leadsGain;
    }

    private String getDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = formatter.format(new Date());
        return format;
    }

}
