package com.watabou.pixeldungeon.items.armor;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.blobs.Blob;
import com.watabou.pixeldungeon.actors.blobs.Regrowth;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.hero.HeroClass;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.utils.GLog;

public class ClericArmor extends ClassArmor {

    private static final String TXT_NOT_Cleric = Game.getVar(R.string.ClericArmor_NotCleric);
    private static final String AC_SPECIAL = Game.getVar(R.string.ClericArmor_ACSpecial);

    public ClericArmor() {
        image = 17;
        hasHelmet = true;
    }

    @Override
    public String special() {
        return AC_SPECIAL;
    }

    @Override
    public void doSpecial() {

        for (Mob mob : Dungeon.level.mobs) {
            if (Dungeon.level.fieldOfView[mob.getPos()]) {
                GameScene.add( Blob.seed( mob.getPos(), 100, Regrowth.class ) );
            }
        }

        getCurUser().hp(getCurUser().hp() - (getCurUser().hp() / 3));

        getCurUser().spend( Actor.TICK );
        getCurUser().getSprite().operate( getCurUser().getPos() );
        getCurUser().busy();

        Sample.INSTANCE.play( Assets.SND_READ );

        GameScene.add( Blob.seed( getCurUser().getPos(), 100, Regrowth.class ) );
    }

    @Override
    public boolean doEquip( Hero hero ) {
        if (hero.heroClass == HeroClass.CLERIC) {
            return super.doEquip( hero );
        } else {
            GLog.w( TXT_NOT_Cleric );
            return false;
        }
    }

    @Override
    public String desc() {
        return Game.getVar(R.string.ClericArmor_Desc);
    }
}