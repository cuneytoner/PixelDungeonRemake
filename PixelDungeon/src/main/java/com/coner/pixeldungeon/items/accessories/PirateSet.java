package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class PirateSet extends Accessory{

    {
        coverHair = true;
        image = 10;
        additionalAttackSkill = 2;
        additionalHP = 10;
        additionalDefenseSkill = 2;

    }

    public String getInfo() {
        return Game.getVar(R.string.This_Pirate_Set_gives_additional_2_attack_skill_2_defense_skill_and_10_HP);
    }

}
