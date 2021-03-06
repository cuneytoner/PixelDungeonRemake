/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.ui;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.scenes.PixelScene;
import com.watabou.pixeldungeon.sprites.CharSprite;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class AttackIndicator extends Tag {
	
	private static final float ENABLED	= 1.0f;
	private static final float DISABLED	= 0.3f;
	
	private static AttackIndicator instance;
	
	private CharSprite sprite = null;
	
	private static Mob lastTarget = null;
	private ArrayList<Mob> candidates = new ArrayList<>();
	
	public AttackIndicator() {
		super( DangerIndicator.COLOR );
		
		instance = this;
		
		setSize( 24, 24 );
		visible( false );
		enable( false );
	}
	
	@Override
	protected void layout() {
		super.layout();
		
		if (sprite != null) {
			sprite.x = x + (width - sprite.width()) / 2;
			sprite.y = y + (height - sprite.height()) / 2;
			PixelScene.align( sprite );
		}
	}	
	
	@Override
	public void update() {
		super.update();
		
		if (Dungeon.hero.isAlive()) {
			
			if (!Dungeon.hero.isReady()) {
				enable( false );
			}		
			
		} else {
			visible( false );
			enable( false );
		}
	}
	
	private void checkEnemies() {
		
		int heroPos = Dungeon.hero.getPos();
		candidates.clear();
		int v = Dungeon.hero.visibleEnemies();
		for (int i=0; i < v; i++) {
			Mob mob = Dungeon.hero.visibleEnemy( i );
			if (Dungeon.level.adjacent( heroPos, mob.getPos() ) && !mob.isPet()) {
				candidates.add( mob );
			}
		}
		
		if (!candidates.contains( lastTarget )) {
			if (candidates.isEmpty()) {
				lastTarget = null;
			} else {
				lastTarget = Random.element( candidates );
				updateImage();				
				flash();
			}
		} else {
			if (!bg.getVisible()) {
				flash();
			}
		}
		
		visible( lastTarget != null );
		enable( bg.getVisible() );
	}
	
	private void updateImage() {
		
		if (sprite != null) {
			sprite.killAndErase();
			sprite = null;
		}

		sprite = lastTarget.sprite();

        float scx = 1f;
        float scy = 1f;
        float scl = 1f;

        if (sprite.width() > 24.0f) {
            scx = 24.0f / sprite.width();
        }
        if (sprite.height() > 24.0f) {
            scy = 24.0f / sprite.height();
        }

        scl = Math.min(1,Math.min(scy,scx));

        sprite.setScale(scl,scl);
		sprite.idle();
		sprite.paused = true;
		add(sprite);

		sprite.x = x + (width - sprite.width()) / 2 + 1;
		sprite.y = y + (height - sprite.height()) / 2;
		PixelScene.align(sprite);
	}
	
	private boolean enabled = true;
	private void enable( boolean value ) {
		enabled = value;
		if (sprite != null) {
			sprite.alpha( value ? ENABLED : DISABLED );
		}
	}
	
	private void visible( boolean value ) {
		bg.setVisible(value);
		if (sprite != null) {
			sprite.setVisible(value);
		}
	}
	
	@Override
	protected void onClick() {
		if (enabled) {
			Dungeon.hero.handle( lastTarget.getPos() );
		}
	}
	
	public static void target( Char target ) {
		lastTarget = (Mob)target;
		instance.updateImage();
		
		HealthIndicator.instance.target( target );
	}
	
	public static void updateState() {
		if(instance != null){
			instance.checkEnemies();
		}
	}
}
