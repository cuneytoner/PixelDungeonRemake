package com.coner.pixeldungeon.levels;

import com.coner.pixeldungeon.remake.R;
import com.watabou.pixeldungeon.items.Amulet;
import com.watabou.noosa.Game;
import com.watabou.noosa.Scene;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.levels.painters.Painter;
import com.watabou.utils.Random;

import java.util.Arrays;

/**
 * Created by cc on 11/21/16.
 */

public class LastLevel extends Level {


    private static final int SIZE = 7;

    {
        color1 = 0x801500;
        color2 = 0xa68521;
    }

    private int pedestal;

    @Override
    public String tilesTex() {
        return Assets.TILES_DRAGONPITS;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_DRAGONPITS;
    }

    @Override
    protected boolean build() {

        Arrays.fill( map, Terrain.WALL );
        Painter.fill( this, 1, 1, SIZE, SIZE, Terrain.WATER );
        Painter.fill( this, 2, 2, SIZE-2, SIZE-2, Terrain.EMPTY );
        Painter.fill( this, SIZE/2, SIZE/2, 3, 3, Terrain.EMPTY_SP );

        entrance = SIZE * getWidth() + SIZE / 2 + 1;
        map[entrance] = Terrain.ENTRANCE;

        setExit(entrance - getWidth() * SIZE,0);
        map[getExit(0)] = Terrain.LOCKED_EXIT;

        pedestal = (SIZE / 2 + 1) * (getWidth() + 1);
        map[pedestal] = Terrain.PEDESTAL;
        map[pedestal-1] = map[pedestal+1] = Terrain.STATUE_SP;

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
    protected void createMobs() {
    }

    @Override
    protected void createItems() {
        drop( new Amulet(), pedestal );
    }

    @Override
    public int randomRespawnCell() {
        return -1;
    }

    @Override
    public String tileName( int tile ) {
        switch (tile) {
            case Terrain.WATER:
                return Game.getVar(R.string.FakeLastLevel2_TileWater);
            case Terrain.GRASS:
                return Game.getVar(R.string.FakeLastLevel2_TileGrass);
            case Terrain.HIGH_GRASS:
                return Game.getVar(R.string.FakeLastLevel2_TileHighGrass);
            case Terrain.STATUE:
            case Terrain.STATUE_SP:
                return Game.getVar(R.string.FakeLastLevel2_TileStatue);
            default:
                return super.tileName( tile );
        }
    }

    @Override
    public String tileDesc(int tile) {
        switch (tile) {
            case Terrain.WATER:
                return Game.getVar(R.string.FakeLastLevel2_TileDescWater);
            case Terrain.STATUE:
            case Terrain.STATUE_SP:
                return Game.getVar(R.string.FakeLastLevel2_TileDescStatue);
            default:
                return super.tileDesc( tile );
        }
    }

    @Override
    public void addVisuals( Scene scene ) {
        DragonPitsLevel.addVisuals( this, scene );
    }

}


