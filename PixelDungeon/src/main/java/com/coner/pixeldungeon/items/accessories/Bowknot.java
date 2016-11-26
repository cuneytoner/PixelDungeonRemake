package com.coner.pixeldungeon.items.accessories;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;

/**
 * Created by DeadDie on 26.05.2016
 */
public class Bowknot extends Accessory{

    {image = 2;
     additionalAttackSkill = 1;
    }

    public String getInfo() {
        return Game.getVar(R.string.this_bowknot_gives_additional_attack_skill);
    }

}
