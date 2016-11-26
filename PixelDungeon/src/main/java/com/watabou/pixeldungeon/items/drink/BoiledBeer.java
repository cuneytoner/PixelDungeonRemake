package com.watabou.pixeldungeon.items.drink;

import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by cc on 11/15/16.
 */

public class BoiledBeer extends Drink {
    {
        image = ItemSpriteSheet.BOILED_BEER;
        energy = Hunger.STARVING - Hunger.HUNGRY;
    }

    @Override
    public int price() {
        return 5 * quantity();
    }

    @Override
    public Item poison(int cell){
        return morphTo(TaintedBeer.class);
    }
}

