package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class NekoEars extends Accessory{

    {
        image = 8;
        coverHair = true;
        additionalHP = 5;
    }

    public String getInfo() {
        return Game.getVar(R.string.Neko_Ears_gives_5_additional_HP);
    }

}
