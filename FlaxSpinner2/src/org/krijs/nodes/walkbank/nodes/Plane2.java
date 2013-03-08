package org.krijs.nodes.walkbank.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.Player;

import static org.krijs.definitions.KeyTiles.TILE_BANK;
import org.krijs.definitions.Globals;

public class Plane2 extends Node {

	@Override
	public boolean activate() {
		Player p = Players.getLocal();
		return Game.isLoggedIn() && 
				p.getPlane() == 2 && !p.isMoving();
	}

	@Override
	public void execute() {		
		Globals.walkTo(TILE_BANK);
	}
}
