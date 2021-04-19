package com.zsy.function.xml.parse;

//import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Title com.pengyang.ecg.bean.ecg
 * @date 2019/11/4
 * @autor Zsy
 */

public class Person {


    /**
     * persons : {"person":[{"-id":"111","name":"张三","age":"33"},{"-id":"222","name":"李四","age":"44"}]}
     */

    private PersonsBean persons;

    public PersonsBean getPersons() {
        return persons;
    }

    public void setPersons(PersonsBean persons) {
        this.persons = persons;
    }

    public static class PersonsBean {
        private List<PersonBean> person;

        public List<PersonBean> getPerson() {
            return person;
        }

        public void setPerson(List<PersonBean> person) {
            this.person = person;
        }

        public static class PersonBean {
            /**
             * -id : 111
             * name : 张三
             * age : 33
             */

//            @SerializedName("-id")
            private String id;
            private String name;
            private String age;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }


            @Override
            public String toString() {
                return "PersonBean{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", age='" + age + '\'' +
                        '}';
            }
        }
    }


    @Override
    public String toString() {
        return "Person{" +
                "persons=" + persons +
                '}';
    }
}
