package org.krijs.nodes;

import static org.krijs.definitions.Globals.*;

import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;

public class StopScript extends Node {
	
	private ActiveScript _activeScript;
	
	public StopScript(ActiveScript activeScript) {
		_activeScript = activeScript;
	}

	@Override
	public boolean activate() {		
		return !SCRIPT_RUNNING;
	}

	@Override
	public void execute() {
		_activeScript.log.info(SCRIPT_STOP_REASON);
		_activeScript.stop();		
	}

}
