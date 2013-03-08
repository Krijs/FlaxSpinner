package org.krijs.nodes;

import org.krijs.definitions.PaintMethods;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Bank.Amount;

import static org.krijs.definitions.KeyTiles.TILE_BANK;
import static org.krijs.definitions.Items.ITEM_FLAX;
import static org.krijs.definitions.Globals.*;

public class DoBank extends Node {
	
	private PaintMethods _pm;
	
	public DoBank(PaintMethods pm) {
		_pm = pm;
	}

	@Override
	public boolean activate() {		
		return Game.isLoggedIn() && SCRIPT_RUNNING && Bank.getNearest() != null && Calculations.distanceTo(TILE_BANK) <= 5
				&& !Inventory.contains(ITEM_FLAX) && Bank.open();
	}

	@Override
	public void execute() {		
		_pm.setCurrentAction("Banking...");
		
		if(Bank.depositInventory()) {					
			if(Bank.withdraw(ITEM_FLAX, Amount.ALL)) {						
				//we're done, bye bye bank.
				Bank.close();
				//sleep(1000, 1500);
			} else {
				//We didn't withdraw... Check we have items.
				if(Bank.getItemCount(ITEM_FLAX) == 0) {
					SCRIPT_RUNNING = false;
					SCRIPT_STOP_REASON = "Out of materials";
				}
			}
		}		
	}

}
