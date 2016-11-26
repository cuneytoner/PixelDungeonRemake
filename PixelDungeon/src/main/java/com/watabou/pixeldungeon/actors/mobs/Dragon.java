package com.watabou.pixeldungeon.actors.mobs;

/**
 * Created by cc on 11/25/16.
 */


import com.coner.pixeldungeon.items.chaos.ChaosCrystal;
import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.blobs.Blob;
import com.watabou.pixeldungeon.actors.blobs.ToxicGas;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Paralysis;
import com.watabou.pixeldungeon.effects.CellEmitter;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.effects.particles.ElmoParticle;
import com.watabou.pixeldungeon.items.keys.SkeletonKey;
import com.watabou.pixeldungeon.items.rings.RingOfThorns;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.sprites.DragonSprite;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class Dragon extends Boss {
    public Dragon() {
        spriteClass = DragonSprite.class;

        hp(ht(200));
        EXP = 30;
        defenseSkill = 18;

        float dice = Random.Float();
        if( dice < 0.5 ) {
            loot = new ChaosCrystal();
        } else {
            loot = new RingOfThorns().random();
        }

        lootChance = 0.333f;

        IMMUNITIES.add( ToxicGas.class );
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 18, 24 );
    }

    @Override
    public int attackSkill( Char target ) {
        return 28;
    }

    @Override
    public int dr() {
        return 10;
    }

    @Override
    public boolean act() {

        GameScene.add( Blob.seed( getPos(), 30, ToxicGas.class ) );

        return super.act();
    }

    @Override
    public void move( int step ) {
        super.move( step );

        if (Dungeon.level.map[step] == Terrain.INACTIVE_TRAP && hp() < ht()) {

            hp(hp() + Random.Int( 1, ht() - hp() ));
            getSprite().emitter().burst( ElmoParticle.FACTORY, 5 );

            if (Dungeon.visible[step] && Dungeon.hero.isAlive()) {
                GLog.n(Game.getVar(R.string.Dragon_Info1));
            }
        }

        int cell = step + Level.NEIGHBOURS8[Random.Int( Level.NEIGHBOURS8.length )];

        if (Dungeon.visible[cell]) {
            CellEmitter.get( cell ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
            Camera.main.shake( 3, 0.7f );
            Sample.INSTANCE.play( Assets.SND_ROCKS );

            if (Dungeon.level.water[cell]) {
                GameScene.ripple( cell );
            } else if (Dungeon.level.map[cell] == Terrain.EMPTY) {
                Dungeon.level.set( cell, Terrain.EMPTY_DECO );
                GameScene.updateMap( cell );
            }
        }

        Char ch = Actor.findChar( cell );
        if (ch != null && ch != this) {
            Buff.prolong( ch, Paralysis.class, 2 );
        }
    }

    @Override
    public void die( Object cause ) {

        super.die( cause );

        GameScene.bossSlain();
        Dungeon.level.drop( new SkeletonKey(), getPos() ).sprite.drop();

        Badges.validateBossSlain(Badges.Badge.BOSS_SLAIN_3);

        yell(Game.getVar(R.string.Dragon_Info2));
    }

    @Override
    public void notice() {
        super.notice();
        yell(Game.getVar(R.string.Dragon_Info3));
    }
}



