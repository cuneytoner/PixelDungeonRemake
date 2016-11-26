package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class WizardHat extends Accessory{

    {
        coverHair = true;
        image = 1;
        additionalHP = 10;
        additionalAttackSkill = 10;
    }


    public String getInfo() {
        return Game.getVar(R.string.This_Wizard_Hat_gives_additional_10_attack_skill_and_10_HP);
    }
}
