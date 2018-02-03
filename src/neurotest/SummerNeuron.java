package neurotest;

import neuroblox.AbstractNeuron;

public class SummerNeuron extends AbstractNeuron {
	
	public SummerNeuron(){
		super();
	}
	
	public void tick(int t) {
		double sum = 0;
		
		for(int i = 0; i < 6; i++){
			if(i != super.facing) sum += super.dataIn[i];
			super.dataOut[super.facing] = sum;
		}
	}

}
