package com.ncist.Pokemon.Test;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//import org.junit.Test;

import java.io.File;
import java.util.List;

 class test {
    private String sn;
    private String name;
    private String author;
    private Double price;

    public test() {
    }


    public   test(String sn, String name, String author, Double price) {
        this.sn = sn;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}


/**
 * @PackageName: pers.xiqingbo.pojo
 * @ClassName: Dom4jTest
 * @Description: 使用dom4j解析xml文档并遍历xml节点
 * @Author: Schieber
 * @Date: 2021/1/13 上午 7:45
 */
public class Test_test {
   // @Test
    public static void read() throws DocumentException {
        // 创建SAXReader实例
        SAXReader reader = new SAXReader();
        // read()读取指定的XML文档并形成DOM树
        Document document = reader.read(new File("D:\\Coding\\C\\Pokemon\\1.xml"));
        // getRootElement()获取根节点
        Element rootEle = document.getRootElement();
        // elements()获取根节点的子节点
        List<Element> bookEles = rootEle.elements();
        // 遍历子节点
        for (Element book : bookEles
        ) {
            // element()获取子节点指定的子元素
            Element nameElement = book.element("name");
            // getText()获取子元素的文本内容
            String nameText = nameElement.getText();
            // elementText()直接获取元素的文本内容
            String authorText = book.elementText("author");
            String priceText = book.elementText("price");
            // attributeValue()直接获取元素的属性值
            String snValue = book.attributeValue("sn");
            System.out.println(new test(snValue, nameText, authorText, Double.parseDouble(priceText)));
        }
    }
}
