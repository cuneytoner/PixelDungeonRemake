package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class Shades extends Accessory{

    {image = 5;
     additionalHP = 10;
     additionalDefenseSkill = 2;
    }

    public String getInfo() {
        return Game.getVar(R.string.This_Shades_gives_additional_2_defense_skill_and_10_HP);
    }

}
