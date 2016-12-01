package com.watabou.pixeldungeon.actors.blobs;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.Journal;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Heap;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

/**
 * Created by cc on 11/28/16.
 */

public class Portal extends Blob {
    protected int pos;

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );

        for (int i=0; i < getLength(); i++) {
            if (cur[i] > 0) {
                pos = i;
                break;
            }
        }
    }

    @Override
    protected void evolve() {
        volume = off[pos] = cur[pos];
/*
        if (Dungeon.visible[pos]) {
            if (this instanceof WaterOfAwareness) {
                Journal.add( Journal.Feature.WELL_OF_AWARENESS );
            } else if (this instanceof WaterOfHealth) {
                Journal.add( Journal.Feature.WELL_OF_HEALTH );
            } else if (this instanceof WaterOfTransmutation) {
                Journal.add( Journal.Feature.WELL_OF_TRANSMUTATION );
            }
        }
        */
    }

    protected boolean affect() {

        Heap heap;

        if (pos == Dungeon.hero.getPos() && affectHero( Dungeon.hero )) {

            volume = off[pos] = cur[pos] = 0;
            return true;

        } else if ((heap = Dungeon.level.getHeap( pos )) != null) {

            Item oldItem = heap.peek();
            Item newItem = affectItem( oldItem );

            if (newItem != null) {

                if (newItem == oldItem) {

                } else if (oldItem.quantity() > 1) {

                    oldItem.quantity( oldItem.quantity() - 1 );
                    heap.drop( newItem );

                } else {
                    heap.replace( oldItem, newItem );
                }

                heap.sprite.link();
                volume = off[pos] = cur[pos] = 0;

                return true;

            } else {

                int newPlace;
                do {
                    newPlace = pos + Level.NEIGHBOURS8[Random.Int( 8 )];
                } while (!Dungeon.level.passable[newPlace] && !Dungeon.level.avoid[newPlace]);
                Dungeon.level.drop( heap.pickUp(), newPlace ).sprite.drop( pos );

                return false;

            }

        } else {

            return false;

        }
    }

    protected boolean affectHero( Hero hero ) {
        return false;
    }

    protected Item affectItem( Item item ) {
        return null;
    }

    @Override
    public void seed( int cell, int amount ) {
        cur[pos] = 0;
        pos = cell;
        volume = cur[pos] = amount;
    }

    public static void affectCell( int cell ) {

        Class<?>[] waters = {WaterOfHealth.class, WaterOfAwareness.class, WaterOfTransmutation.class};

        for (Class<?>waterClass : waters) {
            WellWater water = (WellWater)Dungeon.level.blobs.get( waterClass );
            if (water != null &&
                    water.volume > 0 &&
                    water.pos == cell &&
                    water.affect()) {

                Dungeon.level.set( cell, Terrain.EMPTY_WELL );
                GameScene.updateMap( cell );

                return;
            }
        }
    }
}


