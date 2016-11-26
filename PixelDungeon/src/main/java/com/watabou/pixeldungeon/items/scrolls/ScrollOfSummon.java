package com.watabou.pixeldungeon.items.scrolls;

import com.coner.pixeldungeon.mobs.spiders.SpiderServant;
import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Created by cc on 30.10.2016.
 */

public class ScrollOfSummon extends Scroll {

    @Override
    protected void doRead() {
        Hero hero = getCurUser();

        Sample.INSTANCE.play(Assets.SND_SUMMON);

        //GLog.w(Game.getVar(R.string.ScrollOfChallenge_Info1));
        Mob pet;
        try {
            pet = Mob.randomPet(hero);
            Mob.makePet(pet, hero);
        } catch (Exception e) {
            pet = Mob.makePet(new SpiderServant(), hero);
        }

        setKnown();

        pet.setPos(hero.getPos());
        Dungeon.level.spawnMob(pet);
        getCurUser().spendAndNext( TIME_TO_READ );
    }
}
