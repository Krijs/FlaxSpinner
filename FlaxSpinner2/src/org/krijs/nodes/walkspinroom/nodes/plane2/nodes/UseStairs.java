package org.krijs.nodes.walkspinroom.nodes.plane2.nodes;

import static org.krijs.definitions.Objects.OBJECTS_STAIRCASE_UPPER;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class UseStairs extends Node {

	@Override
	public boolean activate() {		
		return SceneEntities.getNearest(new Filter<SceneObject>() {
			public boolean accept(SceneObject entity) {
				return entity.getId() == OBJECTS_STAIRCASE_UPPER;
			}
		}).isOnScreen();
	}

	@Override
	public void execute() {
		SceneEntities.getNearest(new Filter<SceneObject>() {
			public boolean accept(SceneObject entity) {
				return entity.getId() == OBJECTS_STAIRCASE_UPPER;
			}
		}).interact("Climb-down");	
		sleep(1000,1200); //account for lag.
		
		//should stop it spam clicking...
		while(!Players.getLocal().isIdle())
			sleep(50);
	}

}
