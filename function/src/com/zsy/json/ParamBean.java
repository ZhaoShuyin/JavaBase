package com.zsy.json;

import java.io.Serializable;
import java.util.List;

/**
 * @Title 报告/会诊 参数
 * @date 2020/3/16
 * @autor Zsy
 */
public class ParamBean implements Serializable {

    private static final long serialVersionUID = -7591725449122839295L;
    public int code;
    public ParamEntity data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ParamEntity getData() {
        return data;
    }

    public void setData(ParamEntity data) {
        this.data = data;
    }
}
