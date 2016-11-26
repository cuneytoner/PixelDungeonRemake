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
import com.watabou.pixeldungeon.actors.buffs.Paralysis;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfSummon;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.GargoyleSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

/**
 * Created by cc on 11/26/16.
 */

public class Gargoyle extends Mob {
    private int level;

    private static final String LEVEL = "level";

    {
        spriteClass = GargoyleSprite.class;
        viewDistance = 4;

        this.level = 2 * Statistics.deepestFloor;

        hp(ht( (10 + level) * 8));
        defenseSkill = 29 + level;

        flying = true;
        state = WANDERING;

        loot = new ScrollOfSummon();
        lootChance = 0.15f;
    }


    @Override
    public int attackSkill(Char target) {
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
    public int attackProc( Char enemy, int damage ) {
        //Double damage proc
        if (Random.Int(4) == 1){
            if (enemy !=null){
                DeathStroke.hit(enemy);
            }
            return damage * 2;
        }
        //Paralysis proc
        if (Random.Int(10) == 1){
            Buff.affect(enemy, Burning.class);
        }
        return damage;
    }
}

  