package com.watabou.pixeldungeon.items.drink;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Charm;
import com.watabou.pixeldungeon.actors.buffs.Fury;
import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.buffs.Slow;
import com.watabou.pixeldungeon.actors.buffs.Vertigo;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Created by cc on 11/15/16.
 */

public class Beer extends Drink {


    {
        image   = ItemSpriteSheet.BEER;
        energy  = Hunger.STARVING - Hunger.HUNGRY;
        message = Game.getVar(R.string.Beer_Name);
    }

    @Override
    public void execute(Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_DRINK )) {

            switch (Random.Int( 5 )) {
                case 0:
                    GLog.w(Game.getVar(R.string.Beer_Info1));
                    Buff.affect(hero, Charm.class, Charm.durationFactor(hero) * Random.IntRange(10, 15) * 1);
                    break;
                case 1:
                    GLog.w(Game.getVar(R.string.Beer_Info2));
                    Buff.affect(hero, Fury.class);
                    break;
                case 2:
                    GLog.w(Game.getVar(R.string.Beer_Info3));
                    Buff.prolong( hero, Vertigo.class, Vertigo.duration( hero ) );
                    break;
                case 3:
                    GLog.w(Game.getVar(R.string.Beer_Info4));
                    Buff.prolong( hero, Slow.class, Slow.duration( hero ) );
                    break;
            }
        }
    }

    public int price() {
        return 5 * quantity();
    }

    @Override
    public Item burn(int cell){
        return morphTo(BoiledBeer.class);
    }

    @Override
    public Item freeze(int cell){
        return morphTo(FrozenBeer.class);
    }

    @Override
    public Item poison(int cell){
        return morphTo(TaintedBeer.class);
    }

}
