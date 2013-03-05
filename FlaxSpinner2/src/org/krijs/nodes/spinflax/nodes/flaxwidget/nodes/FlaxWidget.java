package org.krijs.nodes.spinflax.nodes.flaxwidget.nodes;

import static org.krijs.definitions.Widgets.WIDGET_CREATE_ITEM;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

public class FlaxWidget extends Node {

	@Override
	public boolean activate() {		
		return WIDGET_CREATE_ITEM.getChild(59).getChild(3).getText().trim().equalsIgnoreCase("flax");
	}

	@Override
	public void execute() {
		WIDGET_CREATE_ITEM.getChild(12).click(true);
		sleep(1000, 1200); //account for lag
		
		while(!Players.getLocal().isIdle())
			sleep(50);	
	}

}
