//妙蛙种子,水系
package com.ncist.Pokemon.Spirit;

import java.util.ArrayList;

public class Bulbasaur extends SpiritRoot {

    public Bulbasaur() {
        attackPower = 18;
        blood = 300;
        spiritName = "Bulbasaur";   //精灵名字
        tricksList.add(Tricks.tortoise_shell); //添加招式
    }

    //使用何种技能
    public void spirit_choice_tricks(int i,SpiritRoot enemySpirit){
        switch (i) {
            case 0:
                tortoise_shell(enemySpirit);
            case 1:
                break;
            default:
                System.out.println("精灵没有该技能！");
        }
    }


    public  void tortoise_shell(SpiritRoot enemySpirit) {
        //System.out.println("{我方}使用招式：龟甲!");
        this.sub_blood(130,enemySpirit);
    }

}
