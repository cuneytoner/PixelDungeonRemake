package com.coner.pixeldungeon.items.stones;

/**
 * Created by cc on 11/29/16.
 */

import com.coner.android.util.TrackedRuntimeException;
import com.coner.pixeldungeon.levels.PortalLevel;
import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.utils.GLog;

import java.io.IOException;

public class PortalStone extends Stone {

    private static final String TXT_CANNOT_USE_ON_PORTAL_LEVEL  = Game.getVar(R.string.TXT_CANNOT_USE_ON_PORTAL_LEVEL);
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
            setPortal(cell);
        }
    }


}

