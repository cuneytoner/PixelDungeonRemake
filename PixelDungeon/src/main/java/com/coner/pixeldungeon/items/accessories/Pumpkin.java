package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class Pumpkin extends Accessory{

    {
        coverHair = true;
        image = 3;
        additionalDefenseSkill = 2;
        additionalAttackSkill = 1;
        additionalHP = 10;
    }

    public String getInfo() {
        return Game.getVar(R.string.This_Pumpkin_gives_additional_2_defense_skill_1_attack_skill_and_10_HP);
    }
}
