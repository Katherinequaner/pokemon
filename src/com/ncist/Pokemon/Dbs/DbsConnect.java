package com.ncist.Pokemon.Dbs;

import javax.swing.*;
import java.sql.*;

public class DbsConnect {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/pokemondbs?useSSL=false";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "rootroot";

    static Connection conn = null;
    static Statement stmt = null;

    //connect_main
    //main
    //String[] args
    public static void connect_main() {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    //展示玩家相关信息,比如玩家的游戏昵称，玩家等级，所拥有的精灵编号，所拥有的精灵编号对应的精灵等级 (待实现)
    public static void show_play_info(int user) throws SQLException {
        //必须连接数据库先：
        DbsConnect.connect_main();
        try {
            //实例化Statement对象
            stmt = conn.createStatement();

            String sql;
            sql = String.format("select SpiritNum, playMoney ,hasSpiritGrade from Players where PlayerNum  = %d", user);
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                int SpiritNum = rs.getInt("SpiritNum");
                int playMoney = rs.getInt("playMoney");
                int hasSpiritGrade = rs.getInt("hasSpiritGrade");
                System.out.println(String.format("~~~~~~玩家信息：~~~~~~\n所拥有的精灵编号：%s\t所拥有的金币数量：%s\n所拥有的精灵等级：%s",SpiritNum,playMoney,hasSpiritGrade));
            }
        } catch (SQLException se) {
            // 处理 JDBC 错误
            System.out.println("未知错误1！");
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            System.out.println("未知错误2！");
        } finally {
            stmt.close();
            conn.close();
        }
    }

    //验证账号和密码是否正确,返回0则密码错误，返回1则密码正确
    public static int check_pass(int user, String pass) throws SQLException {
        //必须连接数据库先：
        DbsConnect.connect_main();
        try {
            //实例化Statement对象
            stmt = conn.createStatement();

            String sql;
            sql = String.format("select Passwd from user where UserAccount = %d", user);
            ResultSet rs = stmt.executeQuery(sql);
            int flag = 0;

            if (rs.next()) {
                if (pass.equals(rs.getString("Passwd")) != true) {
                    System.out.println("密码错误！请重新输入！");
                    flag = 1;
                }
                if (flag == 1) {
                    return 0;
                }
            }
            else
            {
                return 0;
            }
            System.out.println("密码正确！开始游玩吧");

            return 1;
        } catch (SQLException se) {
            // 处理 JDBC 错误
            System.out.println("未知错误1！");
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            System.out.println("未知错误2！");
        } finally {
            stmt.close();
            conn.close();
        }
        return 0;
    }

    //存储用户的账号的密码
    public static void account_save(int user, String pass) throws SQLException {
        //必须连接数据库先：
        DbsConnect.connect_main();
        try {
            //实例化Statement对象
            stmt = conn.createStatement();
            String sql;
            //int ，char ，int
            sql = String.format("INSERT INTO user VALUES (%d,'%s',%d);", user, pass, user);
            int rs = stmt.executeUpdate(sql);
            System.out.println("成功创建账号！登录账号后开始游玩吧");
        } catch (SQLException se) {
            // 处理 JDBC 错误
            System.out.println("并没有成功创建账号！请重新注册，可能是密码设置的过长的缘故！");
        } catch (Exception e) {
            // 处理 Class.forName 错误
            System.out.println("并没有成功创建账号！请重新注册，可能是密码设置的过长的缘故！");
        } finally {
            stmt.close();
            conn.close();
        }
    }

    //返回最新的玩家编号,且玩家编号是自增的
    public static int select_last_playerid() throws SQLException {
        //必须连接数据库先：
        DbsConnect.connect_main();

        int i = 1; // i 是计数器

        //实例化Statement对象
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT UserAccount FROM user";
        ResultSet rs = stmt.executeQuery(sql);

        //特例：假如数据库没有任何数据时，需要人为伪造
        if (!rs.next()) {
            return 0;
        }

        // 展开结果集数据库
        while (rs.next()) {
            i++;
        }

        // 完成后关闭 先得到的后关闭，后得到的先关闭
        rs.close();
        stmt.close();
        conn.close();
        return i;
    }
}
