package com.watabou.pixeldungeon.actors.blobs;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.effects.BlobEmitter;
import com.watabou.pixeldungeon.effects.particles.DarknessParticle;

public class Darkness extends Blob {
	
	@Override
	protected void evolve() {
		int from = getWidth() + 1;
		int to   = getLength() - getWidth() - 1;

		for (int pos=from; pos < to; pos++) {
			if (cur[pos] > 0) {
				off[pos] = cur[pos];
				volume += off[pos];
			} else {
				off[pos] = 0;
			}
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );	
		emitter.start(DarknessParticle.FACTORY, 0.9f, 0 );
	}
	
	@Override
	public String tileDesc() {
		return Game.getVar(R.string.Darkness_Info);
	}
}
