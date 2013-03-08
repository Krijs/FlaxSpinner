package org.krijs.nodes.walkspinroom.nodes.plane2.nodes;

import org.krijs.definitions.Globals;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;

import static org.krijs.definitions.Objects.OBJECTS_STAIRCASE_UPPER;
import static org.krijs.definitions.KeyTiles.TILE_STAIRS_UPPER;

public class StairsUnreachable extends Node {

	@Override
	public boolean activate() {		
		return Game.isLoggedIn() && 
				!SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_STAIRCASE_UPPER;
					}
				}).isOnScreen();
	}

	@Override
	public void execute() {
		Globals.walkTo(TILE_STAIRS_UPPER);		
	}
}
