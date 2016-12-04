package com.coner.pixeldungeon.levels;

/**
 * Created by cc on 11/28/16.
 */

import com.coner.pixeldungeon.items.guts.PseudoAmulet;
import com.coner.pixeldungeon.items.stones.Stone;
import com.coner.pixeldungeon.levels.PredesignedLevel;
import com.coner.pixeldungeon.remake.R;
import com.coner.pixeldungeon.utils.DungeonGenerator;
import com.watabou.noosa.Game;
import com.watabou.noosa.Scene;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Bones;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.items.Generator;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.Heap;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.armor.Armor;
import com.watabou.pixeldungeon.items.drink.Drink;
import com.watabou.pixeldungeon.items.food.Food;
import com.watabou.pixeldungeon.items.scrolls.Scroll;
import com.watabou.pixeldungeon.items.weapon.Weapon;
import com.watabou.pixeldungeon.levels.HallsLevel;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Patch;
import com.watabou.pixeldungeon.levels.RegularLevel;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.levels.TerrainFlags;
import com.watabou.pixeldungeon.levels.painters.Painter;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.utils.Random;

import java.util.Arrays;


public class PortalLevel extends Level {
    protected int width  = 24;
    protected int height = 24;
    private static final int SIZE = 9;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    {
        color1 = 0x801500;
        color2 = 0xa68521;
    }

    private int _RoomLeft() {
        return getWidth() / 2 - 1;
    }

    private int _RoomRight() {
        return getWidth() / 2 + 1;
    }

    private int _RoomTop() {
        return getHeight() / 2 - 1;
    }

    private int _RoomBottom() {
        return getHeight() / 2 + 1;
    }

    @Override
    protected boolean build() {

        Arrays.fill( map, Terrain.WALL );
        Painter.fill( this, 2, 2, SIZE-2, SIZE-2, Terrain.WATER );
        Painter.fill( this, 3, 3, SIZE-4, SIZE-4, Terrain.EMPTY );
        Painter.fill( this, SIZE/2, SIZE/2, 3, 3, Terrain.EMPTY_SP );

        entrance = (SIZE * getWidth() + SIZE / 2 + 1);

        setExit(entrance - getWidth() * ( SIZE - 1 ),0);
        map[getExit(0)] = Terrain.EXIT;

        entrance = (SIZE / 2 + 1) * (getWidth() + 1);//(SIZE * getWidth() + SIZE / 2 + 1);
        map[entrance] = Terrain.LOCKED_DOOR;
		/*secondaryExit = entrance - getWidth() * SIZE + getWidth() * 2;
		map[secondaryExit] = Terrain.EXIT;*/

        feeling = Feeling.NONE;

        return true;
    }

    @Override
    protected void decorate() {
        for (int i=0; i < getLength(); i++) {
            if (map[i] == Terrain.EMPTY && Random.Int( 10 ) == 0) {
                map[i] = Terrain.EMPTY_DECO;
            }
        }
    }

    @Override
    public int nMobs() {
        return (int) (6 * ((Game.getDifficulty()+1)));
    }

    @Override
    public int randomRespawnCell() {
        int cell = super.randomRespawnCell();

        while (map[cell] == Terrain.FIRE_TRAP
                || map[cell] == Terrain.SECRET_FIRE_TRAP
                || map[cell] == Terrain.EXIT
                || map[cell] == Terrain.ENTRANCE) {
            cell = randomRespawnCell();
        }
        return  cell;
    }

    @Override
    protected void createMobs() {
        if (Random.Int(1)==0)
        {
            for (int i = 0; i< 3;i++) {
                Food food = (Food) Generator.random(Generator.Category.FOOD);
                this.drop(food, this.randomRespawnCell());
            }

        }

        if (Random.Int(1)==0)
        {
            for (int i = 0; i< 3;i++) {
                Drink drink = (Drink) Generator.random(Generator.Category.DRINK);
                this.drop(drink, this.randomRespawnCell());
            }

        }

        if (Random.Int(1)==0)
        {
            for (int i = 0; i< 3;i++) {
                Scroll scroll = (Scroll) Generator.random(Generator.Category.SCROLL);
                this.drop(scroll, this.randomRespawnCell());
            }

        }

        if (Random.Int(5)==0)
        {
            Armor armor = (Armor) Generator.random(Generator.Category.ARMOR);
            this.drop(armor, this.randomRespawnCell());

        }

        if (Random.Int(5)==0)
        {
            Weapon weapon = (Weapon) Generator.random(Generator.Category.WEAPON);
            this.drop(weapon, this.randomRespawnCell());

        }

        if (Random.Int(5)==0)
        {
            for (int i = 0; i< 1;i++) {
                Stone stone = (Stone) Generator.random(Generator.Category.STONE);
                this.drop(stone, this.randomRespawnCell());
            }

        }

        if (Random.Int(10)==0)
        {
            int nMobs = nMobs();
            for (int i = 0; i < nMobs; i++) {
                Mob mob = createMob();
                mobs.add(mob);
                Actor.occupyCell(mob);
            }
        }
        GameScene.updateMap();

    }

    @Override
    protected void createItems() {
    }


    protected boolean[] water() {
        return Patch.generate(this, feeling == Feeling.WATER ? 0.65f : 0.45f, 4 );
    }

    protected boolean[] grass() {
        return Patch.generate(this, feeling == Feeling.GRASS ? 0.60f : 0.40f, 3 );
    }

    public PortalLevel()
    {
        super();
        this.levelId = "portallevel";
    }

    private int pedestal;

    @Override
    public String tileDesc(int tile) {

        switch (tile) {
            case Terrain.EXIT:
            case Terrain.UNLOCKED_EXIT:
                return Game.getVar(R.string.Level_TileDescExit);
            default:
                return super.tileDesc( tile );
        }
    }


    @Override
    public String tilesTex() {
        return Assets.TILES_PORTAL_LEVEL;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_PORTAL_LEVEL;
    }



    @Override
    public boolean isPortalLevel() { return true;}


    @Override
    public void addVisuals( Scene scene ) {
        HallsLevel.addVisuals( this, scene );
    }

}

