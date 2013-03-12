package org.krijs.definitions;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path;

public final class Globals {
	public static boolean scriptRunning = true;
	public static String scriptStopReason = "";
	
	public static void walkTo(Tile tile){                         
        Path path = Walking.findPath(tile);               
        path.traverse(); 
    }
}
