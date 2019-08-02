package com.zsy.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 设计模式之原型模式
 * 介绍:类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等，通过原型拷贝避免这些消耗；
 * ,   通过 new 产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式；
 * ,   一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式拷贝多个对象供调用者使用，即保护性拷贝。
 * 例子: Intent intent2 = (Intent) intent.clone()   <深clone>
 * @author Zsy
 * @date 2019/8/2 11:16
 */
public class K_Prototype {
    public static void main(String[] args) {
        WordDocument doc1 = new WordDocument(); //构造函数
        doc1.setText("文档1");            //
        doc1.addImage("图片1");            //
        doc1.showDocument();

        WordDocument doc2 = doc1.clone();       //clone方法不会执行构造函数
        doc2.setText("文档2");                  //
        doc2.addImage("图片2");         //clone方法中只是简单的进行浅拷贝，引用类型的新对象doc2的mImages只是单纯的指向了this.mImages引用
        doc2.showDocument();

        doc1.showDocument();
        System.out.println("doc1==" + doc1.toString() + " doc2" + doc2.toString());


    }
}


class WordDocument implements Cloneable {
    private String mText;                                       //文本
    private ArrayList<String> mImages = new ArrayList<>();           //文本

    public WordDocument() {
        System.out.println("----------- WordDocument构造函数  -----------");
    }

    //克隆对象
    @Override
    protected WordDocument clone() {
        try {
            WordDocument doc = (WordDocument) super.clone();
            doc.mText = this.mText;
//            doc.mImages = this.mImages;                           //浅clone
            doc.mImages = (ArrayList<String>) this.mImages.clone(); //深clone
            return doc;
        } catch (Exception e) {
        }
        return null;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public List<String> getmImages() {
        return mImages;
    }

    public void addImage(String mImage) {
        this.mImages.add(mImage);
    }

    public void showDocument() {
        System.out.println("Text : " + mText + " mImages== " + mImages.toString());
    }
}

