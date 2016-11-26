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
import com.watabou.pixeldungeon.actors.hero.HeroSubClass;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.utils.GLog;

public class PaladinArmor extends ClassArmor {

    private static final String TXT_NOT_CLERIC = Game.getVar(R.string.ClericArmor_NotCleric);
    private static final String AC_SPECIAL = Game.getVar(R.string.ClericArmor_ACSpecial);

    public PaladinArmor()
    {
        name = Game.getVar(R.string.ClericArmor_Name);
        image = 22;
    }

    @Override
    public String special() {
        return AC_SPECIAL;
    }

    @Override
    public void doSpecial() {
    }

    @Override
    public boolean doEquip( Hero hero ) {
        if (hero.heroClass == HeroClass.CLERIC && hero.subClass == HeroSubClass.PALADIN) {
            return super.doEquip( hero );
        } else {
            GLog.w( TXT_NOT_CLERIC );
            return false;
        }
    }

    @Override
    public String desc() {
        return Game.getVar(R.string.ClericArmor_Desc);
    }
}