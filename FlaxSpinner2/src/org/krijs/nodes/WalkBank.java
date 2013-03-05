package org.krijs.nodes;

import static org.krijs.definitions.Globals.SCRIPT_RUNNING;
import static org.krijs.definitions.KeyTiles.TILE_BANK;
import static org.krijs.definitions.Items.ITEM_FLAX;

import java.util.ArrayList;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.tab.Inventory;
import org.krijs.definitions.PaintMethods;
import org.krijs.nodes.walkbank.nodes.*;

public class WalkBank extends Node {
	
	private PaintMethods _pm;
	private static Tree jobContainer = null;
	private static ArrayList<Node> jobs = new ArrayList<Node>();
	
	public WalkBank(PaintMethods pm) {
		_pm = pm;
	}

	@Override
	public boolean activate() {		
		return SCRIPT_RUNNING && Calculations.distanceTo(TILE_BANK) >= 5 
				&& !Inventory.contains(ITEM_FLAX);
	}

	@Override
	public void execute() {		
		_pm.setCurrentAction("Walking to bank...");
		
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
