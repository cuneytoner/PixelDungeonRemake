package com.watabou.pixeldungeon.sprites;

import com.watabou.pixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.Animation;

/**
 * Created by cc on 11/26/16.
 */

public class GargoyleSprite extends MobSprite {

    public GargoyleSprite() {
        super();

        texture(Assets.GARGOYLE);

        TextureFilm frames = new TextureFilm(texture, 16, 15);

        idle = new Animation(12, true);
        idle.frames(frames, 0, 1, 1, 0, 2, 2);

        run = new Animation(15, true);
        run.frames(frames, 0, 1, 1, 0, 2, 2, 3, 3);

        attack = new Animation(20, false);
        attack.frames(frames, 3, 4, 5, 6);

        die = new Animation(20, false);
        die.frames(frames, 7, 8, 9);

        play(idle);
    }

    @Override
    public int blood() {
        return 0xffd500;
    }
}

