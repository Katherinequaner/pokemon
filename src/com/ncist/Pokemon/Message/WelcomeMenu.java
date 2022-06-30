//欢迎菜单，游戏提示等消息都在这写
package com.ncist.Pokemon.Message;

import com.ncist.Pokemon.PlayerInfo.ShowInfo;
import com.ncist.Pokemon.Spirit.SpiritArray;
import com.ncist.Pokemon.Spirit.Chinese_English;
import org.dom4j.DocumentException;

import java.util.Scanner;

public class WelcomeMenu {
    public static Scanner input = new Scanner(System.in); //定义输入的内容

    //展示可以出战的精灵,返回值：精灵英文名字，精灵中文名字
    public static SpiritArray[] choice_fight_spirit() throws DocumentException {
        SpiritArray[] spiritArray1;

        //展示可以出战的精灵编号
        spiritArray1 = ShowInfo.show_fight_spirit();

        System.out.println("请选择您想要出战的精灵：");
        int choice_spirit = input.nextInt();
        int i = 0;
        while (i < spiritArray1.length) {
            if (spiritArray1[i].spiritNum == choice_spirit){
                return spiritArray1;
            }
            i++;
        }

        return null;
    }

    //暂停键
    public static void stop_key() {
        System.out.println("请按回车键继续");
        input.nextLine();
    }

    //游戏版权等信息
    public static void banner() {
        System.out.println("\n-------------欢迎来到精灵宝可梦游戏!----------------\n-------------版权所有：摆烂小分队----------------");
    }

    //游戏菜单
    public static void menu() {
        System.out.println("\n游戏菜单：\n1：注册账号\n2：登录账号\n3：查看人物信息\n4：随机挑战模式\n5：测试用（上线前删除）");
    }
}

