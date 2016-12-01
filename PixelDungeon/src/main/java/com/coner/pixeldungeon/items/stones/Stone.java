package com.coner.pixeldungeon.items.stones;


/**
 * Created by cc on 11/29/16.
 */

import com.coner.android.util.TrackedRuntimeException;
import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.actors.buffs.Blindness;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.ItemStatusHandler;
import com.watabou.pixeldungeon.items.scrolls.Scroll;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.pixeldungeon.utils.Utils;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Stone extends Item {
    public static final String AC_USE    	= Game.getVar(R.string.Stone_ACUse);

    protected static final float TIME_TO_USESTONE	= 1f;

    private String color;

    private static final Class<?>[] stones = {
            PortalStone.class    };


    private static String[] colors = null;

    private static final Integer[] images = {
            ItemSpriteSheet.PORTAL_STONE
    };

    private static String[] getColors(){
        if(colors == null){
            colors = Game.getVars(R.array.Stone_Colors);
        }
        return colors;
    }

    @SuppressWarnings("unchecked")
    public static void initLabels() {
        handler = new ItemStatusHandler<>((Class<? extends Stone>[]) stones, getColors(), images);
    }

    private static ItemStatusHandler<Stone> handler;

    public static void save( Bundle bundle ) {
        handler.save( bundle );
    }

    @SuppressWarnings("unchecked")
    public static void restore( Bundle bundle ) {
        handler = new ItemStatusHandler<>((Class<? extends Stone>[]) stones, getColors(), images, bundle);
    }

    public Stone() {
        stackable     = true;
        defaultAction = AC_USE;

        image = handler.image( this );
        color  = handler.label( this );
    }

    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_USE );
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {
        if (action.equals( AC_USE )) {

                setCurUser(hero);
                curItem = detach( hero.belongings.backpack );

                doUse();

        } else {

            super.execute( hero, action );

        }
    }

    abstract protected void doUse();

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
        return isKnown() ? name : Utils.format(Game.getVar(R.string.Scroll_Name), color);
    }

    @Override
    public String info() {
        return isKnown() ? desc() : Utils.format(Game.getVar(R.string.Scroll_Info), color);
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

}

