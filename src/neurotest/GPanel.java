package neurotest;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GPanel extends JPanel {
	Game game;
	public GPanel(Game g){
		game = g;
	}

	public Dimension getPreferredSize(){
		return new Dimension(200, 400);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, 200, 400);
		
		g.setColor(new Color(0, 0, 0));
		g.drawRect(0, 0, 50, 100);
		g.drawRect(50, 0, 50, 100);
		g.drawRect(100, 0, 50, 100);
		g.drawRect(150, 0, 50, 100);
		
		g.drawLine(0, (int) Math.round(game.tube1*100), 50, (int) Math.round(game.tube1*100));
		g.drawLine(50, (int) Math.round(game.tube2*100), 100, (int) Math.round(game.tube2*100));
		g.drawLine(100, (int) Math.round(game.tube3*100), 150, (int) Math.round(game.tube3*100));
		g.drawLine(150, (int) Math.round(game.tube4*100), 200, (int) Math.round(game.tube4*100));
		
		g.fillRect((int) Math.floor(game.catcherPosition*0.999)*50, 125, 50, 25);
		
		g.fillRect(0, 175, (int) Math.round(game.energy/15*200), 25);
		
		g.drawString("ENERGY - "+game.energy, 10, 225);
		g.drawString("TIME - "+game.time, 10, 240);
		g.drawString("OUTPUT - "+game.velocity, 10, 255);
		g.drawString("POSITION - "+game.catcherPosition, 10, 270);
	}
}
