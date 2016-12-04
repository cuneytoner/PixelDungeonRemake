package com.coner.pixeldungeon.items.stones;

/**
 * Created by cc on 11/29/16.
 */

import com.coner.android.util.TrackedRuntimeException;
import com.watabou.pixeldungeon.Dungeon;

import java.io.IOException;

public class PortalStone extends Stone {

    private int cellId = -1;

    protected void setPortal(int cell)
    {
        Dungeon.level.addPortalEnterance(cell);
    }

    @Override
    protected void useStone(int cell) {
        setPortal(cell);
    }


}

