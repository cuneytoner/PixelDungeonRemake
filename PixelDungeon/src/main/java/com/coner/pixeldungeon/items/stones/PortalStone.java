package com.coner.pixeldungeon.items.stones;

/**
 * Created by cc on 11/29/16.
 */

import com.coner.pixeldungeon.remake.R;
import com.coner.pixeldungeon.utils.DungeonGenerator;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.utils.GLog;

public class PortalStone extends Stone {

    private static final String TXT_CANNOT_USE_ON_PORTAL_LEVEL  = Game.getVar(R.string.TXT_CANNOT_USE_ON_PORTAL_LEVEL);
    private static final String TXT_CANNOT_USE_SECOND_PORTAL_STONE  = Game.getVar(R.string.TXT_CANNOT_USE_SECOND_PORTAL_STONE);
    private int cellId = -1;

    protected void setPortal(int cell)
    {
        Dungeon.level.addPortalEnterance(cell);
    }

    @Override
    protected void useStone(int cell) {
        if (Dungeon.level.levelId=="portallevel")
        {
            GLog.w( TXT_CANNOT_USE_ON_PORTAL_LEVEL );
        }
        else {
            if (DungeonGenerator.isPortalActive(Dungeon.level.levelId)) {
                GLog.w( TXT_CANNOT_USE_SECOND_PORTAL_STONE );
            }
            else
            {
                Sample.INSTANCE.play(Assets.SND_PORTALSTONE);
                setPortal(cell);
            }
        }
    }

}

