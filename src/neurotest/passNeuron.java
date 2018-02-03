package neurotest;

import neuroblox.Brain;

public class passNeuron extends neuroblox.AbstractNeuron {
	
	public passNeuron(){
		super();
	}
	
	@Override
	public void tick(int t) {
		super.dataOut[Brain.TOP] = super.dataIn[Brain.BTM];
		super.dataOut[Brain.BTM] = super.dataIn[Brain.TOP];
		super.dataOut[Brain.EST] = super.dataIn[Brain.WST];
		super.dataOut[Brain.WST] = super.dataIn[Brain.EST];
		super.dataOut[Brain.NRT] = super.dataIn[Brain.STH];
		super.dataOut[Brain.STH] = super.dataIn[Brain.NRT];
	}
	
}
