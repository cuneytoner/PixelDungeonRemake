package com.coner.pixeldungeon.items.stones;


/**
 * Created by cc on 11/29/16.
 */

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.effects.Splash;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.ItemStatusHandler;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.sprites.ItemSprite;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.Utils;
import com.watabou.utils.Bundle;

import java.util.HashSet;

public class Stone extends Item {
    protected static final float TIME_TO_USESTONE	= 1f;

    private static final Class<?>[] stones = {
            PortalStone.class,
            WanishStone.class
        };

    private static String[] colors = null;

    private static final Integer[] images = {
            ItemSpriteSheet.PORTAL_STONE,
            ItemSpriteSheet.WANISH_STONE
    };


    private static ItemStatusHandler<Stone> handler;

    private String color;

    {
        stackable = true;
        defaultAction = AC_THROW;
    }

    private boolean shatterd = false;

    private static String[] getColors(){
        if(colors == null){
            colors = Game.getVars(R.array.Stone_Colors);
        }
        return colors;
    }

    @SuppressWarnings("unchecked")
    public static void initColors() {
        handler = new ItemStatusHandler<>((Class<? extends Stone>[]) stones, getColors(), images);
    }

    public static void save( Bundle bundle ) {
        handler.save( bundle );
    }

    @SuppressWarnings("unchecked")
    public static void restore( Bundle bundle ) {
        handler = new ItemStatusHandler<>((Class<? extends Stone>[]) stones, getColors(), images, bundle);
    }

    public Stone() {
        image = handler.image( this );
        color  = handler.label( this );
    }

    protected String color() {
        return color;
    }


    public boolean isKnown() {
        return handler.isKnown( this );
    }

    public void setKnown() {
        if (!isKnown()) {
            handler.know( this );
        }

    }

    @Override
    public Item identify() {
        setKnown();
        return super.identify();
    }

    @Override
    public String name() {
        return isKnown() ? name : Utils.format(Game.getVar(R.string.Stone_Name), color);
    }

    @Override
    public String info() {
        return isKnown() ? desc() : Utils.format(Game.getVar(R.string.Stone_Info), color);
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return isKnown();
    }

    public static HashSet<Class<? extends Stone>> getKnown() {
        return handler.known();
    }

    public static HashSet<Class<? extends Stone>> getUnknown() {
        return handler.unknown();
    }

    public static boolean allKnown() {
        return handler.known().size() == stones.length;
    }

    @Override
    public int price() {
        return 15 * quantity();
    }

    @Override
    public Item burn(int cell){
        return null;
    }

    protected boolean canShatter() {
        if(!shatterd) {
            shatterd = true;
            return true;
        }
        return false;
    }

    protected void splash( int cell ) {
        final int color = ItemSprite.pick( image, 8, 10 );
        Splash.at( cell, color, 5 );
    }

    public void shatter( int cell ) {
        setKnown();
        splash( cell );
        useStone(cell);
    }

    protected void useStone(int cell) {
    }

        @Override
    protected void onThrow( int cell ) {
        if (Dungeon.hero.getPos() == cell) {
            super.onThrow( cell );

        } else if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {

            super.onThrow( cell );

        } else  {

            shatter( cell );

        }
    }
}

