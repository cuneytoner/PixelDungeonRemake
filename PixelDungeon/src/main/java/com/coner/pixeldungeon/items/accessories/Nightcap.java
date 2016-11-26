package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class Nightcap extends Accessory{

    {
        coverHair = true;
        image = 4;
        additionalDefenseSkill = 2;
    }

    public String getInfo() {
        return Game.getVar(R.string.This_Nightcap_gives_2_additional_defense_skill);
    }

}
