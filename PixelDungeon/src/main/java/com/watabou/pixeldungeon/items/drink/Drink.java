package com.watabou.pixeldungeon.items.drink;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Statistics;
import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.effects.SpellSprite;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfRecharging;
import com.watabou.pixeldungeon.utils.GLog;

import java.util.ArrayList;

/**
 * Created by cc on 11/15/16.
 */

abstract public class Drink extends Item {

    public static final float TIME_TO_DRINK	= 3f;

    public static final String AC_DRINK = Game.getVar(R.string.Drink_ACDrink);

    public float energy   = 0;
    public String message = Game.getVar(R.string.Drink_Message);

    {
        stackable = true;
    }

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_DRINK );
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {
        if (action.equals( AC_DRINK )) {

            detach( hero.belongings.backpack );

            hero.buff( Hunger.class ).satisfy(energy);
            GLog.i( message );

            switch (hero.heroClass) {
                case CLERIC:
                    if (hero.hp() < hero.ht()) {
                        hero.hp(Math.min( hero.hp() + 15, hero.ht() ));
                        hero.getSprite().emitter().burst( Speck.factory( Speck.HEALING ), 1 );
                    }
                case WARRIOR:
                    if (hero.hp() < hero.ht()) {
                        hero.hp(Math.min( hero.hp() + 5, hero.ht() ));
                        hero.getSprite().emitter().burst( Speck.factory( Speck.HEALING ), 1 );
                    }
                    break;
                case MAGE:
                    hero.belongings.charge( false );
                    ScrollOfRecharging.charge( hero );
                    break;
                case ROGUE:
                case HUNTRESS:
                case ELF:
                    break;
            }

            hero.getSprite().operate( hero.getPos() );
            hero.busy();
            SpellSprite.show( hero, SpellSprite.DRINK );
            Sample.INSTANCE.play( Assets.SND_DRINK );

            hero.spend( TIME_TO_DRINK );

        } else {

            super.execute( hero, action );

        }
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

}

