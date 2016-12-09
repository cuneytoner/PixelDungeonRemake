package com.watabou.pixeldungeon.actors.blobs;

/**
 * Created by cc on 12/5/16.
 */

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.effects.BlobEmitter;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.effects.particles.DarkEnergyParticle;
import com.watabou.pixeldungeon.items.Heap;

public class DarkEnergy extends Blob {

    @Override
    public void use( BlobEmitter emitter ) {
        super.use( emitter );
        emitter.start(DarkEnergyParticle.FACTORY, 0.3f, 22 );
    }

    @Override
    public String tileDesc() {
        return Game.getVar(R.string.DarkEnergy_Info);
    }
}

