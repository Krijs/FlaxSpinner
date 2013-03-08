package org.krijs.nodes.spinflax.nodes.flaxwidget.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;
import static org.krijs.definitions.Items.*;
import static org.krijs.definitions.Widgets.WIDGET_CREATE_ITEM;
import static org.krijs.definitions.Widgets.WIDGET_CREATE_ITEM_SELECT;

public class SetCraft extends Node {

	@Override
	public boolean activate() {		
		return Game.isLoggedIn() && 
				Inventory.contains(ITEM_WOOL, ITEM_HAIR, ITEM_MAGIC_ROOTS, ITEM_SINEW)
				&& !WIDGET_CREATE_ITEM.getChild(59).getChild(3).getText().trim().equalsIgnoreCase("flax")
				&& WIDGET_CREATE_ITEM_SELECT.validate();
	}

	@Override
	public void execute() {
		WIDGET_CREATE_ITEM_SELECT.getChild(44).getChild(6).click(true);		
	}

}
