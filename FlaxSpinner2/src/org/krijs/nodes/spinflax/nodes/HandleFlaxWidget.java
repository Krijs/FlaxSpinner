package org.krijs.nodes.spinflax.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

import static org.krijs.definitions.Widgets.WIDGET_CREATE_ITEM;

public class HandleFlaxWidget extends Node {

	@Override
	public boolean activate() {		
		return WIDGET_CREATE_ITEM.validate();
	}

	@Override
	public void execute() {		
		WIDGET_CREATE_ITEM.getChild(12).click(true);
		sleep(1000, 1200); //account for lag
		
		while(!Players.getLocal().isIdle())
			sleep(50);
	}

}
