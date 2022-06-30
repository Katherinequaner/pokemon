//程序的主方法,程序从这里直接启动
package com.ncist.Pokemon.Main;

import com.ncist.Pokemon.Dbs.DbsConnect;
import com.ncist.Pokemon.GameMechanism.Mechanism;
import com.ncist.Pokemon.Login.CheckLogin;
import com.ncist.Pokemon.Message.WelcomeMenu;
import com.ncist.Pokemon.PlayerInfo.ShowInfo;
import com.ncist.Pokemon.Test.Test_test;
import org.dom4j.DocumentException;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    static int flag = 0;       //flag为1时，说明账户登录成功,只有账户登录成功后才能游玩
    public static int useraccount = 0;     //玩家账户

    public static void main(String[] args) throws SQLException, DocumentException {
        int dochoice = 0;

        WelcomeMenu.banner();
        while (1 == 1) {
            WelcomeMenu.menu();//运行游戏菜单，选择游玩的模式等

            System.out.print("请输入你想要游玩的模式：");
            Scanner input = new Scanner(System.in);
            dochoice = input.nextInt();
            while (dochoice < 1 || dochoice > 5) {
                WelcomeMenu.banner();
                System.out.println("请重新选择游玩的选项！并无该选项！");
                WelcomeMenu.menu();
                System.out.print("请输入你想要游玩的模式：");
                dochoice = input.nextInt();
            }

            //进入相对应的模式
            switch (dochoice) {
                case 1:
                    if (flag != 1) {
                        CheckLogin.sign_account();
                    } else
                        System.out.println("你已经登录了账户，请先退出当前账号再创建新的账号");
                    break;
                case 2:
                    flag = CheckLogin.main_for_check(flag);
                    break;
                case 3:
                    flag = CheckLogin.main_for_check(flag);
                    if (flag != 1) {
                        System.out.println("查看人物信息前，请先登录账号！");
                        break;
                    }
                    //输入：用户ID
                    ShowInfo.main_for_showinfo(useraccount);
                    break;
                case 4:
                    flag = CheckLogin.main_for_check(flag);
                    if (flag != 1) {
                        System.out.println("游玩随机挑战模式前，请先登录账号！");
                        break;
                    }
                    Mechanism.mechanism();
                    break;
                case 5:
                    //Test_test.read();
                    break;
            }

        }

    }

}
