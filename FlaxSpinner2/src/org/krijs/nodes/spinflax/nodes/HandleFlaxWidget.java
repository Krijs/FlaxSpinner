package org.krijs.nodes.spinflax.nodes;

import java.util.ArrayList;

import org.krijs.nodes.spinflax.nodes.flaxwidget.nodes.*;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.methods.Game;

import static org.krijs.definitions.Widgets.WIDGET_CREATE_ITEM;

public class HandleFlaxWidget extends Node {
	
	private static Tree jobContainer = null;
	private static ArrayList<Node> jobs = new ArrayList<Node>();

	@Override
	public boolean activate() {		
		return Game.isLoggedIn() && 
				WIDGET_CREATE_ITEM.validate();
	}

	@Override
	public void execute() {		
		jobs.add(new SetCraft());
        jobs.add(new FlaxWidget());        
        jobContainer = new Tree(jobs.toArray(new Node[jobs.size()]));
        
        final Node job = jobContainer.state();
        if (job != null) {
            jobContainer.set(job);
            getContainer().submit(job);
            job.join();
        }
	}

}
