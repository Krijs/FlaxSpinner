package org.krijs.nodes.spinflax.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;

public class ToggleAbilityBar extends Node {

	@Override
	public boolean activate() {
		return Game.isLoggedIn() && 
				Widgets.get(640, 30).isOnScreen();
	}

	@Override
	public void execute() {
		Widgets.get(640, 30).click(true);	
	}

}
