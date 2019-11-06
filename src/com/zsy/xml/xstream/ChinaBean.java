package com.zsy.xml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("china")//类注解(必须写)
public class ChinaBean {

    @XStreamAsAttribute() @XStreamAlias("dn") //属性注解
    public String dn;
    @XStreamImplicit(itemFieldName = "city")//节点注解(必须写)
    public List<CityBean> city;
    //省略dn 和 city 的 getter 和 setter 方法

    public static class CityBean {
        //值不用注解
        public int year;
        public String name;
        public int age;
        @XStreamAsAttribute() @XStreamAlias("prov")//属性是必须用注解声明的
        public String prov;

      //省略getter 和setter 方法
    }

}