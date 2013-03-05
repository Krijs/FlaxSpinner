package org.krijs.nodes.walkspinroom.nodes;

import static org.krijs.definitions.KeyTiles.TILE_SPIN;

import org.krijs.definitions.Globals;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.Player;

public class Plane1 extends Node {

	@Override
	public boolean activate() {		
		Player p = Players.getLocal();
		return p.getPlane() == 1 && !p.isMoving() && Calculations.distanceTo(TILE_SPIN) >= 5;
	}

	@Override
	public void execute() {
		Globals.walkTo(TILE_SPIN); 		
	}
}
