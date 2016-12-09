package com.coner.pixeldungeon.items.stones;

import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.blobs.Blob;
import com.watabou.pixeldungeon.actors.blobs.DarkEnergy;
import com.watabou.pixeldungeon.scenes.GameScene;

/**
 * Created by cc on 12/5/16.
 */

public class WanishStone extends Stone {

    private int cellId = -1;

    @Override
    public void shatter( int cell ) {

        setKnown();

        splash( cell );
        Sample.INSTANCE.play( Assets.SND_WANISHSTONE );

        DarkEnergy dark = Blob.seed( cell, 10, DarkEnergy.class );
        GameScene.add( dark );

        Char ch = Actor.findChar( cell );
        if (ch != null) {
            ch.die(ch);
        }

    }



}
