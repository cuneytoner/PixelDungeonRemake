package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class Capotain extends Accessory{

    {
        coverHair = true;
        image = 6;
        additionalDefenseSkill = 1;
    }

    public String getInfo() {
        return Game.getVar(R.string.This_Capotain_gives_defence_skill);
    }

}
