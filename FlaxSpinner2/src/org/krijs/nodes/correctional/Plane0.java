package org.krijs.nodes.correctional;

import static org.krijs.definitions.Objects.OBJECTS_STAIRCASE_GROUND;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Plane0 extends Node {	

	@Override
	public boolean activate() {
		return Players.getLocal().getPlane() == 0 &&
				SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_STAIRCASE_GROUND;
					}
				}).validate();
	}

	@Override
	public void execute() {		
		SceneObject stairsGround =
				SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_STAIRCASE_GROUND;
					}
				});
		
		if(!stairsGround.isOnScreen()) 
			Camera.turnTo(stairsGround);
		
		stairsGround.interact("Climb-up");
		sleep(1000, 1200); //account for lag
		
		while(!Players.getLocal().isIdle())
			sleep(50);	
	}
}
