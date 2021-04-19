package com.zsy.function.xml.xstream;

import java.util.List;

/**
 * @Title com.zsy.test
 * @date 2019/11/4
 * @autor Zsy
 */
public class XmlBean {

    private PersonsBean persons;

    public PersonsBean getPersons() {
        return persons;
    }

    public void setPersons(PersonsBean persons) {
        this.persons = persons;
    }

    public static class PersonsBean {
        /**
         * name : 张三
         * id : 111
         * age : 33
         */

        private List<PersonBean> person;

        public List<PersonBean> getPerson() {
            return person;
        }

        public void setPerson(List<PersonBean> person) {
            this.person = person;
        }

        public static class PersonBean {
            private String name;
            private int id;
            private int age;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }
    }
}
