package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class RabbitEars extends Accessory{

    {
        image = 7;
        coverHair = true;
        additionalDefenseSkill = 4;
        pet = true;
    }

    public String getInfo() {
        return Game.getVar(R.string.This_Rabbit_Ears_gives_additional_4_defense_skill);
    }

}
