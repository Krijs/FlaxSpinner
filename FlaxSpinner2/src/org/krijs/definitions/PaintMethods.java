package org.krijs.definitions;

import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;

public class PaintMethods {
	
	private SkillData _sd;
	private Timer _t;
	private int _skill;
	private String _currentAction = "Idle";
	
	public PaintMethods(int skill) {
		_sd = new SkillData();
		_t = new Timer(0);
		_skill = skill; 
	}
	
	public String getElapsed() {
		return _t.toElapsedString();
	}
	
	public String getTTL() {
		  return Time.format(_sd.timeToLevel(SkillData.Rate.HOUR, _skill));
	}

	 public int getXpHr() {
	      return _sd.experience(SkillData.Rate.HOUR, _skill);
	 }		
	 
	 public int getXp() {
	      return _sd.experience(_skill);
	 }	
	 
	 public int getLvl() {
	      return _sd.level(_skill);
	 }

	public String getCurrentAction() {
		return _currentAction;
	}

	public void setCurrentAction(String currentAction) {
		_currentAction = currentAction;
	}		 
	 
}
