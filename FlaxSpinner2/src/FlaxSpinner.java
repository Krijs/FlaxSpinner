import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Skills;
import org.krijs.nodes.*;
import org.krijs.definitions.*;

@Manifest(authors ={"Krijs"}, name = "kFlax Evolution 0.2.1", description = "Flaxination", version = 0.1)
public class FlaxSpinner extends ActiveScript implements PaintListener {
	
	public static Tree jobContainer = null;
    public static ArrayList<Node> jobs = new ArrayList<Node>();
    public PaintMethods m = new PaintMethods(Skills.CRAFTING);

	@Override
	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;	     
	     
	     g.setColor(Color.BLACK);
	     g.fillRect(0, 0, (int) Game.getDimensions().getWidth(), 50);

	     g.setColor(Color.GRAY);
	     g.setFont(new Font("Arial", Font.BOLD, 11));
	     g.drawString("Run Time: " + m.getElapsed(), 3, 12);
	     
	     g.drawString(
	             String.format("Level: %d (+%d)",
	                     Skills.getLevel(Skills.CRAFTING), m.getLvl()),
	             123, 12);
	     g.drawString(String.format("Experience Gained: %s (%s per hour)",
	             m.getXp(), m.getXpHr()), 223, 12);
	     g.drawString("Crafting TTL: " + m.getTTL(), 483, 12);	     
	     
	     g.drawString("Flax spun: " + m.getXp() / 15, 633, 12);
	     
	     g.drawString("kFlax by Krijs v0.1", 663, 45);	
	     g.drawString("Currently: " + m.getCurrentAction(), 463, 45);
	     
	}

	@Override
	public int loop() {
		// TODO Auto-generated method stub
		
		//Wait for game window to be fully loaded before doing anything.
		if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
            return 2500;
        }
		
		if (jobContainer != null) {
            final Node job = jobContainer.state();
            if (job != null) {
                jobContainer.set(job);
                getContainer().submit(job);
                job.join();
            }
        } else {
            jobs.add(new SpinFlax(m));
            jobs.add(new AntiBan());
            jobs.add(new WalkBank(m));
            jobs.add(new DoBank(m));
            jobs.add(new WalkSpinRoom(m));
            jobs.add(new StopScript(this));
            jobContainer = new Tree(jobs.toArray(new Node[jobs.size()]));
        }
		return 200;
	}

}
