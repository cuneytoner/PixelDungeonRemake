package com.watabou.pixeldungeon.items.drink;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Charm;
import com.watabou.pixeldungeon.actors.buffs.Fury;
import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.buffs.Slow;

import com.watabou.pixeldungeon.actors.buffs.Levitation;
import com.watabou.pixeldungeon.actors.buffs.Speed;
import com.watabou.pixeldungeon.actors.buffs.Regeneration;
import com.watabou.pixeldungeon.actors.buffs.Light;


import com.watabou.pixeldungeon.actors.buffs.Vertigo;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Created by cc on 11/15/16.
 */

public class Wine extends Drink {


    {
        image   = ItemSpriteSheet.WINE;
        energy  = Hunger.STARVING - Hunger.HUNGRY;
        message = Game.getVar(R.string.Wine_Name);
    }

    @Override
    public void execute(Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_DRINK )) {

            switch (Random.Int( 5 )) {
                case 0:
                    GLog.w(Game.getVar(R.string.Wine_Info1));
                    Buff.affect( hero, Light.class, Light.DURATION );
                    break;
                case 1:
                    GLog.w(Game.getVar(R.string.Wine_Info2));
                    Buff.affect(hero, Regeneration.class);
                    break;
                case 2:
                    GLog.w(Game.getVar(R.string.Wine_Info3));
                    Buff.affect(hero, Speed.class, Speed.DURATION);
                    break;
                case 3:
                    GLog.w(Game.getVar(R.string.Wine_Info4));
                    Buff.affect( hero, Levitation.class, Levitation.DURATION );
                    break;
            }
        }
    }

    public int price() {
        return 5 * quantity();
    }

    @Override
    public Item burn(int cell){
        return null;
    }

}
