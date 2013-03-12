package org.krijs.nodes;

import static org.krijs.definitions.Globals.scriptRunning;
import static org.krijs.definitions.Items.ITEM_FLAX;
import static org.krijs.definitions.KeyTiles.TILE_SPIN;

import java.util.ArrayList;

import org.krijs.definitions.PaintMethods;
import org.krijs.nodes.walkspinroom.nodes.*;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class WalkSpinRoom extends Node {
	
	private PaintMethods _pm;
	private static Tree jobContainer = null;
	private static ArrayList<Node> jobs = new ArrayList<Node>();
	
	public WalkSpinRoom(PaintMethods pm) {
		_pm = pm;
	}

	@Override
	public boolean activate() {		
		return Game.isLoggedIn() && 
				scriptRunning && Inventory.contains(ITEM_FLAX) &&
				Calculations.distanceTo(TILE_SPIN) >= 5 && Bank.close();
				//Only activate when we know the bank has closed. Script has been getting
				//stuck due to the bank being open!
	}

	@Override
	public void execute() {		
		_pm.setCurrentAction("Walking to spin room...");
		
		jobs.add(new Plane1());
        jobs.add(new Plane2());
        jobContainer = new Tree(jobs.toArray(new Node[jobs.size()]));
        
        final Node job = jobContainer.state();
        if (job != null) {
            jobContainer.set(job);
            getContainer().submit(job);
            job.join();
        }		
	}

}
