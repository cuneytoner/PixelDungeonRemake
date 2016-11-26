package com.watabou.pixeldungeon.items.drink;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.actors.buffs.Awareness;
import com.watabou.pixeldungeon.actors.buffs.Barkskin;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.buffs.MindVision;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Created by cc on 11/15/16.
 */

public class FrozenBeer extends Drink {
    {
        image  = ItemSpriteSheet.FROZEN_BEER;
        energy = Hunger.STARVING - Hunger.HUNGRY;
    }

    @Override
    public void execute(Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_DRINK )) {

            switch (Random.Int( 5 )) {
                case 0:
                    GLog.i(Game.getVar(R.string.FrozenBeer_Info1));
                    Buff.affect( hero, Awareness.class, Awareness.DURATION );
                    break;
                case 1:
                    GLog.i(Game.getVar(R.string.FrozenBeer_Info2));
                    Buff.affect( hero, Barkskin.class ).level( hero.ht() / 4 );
                    break;
                case 2:
                    GLog.i(Game.getVar(R.string.FrozenBeer_Info3));
                    Buff.affect(hero, MindVision.class, MindVision.DURATION);
                    break;
                case 3:
                    GLog.i(Game.getVar(R.string.FrozenBeer_Info4));
                    if (hero.hp() < hero.ht()) {
                        hero.hp(Math.min( hero.hp() + hero.ht() / 4, hero.ht() ));
                        hero.getSprite().emitter().burst( Speck.factory( Speck.HEALING ), 1 );
                    }
                    break;
            }
        }
    }

    public int price() {
        return 10 * quantity();
    }

    @Override
    public Item burn(int cell){
        return morphTo(Beer.class);
    }
}

