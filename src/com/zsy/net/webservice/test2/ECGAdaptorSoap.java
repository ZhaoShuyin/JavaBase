
package com.zsy.net.webservice.test2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ECGAdaptorSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ECGAdaptorSoap {


    /**
     * ��ѯ�ĵ�����
     * 
     * @param acondition
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetWorkList", action = "http://tempuri.org/GetWorkList")
    @WebResult(name = "GetWorkListResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetWorkList", targetNamespace = "http://tempuri.org/", className = "com.zsy.webservice.test2.GetWorkList")
    @ResponseWrapper(localName = "GetWorkListResponse", targetNamespace = "http://tempuri.org/", className = "com.zsy.webservice.test2.GetWorkListResponse")
    public String getWorkList(
        @WebParam(name = "Acondition", targetNamespace = "http://tempuri.org/")
        String acondition);

    /**
     * ���ô���״̬
     * 
     * @param aorderList
     * @param aisSend
     * @return
     *     returns int
     */
    @WebMethod(operationName = "SetSendState", action = "http://tempuri.org/SetSendState")
    @WebResult(name = "SetSendStateResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "SetSendState", targetNamespace = "http://tempuri.org/", className = "com.zsy.webservice.test2.SetSendState")
    @ResponseWrapper(localName = "SetSendStateResponse", targetNamespace = "http://tempuri.org/", className = "com.zsy.webservice.test2.SetSendStateResponse")
    public int setSendState(
        @WebParam(name = "AorderList", targetNamespace = "http://tempuri.org/")
        String aorderList,
        @WebParam(name = "AisSend", targetNamespace = "http://tempuri.org/")
        boolean aisSend);

}
