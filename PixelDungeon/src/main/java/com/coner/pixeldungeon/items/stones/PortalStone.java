package com.coner.pixeldungeon.items.stones;

/**
 * Created by cc on 11/29/16.
 */

import com.coner.pixeldungeon.levels.PredesignedLevel;
import com.coner.pixeldungeon.utils.DungeonGenerator;
import com.coner.pixeldungeon.utils.Position;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.Statistics;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.PortalLevel;
import com.watabou.pixeldungeon.scenes.InterlevelScene;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PortalStone extends Stone {

    private void usePortal() throws IOException {
        Actor.fixTime();

        Position next = new Position("PortalLevel","portallevel",0,0);

        Dungeon.level.removePets();
        Dungeon.saveLevel();
        Dungeon.depth=next.levelDepth;

        Level level = DungeonGenerator.createLevel(next);
        level.create();

        Game.switchScene( InterlevelScene.class );
        Dungeon.switchLevel(level, level.randomRespawnCell(), next.levelId);

//        Dungeon.hero.spawnPets();


    }

    @Override
    protected void doUse() {
        Hero hero = getCurUser();

        Sample.INSTANCE.play(Assets.SND_PORTALSTONE);

        // TODO: uses
        try {
            usePortal();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setKnown();

        getCurUser().spendAndNext( TIME_TO_USESTONE );
    }

}

