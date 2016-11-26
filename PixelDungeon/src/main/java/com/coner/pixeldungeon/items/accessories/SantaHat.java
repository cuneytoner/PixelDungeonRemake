package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

public class SantaHat extends Accessory {

    {
        coverHair = true;
        image = 11;
        additionalHP = 10;
        additionalAttackSkill = 3;
        additionalDefenseSkill = 3;

    }

    public String getInfo() {
        return Game.getVar(R.string.SantaHatDesc);
    }

}
