package com.zsy.net.webservice.test;

/**
 * @Title com.zsy.webservice.test
 */

class Client {

    static String acontion = "{\"?xml\":{\"@version\":\"1.0\",\"@encoding\":\"utf-8\"},\"WorkList\":{\"@xmlns:xsi\":\"http://www.w3.org/2001/XMLSchema-instance\",\"@xmlns:xsd\":\"http://www.w3.org/2001/XMLSchema\",\"ErrorCode\":null,\"Error\":null,\"Item\":{\"PatientName\":null,\"PatientID\":null,\"PatientBirthDate\":null,\"PatientSex\":null,\"PatientHeight\":null,\"PatientWeight\":null,\"StudyInstanceUID\":null,\"AccessionNumber\":null,\"AdmissionID\":null,\"RequestedProcedureID\":null,\"SPSStartDateTime\":null,\"SPSLocation\":null,\"DeviceID\":\"1000\"}}}";

    public static void main(String[] args) {
        ECGAdaptor ecgAdaptor = new ECGAdaptor();
        ECGAdaptorSoap ecgAdaptorSoap = ecgAdaptor.getECGAdaptorSoap();
        String workList = ecgAdaptorSoap.getWorkList(acontion);
        System.out.println(workList);


//        ObjectFactory factory = new ObjectFactory();
//        GetWorkList getWorkList = factory.createGetWorkList();
//        getWorkList.setAcondition(acontion);



    }

}
