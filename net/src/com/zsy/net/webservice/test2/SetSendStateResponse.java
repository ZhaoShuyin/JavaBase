
package com.zsy.net.webservice.test2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SetSendStateResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "setSendStateResult"
})
@XmlRootElement(name = "SetSendStateResponse")
public class SetSendStateResponse {

    @XmlElement(name = "SetSendStateResult")
    protected int setSendStateResult;

    /**
     * ��ȡsetSendStateResult���Ե�ֵ��
     * 
     */
    public int getSetSendStateResult() {
        return setSendStateResult;
    }

    /**
     * ����setSendStateResult���Ե�ֵ��
     * 
     */
    public void setSetSendStateResult(int value) {
        this.setSendStateResult = value;
    }

}
