package org.krijs.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;

import static org.krijs.definitions.Globals.SCRIPT_RUNNING;
import static org.krijs.definitions.Widgets.WIDGET_MAKE_ITEM;

public class AntiBan extends Node {

	@Override
	public boolean activate() {		
		return Game.isLoggedIn() && SCRIPT_RUNNING && WIDGET_MAKE_ITEM.validate();
	}

	@Override
	public void execute() {
		//code shamelessly kanged from some pb thread
		if (Random.nextInt(1, 100) >= 99) {        		
			int num = Random.nextInt(1, 200);
			int degree = Random.nextInt(1, 360);
			int pitch = Random.nextInt(1, 360);			
			
			if (num >= 1 && num <= 25) {
				Camera.setNorth(); //Turns North
			} else if (num > 25 && num <= 50) {
				Camera.setAngle(degree); //Sets the Camera Angle 
			} else if (num > 50 && num <= 75) {
				Camera.setPitch(pitch);//Sets the Camera Pitch
			} else if (num > 75 && num <= 100){
				Tabs.STATS.open(false); //Opens the Stats Tab				
				sleep(1500,2000);
			} else if (num > 100 && num <= 125) {
				Tabs.EQUIPMENT.open(false); //Opens the Equipment Tab
				sleep(1500,2000);
			} else if (num > 125 && num <= 150) {
				Tabs.FRIENDS.open(false); //Opens the Friends Tab
				sleep(1000,1500);
			} else if (num > 150 && num <= 175) {
				Tabs.PRAYER.open(false); //Opens the Prayer Icon				
				sleep(1500,2000);
			} else {
				Camera.setAngle(degree); //Sets the Camera Angle
				Camera.setPitch(pitch); //Sets the Camera Pitch
			}
		}				
	}
}
