package neuroblox;

public abstract class AbstractNeuron {
	public AbstractNeuron(){
		facing = (int) Math.round(Math.random()*5);
	}
	public AbstractNeuron(String fromString){}
	
	int facing = Brain.TOP;
	
	public double[] dataIn = {0, 0, 0, 0, 0, 0}; 
	public double[] dataOut = {0, 0, 0, 0, 0, 0};
	
	final void setInData(double...ds){
		try{
			dataIn[Brain.TOP] = ds[Brain.TOP];
			dataIn[Brain.BTM] = ds[Brain.BTM];
			dataIn[Brain.EST] = ds[Brain.EST];
			dataIn[Brain.WST] = ds[Brain.WST];
			dataIn[Brain.NRT] = ds[Brain.NRT];
			dataIn[Brain.STH] = ds[Brain.STH];
		}catch(ArrayIndexOutOfBoundsException e){
			Brain.debug("AbstractNeuron.setInData() failed - index out of bounds exception");
		}
	}
	
	final double[] getOutData(){
		return dataOut;
	}
	
	public abstract void tick(int t);
	
	public String saveString(){
		return "";
	}
}
