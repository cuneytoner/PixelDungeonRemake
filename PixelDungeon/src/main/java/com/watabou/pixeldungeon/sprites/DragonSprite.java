package com.watabou.pixeldungeon.sprites;

import com.watabou.noosa.Animation;
import com.watabou.noosa.TextureFilm;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.effects.Speck;

/**
 * Created by cc on 11/23/16.
 */

public class DragonSprite extends MobSprite {

    public DragonSprite() {
        super();

        texture( Assets.DRAGON );

        TextureFilm frames = new TextureFilm( texture, 32, 32 );

        idle = new Animation( 10, true );
        idle.frames( frames, 0, 1 );

        run = new Animation( 10, true );
        run.frames( frames, 2, 3 );

        attack = new Animation( 15, false );
        attack.frames( frames, 8,9,10,11 );

        die = new Animation( 20, false );
        die.frames( frames, 4,5,4,5,6,6,7);

        play( idle );
    }

    @Override
    public void onComplete( Animation anim ) {

        super.onComplete( anim );

        if (anim == die) {
            emitter().burst( Speck.factory( Speck.MIST ), 15 );
        }
    }

    @Override
    public int blood() {
        return 0xFFFFFF88;
    }
}
