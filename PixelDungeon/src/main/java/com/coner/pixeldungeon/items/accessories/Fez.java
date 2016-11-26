package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class Fez extends Accessory{

    {
        coverHair = true;
        image = 9;
        additionalHP = 10;
    }

    public String getInfo() {
        return Game.getVar(R.string.This_Fez_gives_10_additional_HP);
    }

}
