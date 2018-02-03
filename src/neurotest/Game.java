package neurotest;

import neuroblox.*;

public class Game implements Runnable {
	public boolean finished = false;
	
	boolean setRealtime = false;
	
	boolean fromBatch = false;
	boolean generated = false;
	
	Brain[] batch = new Brain[4096];
	double[] scores = new double[4096];
	
	Brain[] genBatch = new Brain[4096];
	
	public GPanel panel;
	
	boolean realtime = false;
	
	double velocity;
	double tube1 = -1;
	double tube2 = -1;
	double tube3 = -1;
	double tube4 = -1;
	
	double catcherPosition = 0;
	double energy = 15;
	
	boolean simulating = false;
	Brain winner;
	
	double time = 0;
	int iterations = 0;
	
	private Brain mutateBrain(Brain b, int temperature){
		for(int i = 0; i < temperature; i++){
			if(Math.random() < 0.5) b.neurons[(int) Math.round(Math.random()*7)][(int) Math.round(Math.random()*7)][(int) Math.round(Math.random()*7)] = null;
			else b.placeNeuron(generateNeuron());
		}
		
		return b;
	}
	
	private AbstractNeuron generateNeuron(){
		int i = (int) Math.round(Math.random()*5);
		
		if(i == 0){
			return new PipeNeuron();
		} else if(i == 1){
			return new BroadcasterNeuron();
		} else if(i == 2){
			return new NegatorNeuron();
		} else if(i == 3){
			return new ReciprocatorNeuron();
		} else if(i == 4){
			return new SummerNeuron();
		} else {
			return new MultiplicatorNeuron();
		}
	}
	
	public void run() {
		finished = false;
		
		Brain b = new Brain(1);
		
		while(true){
			
			if(realtime) try{ Thread.sleep(1000/60); } catch(Exception e) {/*it's probably nothing too bad*/}
		
			if(simulating){
			
				if(Math.random() < 0.05){
					if(tube1 == -1 && Math.random() < 0.25){
						tube1 = 0;
					}else if(tube2 == -1 && Math.random() < 0.25){
						tube2 = 0;
					}else if(tube3 == -1 && Math.random() < 0.25){
						tube3 = 0;
					}else if(tube4 == -1 && Math.random() < 0.25){
						tube4 = 0;
					}
				}
				
				if(tube1 != -1) tube1 += 1.0/60;
				if(tube2 != -1) tube2 += 1.0/60;
				if(tube3 != -1) tube3 += 1.0/60;
				if(tube4 != -1) tube4 += 1.0/60;
				
				b.setInputAt(tube1, 2, 2, Brain.TOP);
				b.setInputAt(tube2, 3, 2, Brain.TOP);
				b.setInputAt(tube3, 4, 2, Brain.TOP);
				b.setInputAt(tube4, 5, 2, Brain.TOP);
				b.setInputAt(catcherPosition, 2, 5, Brain.TOP);
				
				b.tick();
				
				velocity = Math.min(0.1, Math.max(-0.1, b.getOutputAt(2, 2, Brain.BTM)));
				
				catcherPosition += velocity;
				catcherPosition = Math.min(4, Math.max(0, catcherPosition));
				
				if(tube1 >1 && catcherPosition <= 1){ energy++; tube1 = -1; }
				else if(tube1 >1) tube1 = -1;
				if(tube2 >1 && catcherPosition > 1 && catcherPosition <= 2){ energy++; tube2 = -1; }
				else if(tube2 >1) tube2 = -1;
				if(tube3 >1 && catcherPosition > 2 && catcherPosition <= 3){ energy++; tube3 = -1; }
				else if(tube3 >1) tube3 = -1;
				if(tube4 >1 && catcherPosition > 3){ energy++; tube4 = -1; }
				else if(tube4 >1) tube4 = -1;
				
				time += 1.0/60;
				energy -= 1.0/60;
				if(energy < 0){
					if(time > 60 && !realtime){
						System.err.println("A brain got more than 60s! Switching to realtime on next batch.");
						setRealtime = true;
					}
					
					batch[iterations] = b;
					scores[iterations] = time;
					
					iterations++;
					if((iterations+1)%256 == 0)System.out.println("Iteration "+iterations+" ("+(((float) (iterations)+1)/40.96)+"%)");
					
					time = 0;
					energy = 15;
					tube1 = -1;
					tube2 = -1;
					tube3 = -1;
					tube4 = -1;
					
					catcherPosition = 0;
					
					if(iterations >= 4095){
						iterations = 0;
						fromBatch = true;
						generated = false;
					}
					
					simulating = false;
				}
			} else {
				
				b = new Brain(8);
				
				if(!fromBatch){
					for(int i = 0; i < 256; i++){
						b.placeNeuron(generateNeuron());
					}
				} else if(fromBatch && !generated){
					if(setRealtime) realtime = true;
					System.out.println("----\nBatch finished. Generating new...\n----");
					double highest = 0;
					int highestIndex = 0;
					for(int i = 0; i < 4096; i++){
						if(scores[i] > highest){
							highest = scores[i];
							highestIndex = i;
						}
					}
					
					for(int i = 0; i < 4096; i++){
						genBatch[i] = mutateBrain(batch[highestIndex], i);
					}
					
					generated = true;
				}
				
				if(fromBatch){
					b = genBatch[iterations];
				}
				
				simulating = true;
			}
			
			panel.repaint();
		}
	}

}
