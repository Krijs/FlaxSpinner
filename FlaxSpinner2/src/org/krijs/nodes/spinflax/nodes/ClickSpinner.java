package org.krijs.nodes.spinflax.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;

import static org.krijs.definitions.Objects.OBJECTS_SPINNER;
import static org.krijs.definitions.Widgets.WIDGET_CREATE_ITEM;

public class ClickSpinner extends Node {	

	@Override
	public boolean activate() {		
		return !WIDGET_CREATE_ITEM.validate();
	}

	@Override
	public void execute() {
		SceneEntities.getNearest(new Filter<SceneObject>() {
			public boolean accept(SceneObject entity) {
				return entity.getId() == OBJECTS_SPINNER;
			}
		}).getLocation().interact("Spin");
		sleep(1000, 1200); //account for lag
		
		while(!Players.getLocal().isIdle())
			sleep(51);	
	}

}
