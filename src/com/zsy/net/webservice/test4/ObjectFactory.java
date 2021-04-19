
package com.zsy.net.webservice.test4;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.zsy.webservice.test4 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AAA_QNAME = new QName("http://example/", "AAA");
    private final static QName _GetMobileCodeInfoResponse_QNAME = new QName("http://example/", "getMobileCodeInfoResponse");
    private final static QName _AAAResponse_QNAME = new QName("http://example/", "AAAResponse");
    private final static QName _GetMobileCodeInfo_QNAME = new QName("http://example/", "getMobileCodeInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.zsy.webservice.test4
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AAA }
     * 
     */
    public AAA createAAA() {
        return new AAA();
    }

    /**
     * Create an instance of {@link AAAResponse }
     * 
     */
    public AAAResponse createAAAResponse() {
        return new AAAResponse();
    }

    /**
     * Create an instance of {@link GetMobileCodeInfo }
     * 
     */
    public GetMobileCodeInfo createGetMobileCodeInfo() {
        return new GetMobileCodeInfo();
    }

    /**
     * Create an instance of {@link GetMobileCodeInfoResponse }
     * 
     */
    public GetMobileCodeInfoResponse createGetMobileCodeInfoResponse() {
        return new GetMobileCodeInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AAA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "AAA")
    public JAXBElement<AAA> createAAA(AAA value) {
        return new JAXBElement<AAA>(_AAA_QNAME, AAA.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMobileCodeInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getMobileCodeInfoResponse")
    public JAXBElement<GetMobileCodeInfoResponse> createGetMobileCodeInfoResponse(GetMobileCodeInfoResponse value) {
        return new JAXBElement<GetMobileCodeInfoResponse>(_GetMobileCodeInfoResponse_QNAME, GetMobileCodeInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AAAResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "AAAResponse")
    public JAXBElement<AAAResponse> createAAAResponse(AAAResponse value) {
        return new JAXBElement<AAAResponse>(_AAAResponse_QNAME, AAAResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMobileCodeInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getMobileCodeInfo")
    public JAXBElement<GetMobileCodeInfo> createGetMobileCodeInfo(GetMobileCodeInfo value) {
        return new JAXBElement<GetMobileCodeInfo>(_GetMobileCodeInfo_QNAME, GetMobileCodeInfo.class, null, value);
    }

}
