package com.watabou.pixeldungeon.items.drink;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by cc on 11/15/16.
 */

public class TaintedBeer extends TaintedDrink {
    public TaintedBeer() {
        image   = ItemSpriteSheet.TAINTED_BEER;
    }

    @Override
    public Drink purify() {
        return new Beer();
    }

}

