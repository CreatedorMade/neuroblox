package neurotest;

import neuroblox.Brain;

public class neurotest {

	public static void main(String[] args) {
		int iterations = 0;
		Brain winner;
		while(true){
			Brain b = new Brain(8);
			for(int i = 0; i < 128; i++) b.placeNeuron(new passNeuron());
			
			b.setInputAt(10, 1, 1, Brain.TOP);
			
			for(int i = 0; i < 50; i++) b.tick();

			winner = b;
			if(b.getOutputAt(1, 1, Brain.BTM) >= 5) break;
			
			b.dump();
			iterations++;
		}
		winner.dump();
		System.out.println("Test (finally) complete after "+(iterations+1)+" iterations");
	}

}
