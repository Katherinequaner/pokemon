package com.ncist.Pokemon.PlayerInfo;

import com.ncist.Pokemon.Message.WelcomeMenu;
import com.ncist.Pokemon.Spirit.SpiritArray;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static com.ncist.Pokemon.Main.Main.useraccount;

public class ShowInfo {

    // 核心代码，详见：https://www.cnblogs.com/xiqingbo/p/java-09.html
    //读取XML文件，输入应该是一个xml路径 (待完成)

    //将个人信息存入xml
    public static void save_info_Xml(String path, String PlayerNameText, String PlayerNumText, String PlayerMoneyText, String fightNum, String spiritNum, String nameText, String englishText,String gradeText, String bloodText, String attackPowerText) {
//        Document：表示xml文档信息，是一个树形结构。
//        Eelment：表示xml文件的元素节点，主要是提供一些元素的操作方法。
//        Attribute：表示元素结点中的属性，可以自定义。

        Document document = DocumentHelper.createDocument();
        //创建根元素
        Element root = document.addElement("Players");

        //创建子元素
        Element PlayerName = root.addElement("PlayerName");
        PlayerName.setText(PlayerNameText);
        Element PlayerNum = root.addElement("PlayerNum");
        PlayerNum.setText(PlayerNumText);
        Element PlayerMoney = root.addElement("PlayerMoney");
        PlayerMoney.setText(PlayerMoneyText);
        Element FightSpirit = root.addElement("FightSpirit");
        Element Fight = FightSpirit.addElement("Fight");
        Element FightNum = Fight.addAttribute("sn", fightNum);
        FightNum.setText(fightNum);

        Element SpiritNums = root.addElement("SpiritNums");

        Element Spirit = SpiritNums.addElement("Spirit");
        Element SpiritElement = Spirit.addAttribute("sn", spiritNum);

        Element name = SpiritElement.addElement("name");
        name.setText(nameText);
        Element english = SpiritElement.addElement("english");
        english.setText(englishText);
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

    //展示要出战的精灵,,返回值：返回一个二维数组，数组中有 精灵编号 和对应的 精灵名字
    public static SpiritArray[] show_fight_spirit() throws DocumentException {
        //用于返回二维数组
        SpiritArray[] spiritArray1 = new SpiritArray[4];
        for(int i=0;i<4;i++){
            spiritArray1[i] = new SpiritArray();
        }

        // 创建SAXReader实例
        SAXReader reader = new SAXReader();

        //路径写死，比如说D:\\Pokemon\\XML\\%d.xml
        String fileName = String.format("D:\\Coding\\C\\Pokemon\\XML\\%d.xml", useraccount);
        // read()读取指定的XML文档并形成DOM树
        Document document = reader.read(new File(fileName));
        // getRootElement()获取根节点
        Element rootEle = document.getRootElement();
        // elements()获取根节点的子节点
        List<Element> playersEles = rootEle.elements();


        //将FightSpirit节点作为根节点
        Element Fight = playersEles.get(3);
        List<Element> FightEles = Fight.elements();

        String[] stringTextArray1 = new String[4];
        int i = 0;
        //进入FightSpirit节点,并遍历FightSpirit节点
        for (Element FightElement : FightEles) {
            if (i == 4) {
                break;
            }
            //获取FightSpirit节点的信息,即获取要出战精灵的编号
            stringTextArray1[i] = (FightElement.attributeValue("sn"));
            i++;
        }

        i = 0;
        while (i != 4) {
            if (stringTextArray1[i] != null) {
                System.out.printf(stringTextArray1[i] + " ");
            }
            i++;
        }
        System.out.println();

        //选择SpiritNums节点
        Element player = playersEles.get(4);
        //将SpiritNums节点作为根节点
        List<Element> SpiritEles = player.elements();
        //进入Spirit节点,并遍历Spirit节点
        i = 0;
        int snValue, gradeText, bloodText, attackPowerText;
        String nameText,englishName;
        for (; i < stringTextArray1.length; i++) {
            for (Element SpiritElement : SpiritEles) {
                //获取Spirit节点的相关信息,如名字，等级等
                snValue = Integer.parseInt(SpiritElement.attributeValue("sn"));
                if(stringTextArray1[i]==null){
                    break;
                }
                if ( i < stringTextArray1.length - 1 && Integer.parseInt(stringTextArray1[i]) == snValue) {
                    nameText = SpiritElement.elementText("name");
                    englishName = SpiritElement.elementText("english");
                    gradeText = Integer.parseInt(SpiritElement.elementText("grade"));
                    bloodText = Integer.parseInt(SpiritElement.elementText("blood"));
                    attackPowerText = Integer.parseInt(SpiritElement.elementText("attackPower"));
                    spiritArray1[i].spiritNum =  snValue;
                    spiritArray1[i].englishName =  englishName;
                    spiritArray1[i].chineseName =  nameText;
                    System.out.println(String.format("可出战的精灵编号：%d\t可出战的精灵名字：%s\n可出战的精灵等级：%d\t可出战的精灵血量：%d\t可出战的精灵攻击力：%d\n", snValue, nameText, gradeText, bloodText, attackPowerText));
                    break;
                }
            }
        }

        return spiritArray1;
    }

    //展示输入的玩家编号的用户信息
    public static void main_for_showinfo(int useraccount) throws DocumentException {
        // 创建SAXReader实例
        SAXReader reader = new SAXReader();

        //根据useraccount来从数据库中查找该用户所对应的 xml文件路径，然后下面再导入并解析输出 (待完成)
        //String filePath = DbsConnect.select_user_xml(useraccount);

        //路径写死，比如说D:\\Pokemon\\XML\\%d.xml
        String fileName = String.format("D:\\Coding\\C\\Pokemon\\XML\\%d.xml", useraccount);
        // read()读取指定的XML文档并形成DOM树
        Document document = reader.read(new File(fileName));
        // getRootElement()获取根节点
        Element rootEle = document.getRootElement();
        // elements()获取根节点的子节点
        List<Element> playersEles = rootEle.elements();

        System.out.println("~~~~~~玩家信息：~~~~~~");
        String[] stringTextArray = new String[4];
        String[] stringTextArray1 = new String[4];

        //将子节点当做根节点,重复上诉过程
        for (int i = 0; i < 3; i++) {
            Element player = playersEles.get(i);
            String stringText = player.getText();
            stringTextArray[i] = stringText;
        }

        //将FightSpirit节点作为根节点
        Element Fight = playersEles.get(3);
        List<Element> FightEles = Fight.elements();
        int i = 0;
        //进入FightSpirit节点,并遍历FightSpirit节点
        for (Element FightElement : FightEles) {
            if (i == 4) {
                break;
            }
            //获取FightSpirit节点的信息,即获取要出战精灵的编号
            stringTextArray1[i] = (FightElement.attributeValue("sn"));
            i++;
        }

        //后期 用户ID 和 账户余额 需要强制类型转换
        System.out.printf(String.format("用户名：%s\t\t用户ID：%s\t\t账户余额：%s\t\t已选择要出战的精灵：", stringTextArray[0], stringTextArray[1], stringTextArray[2]));

        i = 0;
        while (i != 4) {
            if (stringTextArray1[i] != null) {
                System.out.printf(stringTextArray1[i] + " ");
            }
            i++;
        }
        System.out.println();

        //选择SpiritNums节点
        Element player = playersEles.get(4);
        //将SpiritNums节点作为根节点
        List<Element> SpiritEles = player.elements();
        //进入Spirit节点,并遍历Spirit节点
        i = 0;
        int snValue, gradeText, bloodText, attackPowerText;
        System.out.println("~~~~~~第1页~~~~~~");
        for (Element SpiritElement : SpiritEles) {
            //获取Spirit节点的相关信息,如名字，等级等
            snValue = Integer.parseInt(SpiritElement.attributeValue("sn"));
            String nameText = SpiritElement.elementText("name");
            gradeText = Integer.parseInt(SpiritElement.elementText("grade"));
            bloodText = Integer.parseInt(SpiritElement.elementText("blood"));
            attackPowerText = Integer.parseInt(SpiritElement.elementText("attackPower"));
            System.out.println(String.format("所拥有的精灵编号：%d\t所拥有的精灵名字：%s\n所拥有的精灵等级：%d\t所拥有的精灵血量：%d\t所拥有的精灵攻击力：%d\n", snValue, nameText, gradeText, bloodText, attackPowerText));
            i++;
            if (i % 4 == 0 && i != 4 && i != 0) { //四个为一组输出
                //暂停键
                WelcomeMenu.stop_key();
                System.out.println(String.format("~~~~~~第%d页~~~~~~", (i / 4) + 1));
            }
        }
        //暂停键
        WelcomeMenu.stop_key();
    }
}

