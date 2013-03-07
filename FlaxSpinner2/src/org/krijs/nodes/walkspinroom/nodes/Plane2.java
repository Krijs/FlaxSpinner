package org.krijs.nodes.walkspinroom.nodes;

import java.util.ArrayList;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.Player;

import org.krijs.nodes.walkspinroom.nodes.plane2.nodes.*;


public class Plane2 extends Node {
	
	private static Tree jobContainer = null;
	private static ArrayList<Node> jobs = new ArrayList<Node>();

	@Override
	public boolean activate() {
		Player p = Players.getLocal();
		return p.getPlane() == 2 && !p.isMoving() && Game.isLoggedIn();
	}

	@Override
	public void execute() {		
		jobs.add(new StairsUnreachable());
        jobs.add(new UseStairs());
        jobContainer = new Tree(jobs.toArray(new Node[jobs.size()]));
        
        final Node job = jobContainer.state();
        if (job != null) {
            jobContainer.set(job);
            getContainer().submit(job);
            job.join();
        }		
	}
}
