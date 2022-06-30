package com.ncist.Pokemon.Test;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Test_test {

    public static void main(String[] args) {
        save_info_Xml("D:\\Coding\\C\\Pokemon\\XML\\test.xml", "毕载小子", "1", "100", "1", "妙蛙种子", "1", "300", "17");
    }

    public static void save_info_Xml(String path, String PlayerNameText, String PlayerNumText, String PlayerMoneyText, String spiritNum, String nameText, String gradeText, String bloodText, String attackPowerText) {
//        Document：表示xml文档信息，是一个树形结构。
//        Eelment：表示xml文件的元素节点，主要是提供一些元素的操作方法。
//        Attribute：表示元素结点中的属性，可以自定义。

        Document document = DocumentHelper.createDocument();
        //创建根元素
        Element root = document.addElement("Players");

        //创建子元素
        Element PlayerName = root.addElement("PlayerName");
        String PlayerNameText1 = PlayerNameText;
        PlayerName.setText(PlayerNameText1);
        Element PlayerNum = root.addElement("PlayerNum");
        String PlayerNumText1 = PlayerNumText;
        PlayerNum.setText(PlayerNumText1);
        Element PlayerMoney = root.addElement("PlayerMoney");
        String PlayerMoneyText1 = PlayerMoneyText;
        PlayerMoney.setText(PlayerMoneyText1);
        Element SpiritNums = root.addElement("SpiritNums");


        Element Spirit = SpiritNums.addElement("Spirit");
        Element SpiritElement = Spirit.addAttribute("sn", spiritNum);

        Element name = SpiritElement.addElement("name");
        name.setText(nameText);
        Element grade = SpiritElement.addElement("grade");
        grade.setText(gradeText);
        Element blood = SpiritElement.addElement("blood");
        blood.setText(bloodText);
        Element attackPower = SpiritElement.addElement("attackPower");
        attackPower.setText(attackPowerText);


        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置xml文档的编码为utf-8
        format.setEncoding("utf-8");
        Writer out;
        try {
            //创建一个输出流对象
            out = new FileWriter(path);
            //创建一个dom4j创建xml的对象
            XMLWriter writer = new XMLWriter(out, format);
            //调用write方法将doc文档写到指定路径
            writer.write(document);
            writer.close();
            System.out.print("生成XML文件成功");
        } catch (IOException e) {
            System.out.print("生成XML文件失败");
            e.printStackTrace();
        }

    }

}


