package neurotest;
import javax.swing.JFrame;

public class Neurotest {
	public static void main(String[] args) {
		
		boolean realtime = false;
		for(String s : args){
			if(s.equals("-r")) realtime = true;
			
		}
		
		JFrame frame = new JFrame("neurotest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game game = new Game();
		game.realtime = realtime;
		GPanel panel = new GPanel(game);
		frame.add(panel);
		panel.validate();
		frame.pack();
		game.panel = panel;
		frame.setVisible(true);
		
		new Thread(game).start();
	}
}