package com.coner.pixeldungeon.levels;

/**
 * Created by cc on 11/28/16.
 */

import com.coner.pixeldungeon.items.guts.PseudoAmulet;
import com.coner.pixeldungeon.levels.PredesignedLevel;
import com.coner.pixeldungeon.remake.R;
import com.coner.pixeldungeon.utils.DungeonGenerator;
import com.watabou.noosa.Game;
import com.watabou.noosa.Scene;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.levels.HallsLevel;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.levels.painters.Painter;
import com.watabou.utils.Random;

import java.util.Arrays;


public class PortalLevel extends PredesignedLevel{
    private static final int SIZE = 7;

    {
        color1 = 0x801500;
        color2 = 0xa68521;
    }

    public PortalLevel(String fileName) {
        super(fileName);
    }

    public PortalLevel()
    {
        super();
        this.levelId = "portallevel";
    }
    private int pedestal;

    @Override
    public String tilesTex() {
        return Assets.TILES_PORTAL_LEVEL;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_PORTAL_LEVEL;
    }

    @Override
    protected void createMobs() {
    }

    @Override
    protected void createItems() {
        drop( new PseudoAmulet(), pedestal );
    }

    @Override
    public int randomRespawnCell() {
        return -1;
    }

    @Override
    public boolean isPortalLevel() { return true;}


    @Override
    public void addVisuals( Scene scene ) {
        HallsLevel.addVisuals( this, scene );
    }

}

