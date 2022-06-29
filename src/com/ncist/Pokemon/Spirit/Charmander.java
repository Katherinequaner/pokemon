//小火龙,火系
package com.ncist.Pokemon.Spirit;

import java.util.ArrayList;

public class Charmander extends SpiritRoot {

    public Charmander() {
        attackPower = 22;
        blood = 180;
        spiritName = "Charmander";
        tricksList.add(Tricks.tornado); //添加招式
       // tricksList.add();
    }
    //使用何种技能
    public void spirit_choice_tricks(int i,SpiritRoot enemySpirit){
        switch (i) {
            case 0:
                tornado(enemySpirit);
                break;
            case 1:
                break;
            default:
                System.out.println("精灵没有该技能！");
        }
    }

    public  void  tornado(SpiritRoot enemySpirit) {
        //System.out.println("{我方}使用招式：龙卷风!");
        this.sub_blood(150,enemySpirit);
    }

}
