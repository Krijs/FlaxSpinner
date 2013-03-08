package org.krijs.nodes;

import java.util.ArrayList;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;

import org.krijs.definitions.PaintMethods;
import static org.krijs.definitions.Widgets.*;
import static org.krijs.definitions.Objects.OBJECTS_SPINNER;
import static org.krijs.definitions.Globals.SCRIPT_RUNNING;
import static org.krijs.definitions.Items.*;
import static org.krijs.definitions.KeyTiles.TILE_SPIN;
import org.krijs.nodes.spinflax.nodes.*;

public class SpinFlax extends Node {
	
	private PaintMethods _pm;
	private static Tree jobContainer = null;
	private static ArrayList<Node> jobs = new ArrayList<Node>();
	
	public SpinFlax(PaintMethods pm) {
		_pm = pm;
	}

	@Override
	public boolean activate() {	
		SceneObject spinner = SceneEntities.getNearest(new Filter<SceneObject>() {
			public boolean accept(SceneObject entity) {
				return entity.getId() == OBJECTS_SPINNER;
			}
		});
		return Game.isLoggedIn() && 
				SCRIPT_RUNNING && Inventory.contains(ITEM_FLAX) && Players.getLocal().getPlane() == 1
				 && Calculations.distanceTo(TILE_SPIN) <= 5 && 
				 spinner.isOnScreen() && !WIDGET_MAKE_ITEM.validate();
	}

	@Override
	public void execute() {		
		_pm.setCurrentAction("Spinning Flax...");
		
		jobs.add(new ToggleAbilityBar());
        jobs.add(new ClickSpinner());
        jobs.add(new HandleFlaxWidget());
        jobContainer = new Tree(jobs.toArray(new Node[jobs.size()]));
        
        final Node job = jobContainer.state();
        if (job != null) {
            jobContainer.set(job);
            getContainer().submit(job);
            job.join();
        }
	}
}
