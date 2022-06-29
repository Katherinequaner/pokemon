package com.ncist.Pokemon.Login;

import java.sql.SQLException;
import java.util.Scanner;

import com.ncist.Pokemon.Main.*;

import com.ncist.Pokemon.Dbs.DbsConnect;

import static com.ncist.Pokemon.Main.Main.useraccount;

public class CheckLogin {
    static Scanner input = new Scanner(System.in);

    //注册账户
    public static void sign_account() throws SQLException {
        //读取数据库，查看当前最新的玩家编号，当前玩家编号 = 最新的玩家编号 + 1
        int user = DbsConnect.select_last_playerid();
        user = user + 1;

        System.out.println(String.format("自动分配账号：\n您的账号为：{%d}\n请输入您的密码：(最多10位)", user));
        String pass = input.next();
        while (pass == null) {
            System.out.println("输入的账号不允许为空！请重新输入！");
            pass = input.next();
        }
        //将账号和密码存入数据库
        DbsConnect.account_save(user, pass);
    }

    //检查账号是否正常
    public static int main_for_check(int flag) throws SQLException {
        int user = 0;
        if (flag == 0) {
            System.out.println("请输入你的账号：");
            user = input.nextInt();
            System.out.println("请输入你的账号密码：");
            String pass = input.next();

            //数据库代码，将账号和密码和数据库中的密码比对 (待完工)
            int i = DbsConnect.check_pass(user, pass);
            if (i == 1) {
                useraccount = user;
                return 1;
            } else {
                System.out.println("您的账号或密码有误，请重新输出！");
                return 0;
            }
        }
        return 1;
    }
}
