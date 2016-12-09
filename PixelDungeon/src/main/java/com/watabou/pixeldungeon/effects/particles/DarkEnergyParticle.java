package com.watabou.pixeldungeon.effects.particles;

/**
 * Created by cc on 12/5/16.
 */

import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.noosa.particles.Emitter.Factory;
import com.watabou.utils.Random;

public class DarkEnergyParticle extends PixelParticle{

    public static final Emitter.Factory FACTORY = new Factory() {
        @Override
        public void emit( Emitter emitter, int index, float x, float y ) {
            ((DarkEnergyParticle)emitter.recycle( DarkEnergyParticle.class )).reset( x, y );
        }
    };

    public DarkEnergyParticle() {
        color( 0xCC0000 );
        lifespan = 0.8f;

        acc.set( 0, +40 );
    }

    public void reset( float x, float y ) {
        revive();

        this.x = x;
        this.y = y;

        left = lifespan;

        size = 4;
        speed.set( 0 );
    }

    @Override
    public void update() {
        super.update();
        float p = left / lifespan;
        am = p > 0.6f ? (1 - p) * 2.5f : 1;
    }}
