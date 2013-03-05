package org.krijs.nodes.walkbank.nodes;

import static org.krijs.definitions.Objects.OBJECTS_STAIRCASE_LOWER;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Plane1 extends Node {

	@Override
	public boolean activate() {		
		Player p = Players.getLocal();
		return p.getPlane() == 1 && !p.isMoving();
	}

	@Override
	public void execute() {
		SceneObject stairsLower = 
				SceneEntities.getNearest(new Filter<SceneObject>() {
					public boolean accept(SceneObject entity) {
						return entity.getId() == OBJECTS_STAIRCASE_LOWER;
					}
				});	
		if(!stairsLower.isOnScreen()) 
			Camera.turnTo(stairsLower);


		stairsLower.interact("Climb-up");
		sleep(1000,1200); //account for lag.
		
		//should stop it spam clicking...
		while(!Players.getLocal().isIdle())
			sleep(50);
	}

}
