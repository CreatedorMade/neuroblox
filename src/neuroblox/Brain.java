package neuroblox;

public class Brain {
	
	public static final int BTM = 0; // +Z
	public static final int TOP = 1; // -Z
	public static final int NRT = 2; // +X
	public static final int STH = 3; // -X
	public static final int WST = 4; // +Y
	public static final int EST = 5; // -Y
	
	int t = 0;
	
	private int size;
	
	private double[][] topFace;
	private double[][] btmFace;
	private double[][] nrtFace;
	private double[][] sthFace;
	private double[][] wstFace;
	private double[][] estFace;
	
	public AbstractNeuron[][][] neurons;
	
	public Brain(int size){
		this.size = size;
		
		neurons = new AbstractNeuron[size][size][size];
		
		topFace = new double[size][size];
		btmFace = new double[size][size];
		nrtFace = new double[size][size];
		sthFace = new double[size][size];
		wstFace = new double[size][size];
		estFace = new double[size][size];
	}
	
	public double getDataTo(int x, int y, int z, int r){
		try {
			if(x == 0 && r == STH){
				return sthFace[y][z];
			} else if(x == size-1 && r == NRT){
				return nrtFace[y][z];
			} else if(y == 0 && r == EST){
				return estFace[x][z];
			} else if(y == size-1 && r == WST){
				return wstFace[x][z];
			} else if(z == 0 && r == TOP){
				return topFace[x][y];
			} else if(z == size-1 && r == BTM){
				return btmFace[x][y];
			} else {
				int cx = x;
				int cy = y;
				int cz = z;
				int cr = r;
				if(r == NRT){
					cx++;
					cr = STH;
				} else if(r == STH){
					cx--;
					cr = NRT;
				} else if(r == EST){
					cy--;
					cr = WST;
				} else if(r == WST){
					cy++;
					cr = EST;
				} else if(r == TOP){
					cz--;
					cr = BTM;
				} else if(r == BTM){
					cz++;
					cr = TOP;
				} else {
					debug("GetDataTo() failed - unknown direction");
					return 0;
				}
				return getDataFrom(cx, cy, cz, cr);
			}
		} catch(ArrayIndexOutOfBoundsException e){
			debug("GetDataTo() failed - index out of bounds exception");
		}
		return 0;
	}
	
	public double getDataFrom(int x, int y, int z, int r){
		try{
			if(neurons[x][y][z] != null){
				return neurons[x][y][z].getOutData()[r];
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			debug("GetDataFrom() failed - index out of bounds exception");
		}
		return 0;
	}
	
	static void debug(String... s){
		for(String str : s){
			System.out.println("[DEBUG] "+str);
		}
	}
	
	public void tick(){
		for(int x = 0; x < size; x++)
			for(int y = 0; y < size; y++)
				for(int z = 0; z < size; z++)
					if(neurons[x][y][z] != null){
						double[] ds = {getDataTo(x,y,z,BTM), getDataTo(x,y,z,TOP), getDataTo(x,y,z,NRT), getDataTo(x,y,z,STH), getDataTo(x,y,z,WST), getDataTo(x,y,z,EST)};
						neurons[x][y][z].setInData(ds);
					}
		for(int x = 0; x < size; x++)
			for(int y = 0; y < size; y++)
				for(int z = 0; z < size; z++)
					if(neurons[x][y][z] != null){
						neurons[x][y][z].tick(t);
					}
		
		t++;
	}
	
	public boolean placeNeuron(AbstractNeuron n){
		int count = 0;
		for(int x = 0; x < size; x++)
			for(int y = 0; y < size; y++)
				for(int z = 0; z < size; z++)
					if(neurons[x][y][z] == null){
						count++;
					}
		if(count == 0) return false;
		while(true) // this is probably a bad idea, but i'll do whatever i damn well please
			for(int x = 0; x < size; x++)
				for(int y = 0; y < size; y++)
					for(int z = 0; z < size; z++)
						if(neurons[x][y][z] == null && Math.random() < 1.0/(double) (count)){
							neurons[x][y][z] = n;
							return true;
						}
	}
	
	public void setInputAt(double d, int x, int y, int r){
		try{
			if(r == BTM){
				btmFace[x][y] = d;
			} else if(r == TOP){
				topFace[x][y] = d;
			} else if(r == EST){
				estFace[x][y] = d;
			} else if(r == WST){
				wstFace[x][y] = d;
			} else if(r == NRT){
				nrtFace[x][y] = d;
			} else if(r == STH){
				sthFace[x][y] = d;
			} else {
				debug("setInputAt() failed - unknown direction");
			}
		} catch(ArrayIndexOutOfBoundsException e){
			debug("setInputAt() failed - index out of bounds");
		}
	}
	
	public double getOutputAt(int x, int y, int r){
		try{
			if(r == BTM){
				return getDataFrom(x, y, size-1, BTM);
			} else if(r == TOP){
				return getDataFrom(x, y, 0, TOP);
			} else if(r == EST){
				return getDataFrom(x, 0, y, EST);
			} else if(r == WST){
				return getDataFrom(x, size-1, y, WST);
			} else if(r == NRT){
				return getDataFrom(size-1, x, y, NRT);
			} else if(r == STH){
				return getDataFrom(0, x, y, STH);
			} else {
				debug("getOutputAt() failed - unknown direction");
			}
		} catch(ArrayIndexOutOfBoundsException e){
			debug("getOutputAt() failed - index out of bounds");
		}	
		return 0;
	}
	
	public void dump(){
		String str = "Brain data:\n";
		for(int z = 0; z < size; z++){
			for(int x = 0; x < size; x++){
				for(int y = 0; y < size; y++){
					str += "["+neurons[x][y][z]+" ";
					for(int i = 0; i < 6; i++){
						str += getDataFrom(x,y,z,i) + " ";
					}
					str += "]  ";
				}
				str+="\n";
			}
			str+="\n";
		}
		System.out.print(str);
	}
	
}
