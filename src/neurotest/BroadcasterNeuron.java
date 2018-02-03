package neurotest;

import neuroblox.AbstractNeuron;

public class BroadcasterNeuron extends AbstractNeuron {
	
	public BroadcasterNeuron(){
		super();
	}
	
	public void tick(int t) {
		double val = super.dataIn[super.facing];
		
		for(int i = 0; i < 6; i++)
			if(i != super.facing) super.dataOut[i] = val;
	}
	
}
