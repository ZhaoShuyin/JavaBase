package test.test;

/**
 * @Title com.zsy.test
 * @date 2019/12/11
 * @autor Zsy
 */

class Test5 {

    public static void main(String[] args) {
//        System.out.println(paramCreatDefault("张三", "男", "11岁", "2019-01-01",
////                "科室1", "来源1",
////                "门诊号1", "住院号1", "病区1", "床号1",
////                "检查项目", "ECG"));

        /*Student student = new Student("张三", 11);
        Class aClass = student.getClass();
        Field[] fields = aClass.getFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String name = field.getName();
            System.out.println(name);
            try {
                Object o = field.get(student);
                System.out.println(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();

            }
        }*/


        /*String abc = "";
        switch (abc){
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                System.out.println("3");
                break;
                default:
                    System.out.println("未知");
        }*/

        String abc = "123";
        String[] split = abc.split(",");
        System.out.println("("+split[0]+") =========== ("+split[1]+")");
    }

    public static class Student {
        public String name;
        public int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


    /**
     * 快速创建使用数据
     */
    public static String paramCreatDefault(String name, String sex, String age, String brithday,           //姓名,性别,年龄,生日
                                           String department, String resourceType,                         //科室,患者来源
                                           String outPatient, String inHospital, String ward, String bedin,//门诊号,住院号,病区(房间号),床号
                                           String checkOption, String checkType) {                         //检查项目,检查类型(ECG)
        StringBuilder builder = new StringBuilder();
        builder.append("{\"Token\":null,\"Command\":\"E00051\",\"Param\":{\"Type\":\"ADD\",");
        builder.append("\"hospital_Name\":\"" + "医院" + "\"},");      //医院
        builder.append("\"RequestModel\":{");
        builder.append("\"NAME\":\"" + name + "\",");               //姓名/英文名
        builder.append("\"NAME_PHONETIC\":\"" + PinYinUtil.getSelling(name, false) + "\",");
        builder.append("\"SEX\":\"" + sex + "\",");                                     //性别
        builder.append("\"EXAM_AGE\":\"" + age + "\",");                                //年龄
        builder.append("\"BIRTHDAY\":\"" + brithday + "\",");                           //生日
        builder.append("\"REQ_DEPT_CODE\":\"" + department + "\",");                    //科室
        builder.append("\"PATIENT_SOURCE\":\"" + resourceType + "\",");                 //患者来源
        builder.append("\"ITEM_NAME\":\"" + checkOption + "\",");                       //检查项目
        builder.append("\"EXAM_CLASS\":\"" + checkType + "\",");                        //检查类型
        builder.append("\"REQ_DOCTOR_CODE\":\"" + "张医生" + "\",");     //申请医生
        builder.append("\"CREATE_USER_UID \":\"" + "登录账户" + "\",");       //申请账号
        builder.append("\"REQ_DATETIME\":\"" + "1999-09-09" + "\",");             //创建日期
        builder.append("\"PACS_UPLOAD_STATUS \":\"0\",");                               //初始化检查PACS图像未上传
        builder.append("\"RIS_UPLOAD_STATUS\":\"0\",");                                 //初始化检查RIS图像未上传
        builder.append("\"IMAGE_STATUS\":\"0\",");                                      //初始化检查为无图
        builder.append("\"EXAM_STATUS\":\"0\",");                                       //检查状态
        builder.append("\"DELETE_FLAG\":\"0\",");                                       //删除状态
        builder.append("\"PRINT_NUM\":\"0\"");                                          //Print次数
        if (outPatient != null) {
            builder.append(",\"OUTP_NO\":\"" + outPatient + "\"");                       //门诊号
        }
        if (inHospital != null) {
            builder.append(",\"INP_NO\":\"" + inHospital + "\"");                        //住院号
        }
        if (ward != null) {
            builder.append(",\"ROOM_NO\":\"" + ward + "\"");                              //病区/房间号
        }
        if (bedin != null) {
            builder.append(",\"BED_NO\":\"" + bedin + "\"");                              //床号
        }
        builder.append("}}");                                                           //结尾
        return builder.toString();
    }
}
