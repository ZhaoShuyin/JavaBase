
package com.zsy.net.webservice.test2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ECGAdaptorHttpGet", targetNamespace = "http://tempuri.org/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ECGAdaptorHttpGet {


    /**
     * ��ѯ�ĵ�����
     * 
     * @param acondition
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetWorkList")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String getWorkList(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "Acondition")
        String acondition);

    /**
     * ���ô���״̬
     * 
     * @param aorderList
     * @param aisSend
     * @return
     *     returns int
     */
    @WebMethod(operationName = "SetSendState")
    @WebResult(name = "int", targetNamespace = "http://tempuri.org/", partName = "Body")
    public int setSendState(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AorderList")
        String aorderList,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AisSend")
        String aisSend);

}
