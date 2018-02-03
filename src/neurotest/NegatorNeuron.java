package neurotest;

import neuroblox.AbstractNeuron;

public class NegatorNeuron extends AbstractNeuron {
	
	public NegatorNeuron(){
		super();
	}
	
	public void tick(int t) {
		for(int i = 0; i < 6; i++) if(i != super.facing) super.dataOut[i] = -super.dataIn[super.facing];
	}

}
