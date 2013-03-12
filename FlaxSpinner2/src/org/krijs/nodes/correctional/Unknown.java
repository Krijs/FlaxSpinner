package org.krijs.nodes.correctional;

import static org.krijs.definitions.Objects.OBJECTS_BANK_LADDER;
import static org.krijs.definitions.Objects.OBJECTS_STAIRCASE_GROUND;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;

import static org.krijs.definitions.Globals.*;


public class Unknown extends Node {	

	@Override
	public boolean activate() {
		return 	Game.isLoggedIn() && 
				!(SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_BANK_LADDER;
					}
				}).validate() && 
				SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_STAIRCASE_GROUND;
					}
				}).validate());
	}

	@Override
	public void execute() {		
		scriptRunning = false;
		scriptStopReason = "Unknown location, stopping...";		
	}
}
