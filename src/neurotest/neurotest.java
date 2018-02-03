package neurotest;

import neuroblox.Brain;

public class neurotest {

	public static void main(String[] args) {
		int iterations = 0;
		while(true){
			Brain b = new Brain(3);
			for(int i = 0; i < 9; i++) b.placeNeuron(new passNeuron());
			
			b.setInputAt(10, 1, 1, Brain.TOP);
			
			for(int i = 0; i < 50; i++) b.tick();
			
			if(b.getOutputAt(1, 1, Brain.BTM) >= 5) break;
			
			iterations++;
		}
		System.out.println("Test (finally) complete after "+iterations+" iterations");
	}

}
