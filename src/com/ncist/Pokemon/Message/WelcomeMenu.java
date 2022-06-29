//欢迎菜单，游戏提示等消息都在这写
package com.ncist.Pokemon.Message;


public class WelcomeMenu {
    //游戏版权等信息
    public static void banner(){
        System.out.println("\n-------------欢迎来到精灵宝可梦游戏!----------------\n-------------版权所有：摆烂小分队----------------");
    }

    public static void menu() {
        System.out.println("\n游戏菜单：\n1：注册账号\n2：登录账号\n3：查看人物信息\n4：随机挑战模式\n5：测试用（上线前删除）");
    }
}

