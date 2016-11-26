package com.coner.pixeldungeon.levels;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.Scene;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.DungeonTilemap;
import com.coner.pixeldungeon.remake.R;
import com.watabou.pixeldungeon.actors.mobs.npcs.Hedgehog;
import com.watabou.pixeldungeon.items.Torch;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Patch;
import com.watabou.pixeldungeon.levels.RegularLevel;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.levels.TerrainFlags;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

/**
 * Created by cc on 11/21/16.
 */

public class DragonPitsLevel extends RegularLevel {
    {
        minRoomSize = 10;

        viewDistance = Math.max( 37 - Dungeon.depth, 1 );

        color1 = 0x801500;
        color2 = 0xa68521;
    }

    @Override
    public void create() {
        addItemToSpawn( new Torch() );
        super.create();
    }

    @Override
    public String tilesTex() {
        return Assets.TILES_DRAGONPITS;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_DRAGONPITS;
    }

    protected boolean[] water() {
        return Patch.generate(this, feeling == Feeling.WATER ? 0.55f : 0.40f, 6 );
    }

    protected boolean[] grass() {
        return Patch.generate(this, feeling == Feeling.GRASS ? 0.55f : 0.30f, 3 );
    }

    @Override
    protected void decorate() {

        for (int i=getWidth() + 1; i < getLength() - getWidth() - 1; i++) {
            if (map[i] == Terrain.EMPTY) {

                int count = 0;
                for (int j=0; j < NEIGHBOURS8.length; j++) {
                    if ((TerrainFlags.flags[map[i + NEIGHBOURS8[j]]] & TerrainFlags.PASSABLE) > 0) {
                        count++;
                    }
                }
                /*
                if (Random.Int( 80 ) < count) {
                    map[i] = Terrain.EMPTY_DECO;
                }*/

            } else
            if (map[i] == Terrain.WALL) {

                int count = 0;
                for (int j=0; j < NEIGHBOURS4.length; j++) {
                    if (map[i + NEIGHBOURS4[j]] == Terrain.WATER) {
                        count++;
                    }
                }

                if (Random.Int( 4 ) < count) {
                    map[i] = Terrain.WALL_DECO;
                }

            }
        }

        while (true) {
            int pos = roomEntrance.random(this);
            if (pos != entrance) {
                map[pos] = Terrain.SIGN;
                break;
            }
        }
    }

    @Override
    public String tileName( int tile ) {
        switch (tile) {
            case Terrain.WATER:
                return Game.getVar(R.string.Dragon_Pits_TileWater);
            case Terrain.GRASS:
                return Game.getVar(R.string.Dragon_Pits_TileGrass);
            case Terrain.HIGH_GRASS:
                return Game.getVar(R.string.Dragon_Pits_TileHighGrass);
            case Terrain.STATUE:
            case Terrain.STATUE_SP:
                return Game.getVar(R.string.Dragon_Pits_TileStatue);
            default:
                return super.tileName( tile );
        }
    }

    @Override
    public String tileDesc(int tile) {
        switch (tile) {
            case Terrain.WATER:
                return Game.getVar(R.string.Dragon_Pits_TileDescWater);
            case Terrain.STATUE:
            case Terrain.STATUE_SP:
                return Game.getVar(R.string.Dragon_Pits_TileDescStatue);
            case Terrain.BOOKSHELF:
                return Game.getVar(R.string.Dragon_Pits_TileDescBookshelf);
            default:
                return super.tileDesc( tile );
        }
    }

    @Override
    public void addVisuals( Scene scene ) {
        super.addVisuals( scene );
        addVisuals( this, scene );
    }

    public static void addVisuals( Level level, Scene scene ) {
        for (int i=0; i < level.getLength(); i++) {
            if (level.map[i] == 63) {
                scene.add( new Stream( i ) );
            }
        }
    }

    @Override
    protected void createMobs() {
        super.createMobs();

        Hedgehog.spawn(this);
    }

    private static class Stream extends Group {

        private int pos;

        private float delay;

        public Stream( int pos ) {
            super();

            this.pos = pos;

            delay = Random.Float( 2 );
        }

        @Override
        public void update() {

            if (setVisible(Dungeon.visible[pos])) {

                super.update();

                if ((delay -= Game.elapsed) <= 0) {

                    delay = Random.Float( 2 );

                    PointF p = DungeonTilemap.tileToWorld( pos );
                    ((FireParticle)recycle( FireParticle.class )).reset(
                            p.x + Random.Float( DungeonTilemap.SIZE ),
                            p.y + Random.Float( DungeonTilemap.SIZE ) );
                }
            }
        }

        @Override
        public void draw() {
            GLES20.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE );
            super.draw();
            GLES20.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
        }
    }

    public static class FireParticle extends PixelParticle.Shrinking {

        public FireParticle() {
            super();

            color( 0xEE7722 );
            lifespan = 1f;

            acc.set( 0, +80 );
        }

        public void reset( float x, float y ) {
            revive();

            this.x = x;
            this.y = y;

            left = lifespan;

            speed.set( 0, -40 );
            size = 4;
        }

        @Override
        public void update() {
            super.update();
            float p = left / lifespan;
            am = p > 0.8f ? (1 - p) * 5 : 1;
        }
    }
}
