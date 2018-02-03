package neurotest;

import neuroblox.AbstractNeuron;

public class PipeNeuron extends AbstractNeuron {
	
	int face1 = 0;
	int face2 = 0;
	
	public PipeNeuron(){
		face1 = (int) Math.round(Math.random()*5);
		face2 = (int) Math.round(Math.random()*5);
		while(face2 == face1)face2 = (int) Math.round(Math.random()*5);
	}
	
	public void tick(int t) {
		super.dataOut[face1] = super.dataIn[face2];
		super.dataOut[face2] = super.dataIn[face1];
	}

}
