package org.krijs.nodes.correctional;

import static org.krijs.definitions.Objects.OBJECTS_BANK_LADDER;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Plane3 extends Node {	

	@Override
	public boolean activate() {
		return Game.isLoggedIn() && 
				Players.getLocal().getPlane() == 3 &&
				SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_BANK_LADDER;
					}
				}).validate();
	}

	@Override
	public void execute() {		
		SceneObject ladder =
				SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_BANK_LADDER;
					}
				});
		if(!ladder.isOnScreen()) 
			Camera.turnTo(ladder);
		
		
		ladder.interact("Climb-down");
		sleep(1000, 1200); //account for lag
		
		while(!Players.getLocal().isIdle() && Game.isLoggedIn())
			sleep(50);	
	}
}
