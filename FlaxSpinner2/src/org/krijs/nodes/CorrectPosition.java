package org.krijs.nodes;

import static org.krijs.definitions.Globals.scriptRunning;

import java.util.ArrayList;

import org.krijs.definitions.PaintMethods;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.krijs.nodes.correctional.*;


public class CorrectPosition extends Node {
	private PaintMethods _pm;
	private static Tree jobContainer = null;
	private static ArrayList<Node> jobs = new ArrayList<Node>();
	
	public CorrectPosition(PaintMethods pm) {
		_pm = pm;
	}

	@Override
	public boolean activate() {		
		int plane = Players.getLocal().getPlane();
		return Game.isLoggedIn() && scriptRunning && plane < 1 || plane > 2;
	}

	@Override
	public void execute() {	
		_pm.setCurrentAction("Correcting current position");
		jobs.add(new Plane0());
        jobs.add(new Plane3());        
        jobContainer = new Tree(jobs.toArray(new Node[jobs.size()]));
        
        final Node job = jobContainer.state();
        if (job != null) {
            jobContainer.set(job);
            getContainer().submit(job);
            job.join();
        }
	}	
}
