//主要写战斗过程中弹出的消息
package com.ncist.Pokemon.GameMechanism;

import com.ncist.Pokemon.Message.WelcomeMenu;
import com.ncist.Pokemon.Spirit.Chinese_English;
import com.ncist.Pokemon.Spirit.*;//导入所有精灵
import org.dom4j.DocumentException;

import static com.ncist.Pokemon.Message.WelcomeMenu.input;

import java.util.Scanner;

public class FightMechanism {
    static int round = 3;      //回合数，回合数为0时，游戏结束
    static int i = 0;          //计数器

    public static void Round_system() throws DocumentException {

        int choiceTrick = 0;          //选择的技能
        int randNum = 0;    //随机数，用于查看是先手还是后手
        SpiritRoot callSpirit1 = null;   //召唤的精灵1
        SpiritRoot callSpirit2 = null;   //召唤的精灵2


        System.out.println("可以出战的精灵:");//选择双方对战的精灵
        //输出你所拥有的精灵 (待完成)
        SpiritArray[] callSpirit;
        callSpirit = WelcomeMenu.choice_fight_spirit();
        while (callSpirit == null){
            System.out.println("不存在的精灵！请重新输入");
            callSpirit = WelcomeMenu.choice_fight_spirit();
        }

        System.out.println(String.format("我选择出战{%s}",callSpirit[0].chineseName));

        //召唤所选择的精灵：
        callSpirit1 = choice_spirit(callSpirit[0].englishName);
        if (callSpirit1 == null) {
            //召唤失败后的代码 (待完成)
        }
        assert callSpirit1 != null;

        //机器人随机选择某个精灵与其对战 (待完成)

        System.out.println("黑暗势力选择出战{小火龙}");//(测试用)
        callSpirit2 = choice_spirit("Charmander");
        if (callSpirit2 == null) {
            //召唤失败后的代码 (待完成)
        }
        assert callSpirit2 != null;


        //随机选择出手时机,是先手还是后手
        randNum = 1;//(int) (Math.random() * 2);
//        if (randNum == 1) {
//            System.out.println("\n你是先手");
//        } else {
//            System.out.println("\n你是后手");
//        }

        while (round != 0) {
            i++;

            System.out.println(String.format("\n-------------第%d回合开始----------------\n", i));

            //先手逻辑
            if (randNum == 1) {
                System.out.println("~~~~~~我方的回合：~~~~~~");

                callSpirit1.show_state(callSpirit2);

                //列出该精灵所拥有的技能 (待完成 UI)
                callSpirit1.show_tricks();

                //使用你选择的技能
                System.out.println("请选择你想要使用的技能：");
                choiceTrick = input.nextInt();
                callSpirit1.choice_trick(choiceTrick, callSpirit1, callSpirit2);
                callSpirit1.show_state(callSpirit2);

                //暂停键
                WelcomeMenu.stop_key();


                //每个回合都先判断一次，如果对战双方某个精灵没血了，说明对方胜利，并退出回合
                if (judge_over(callSpirit1, callSpirit2) == 1) {
                    break;
                }

                System.out.println("~~~~~~对手的回合：~~~~~~");
                //对方精灵开始使用招式:

                //因为是机器人，所以随机抽一个技能来使用
                callSpirit1.choice_he_trick(callSpirit2, callSpirit1);

                //造成了xx效果 (待完成)
                callSpirit1.show_state(callSpirit2);

                if (judge_over(callSpirit1, callSpirit2) == 1) {
                    break;
                }

                //暂停键
                WelcomeMenu.stop_key();

            } else    //后手逻辑
            {
                System.out.println("后手逻辑待完工");

            }


            System.out.println(String.format("\n-------------第%d回合结束----------------\n", i));
            round--;
        }

    }

    //判断游戏是否结束
    public static int judge_over(SpiritRoot callSpirit1, SpiritRoot callSpirit2) {
        if (callSpirit1.judge_blood() <= 0) {
            System.out.println("我方精灵血量不足，游戏失败!\n-----------------------------------\n");
            //暂停键
            WelcomeMenu.stop_key();
            return 1;
        } else {
            if (callSpirit2.judge_blood() <= 0) {
                System.out.println("对方精灵血量不足，游戏胜利!\n-----------------------------------\n");
                //暂停键
                WelcomeMenu.stop_key();
                return 1;
            }
        }
        round++;
        return 0;
    }

    //召唤某个精灵
    public static SpiritRoot choice_spirit(String spiritName) {
        //选择一个精灵，把名字导进来
        switch (spiritName) {
            case "Bulbasaur":
                Bulbasaur Bulbasaur1 = new Bulbasaur();
                System.out.println("成功召唤妙蛙种子！");//召唤精灵
                //暂停键
                WelcomeMenu.stop_key();
                return Bulbasaur1;
            case "Charmander":
                Charmander Charmander1 = new Charmander();
                System.out.println("成功召唤小火龙！");//召唤精灵
                //暂停键
                WelcomeMenu.stop_key();
                return Charmander1;
            default:
                System.out.println("没找到你想要召唤的精灵");
        }
        return null;
    }
}
