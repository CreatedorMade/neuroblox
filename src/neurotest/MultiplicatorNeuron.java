package neurotest;

import neuroblox.AbstractNeuron;

public class MultiplicatorNeuron extends AbstractNeuron {
	
	int face1 = 0;
	int face2 = 0;
	
	public MultiplicatorNeuron(){
		face1 = (int) Math.round(Math.random()*5);
		face2 = (int) Math.round(Math.random()*5);
		while(face2 == face1)face2 = (int) Math.round(Math.random()*5);
	}
	
	public void tick(int t) {
		double val = super.dataIn[face1] * super.dataIn[face2];
		for(int i = 0; i < 6; i++) if(i != face1 && i != face2) super.dataOut[i] = val;
	}

}
