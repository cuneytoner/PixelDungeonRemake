package com.watabou.pixeldungeon.actors.mobs;

import java.util.HashSet;

import com.coner.pixeldungeon.effects.DeathStroke;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.Statistics;
import com.watabou.pixeldungeon.actors.buffs.Amok;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.actors.buffs.Charm;
import com.watabou.pixeldungeon.actors.buffs.Cripple;
import com.watabou.pixeldungeon.actors.buffs.Paralysis;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.items.drink.Wine;
import com.watabou.pixeldungeon.items.food.MysteryMeat;
import com.watabou.pixeldungeon.items.potions.PotionOfHealing;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfSummon;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.mechanics.Ballistica;
import com.watabou.pixeldungeon.sprites.GoblinSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

/**
 * Created by cc on 11/26/16.
 */

public class Goblin extends Mob {
    private int level;

    private static final String LEVEL = "level";

    {
        spriteClass = GoblinSprite.class;
        viewDistance = 6;

        this.level = 2 * Statistics.deepestFloor;

        hp(ht( (5 + level) * 8));
        defenseSkill = 19 + level;

        flying = true;
        state = WANDERING;

        loot = new PotionOfHealing();
        lootChance = 0.225f;

        RESISTANCES.add( Charm.class );
    }


    @Override
    public int attackSkill(Char target) {
        return defenseSkill;
    }


    @Override
    public int damageRoll() {
        return Random.NormalIntRange( defenseSkill/2, defenseSkill );
    }


    @Override
    public int dr() {
        return defenseSkill;
    }


    private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
    static {
        IMMUNITIES.add(Burning.class);
        IMMUNITIES.add(Amok.class);
    }

    @Override
    public HashSet<Class<?>> immunities() {
        return IMMUNITIES;
    }

    @Override
    protected boolean canAttack( Char enemy ) {
        return !Dungeon.level.adjacent( getPos(), enemy.getPos() ) && Ballistica.cast( getPos(), enemy.getPos(), false, true ) == enemy.getPos();
    }

    @Override
    public int attackProc( Char enemy, int damage ) {
        if (Random.Int( 2 ) == 0) {
            Buff.prolong( enemy, Cripple.class, Cripple.DURATION );
        }

        return damage;
    }

    @Override
    protected boolean getCloser( int target ) {
        if (state == HUNTING) {
            return enemySeen && getFurther( target );
        } else {
            return super.getCloser( target );
        }
    }

    @Override
    protected void dropLoot() {
        if (Random.Int( 8 ) == 0) {
            Dungeon.level.drop( new PotionOfHealing(), getPos() ).sprite.drop();
        } else if (Random.Int( 6 ) == 0) {
            Dungeon.level.drop( new Wine(), getPos() ).sprite.drop();
        }
    }

}

  