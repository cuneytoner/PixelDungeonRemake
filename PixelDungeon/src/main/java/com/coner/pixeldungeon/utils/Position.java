package com.coner.pixeldungeon.utils;

import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public class Position implements Bundlable {

	public int    levelDepth = -1;
	public int    cellId     = -1;
	public String levelKind  = "DeadEndLevel";
	public String levelId;
	public int    xs         = 32;
	public int    ys         = 32;


	static final String LEVEL_DEPTH = "levelDepth";
	static final String LEVEL_KIND  = "levelKind";
	static final String LEVEL_ID    = "levelId";
	static final String CELL_ID     = "cellId";

	public Position(String _levelKind, String _levelId, int _levelDepth, int _cellId){
		levelKind  = _levelKind;
		levelId    = _levelId;
		levelDepth = _levelDepth;
		cellId     = _cellId;
	}

	public Position(Position pos) {
		this(pos.levelKind,pos.levelId, pos.levelDepth, pos.cellId);
	}

	public Position() {
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		levelDepth = bundle.getInt(LEVEL_DEPTH);
		levelKind  = bundle.getString(LEVEL_KIND);
		cellId     = bundle.getInt(CELL_ID);
		levelId    = bundle.optString(LEVEL_ID, DungeonGenerator.guessLevelId(levelKind,levelDepth));
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		bundle.put(LEVEL_DEPTH, levelDepth);
		bundle.put(CELL_ID,     cellId);
		bundle.put(LEVEL_KIND,  levelKind);
	}
	
	public boolean dontPack() {
		return false;
	}
	
}
