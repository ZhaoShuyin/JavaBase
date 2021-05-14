
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
 *         &lt;element name="AorderList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AisSend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "aorderList",
    "aisSend"
})
@XmlRootElement(name = "SetSendState")
public class SetSendState {

    @XmlElement(name = "AorderList")
    protected String aorderList;
    @XmlElement(name = "AisSend")
    protected boolean aisSend;

    /**
     * ��ȡaorderList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAorderList() {
        return aorderList;
    }

    /**
     * ����aorderList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAorderList(String value) {
        this.aorderList = value;
    }

    /**
     * ��ȡaisSend���Ե�ֵ��
     * 
     */
    public boolean isAisSend() {
        return aisSend;
    }

    /**
     * ����aisSend���Ե�ֵ��
     * 
     */
    public void setAisSend(boolean value) {
        this.aisSend = value;
    }

}
