package org.krijs.definitions;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path;

public final class Globals {
	public static boolean SCRIPT_RUNNING = true;
	public static String SCRIPT_STOP_REASON = "";
	
	public static void walkTo(Tile tile){                         
        Path path = Walking.findPath(tile);               
        path.traverse(); 
    }
}
