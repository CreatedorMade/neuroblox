package neurotest;

import neuroblox.AbstractNeuron;

public class ReciprocatorNeuron extends AbstractNeuron {
	
	public ReciprocatorNeuron(){
		super();
	}
	
	public void tick(int t) {
		double val = 1.0/super.dataIn[super.facing];
		for(int i = 0; i < 6; i++) if(i != super.facing) super.dataOut[i] = val;
	}

}
