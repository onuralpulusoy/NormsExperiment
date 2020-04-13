package norm;

public class Norm {
	
	//Commented by OU on 13/04/2020
	//DESC:This class is used to describe m-norms for the simulation
	//Agents own m-norms, and can assign privacy decisions according to the relationship types and contextual properties
	
	int relType;
	int conType;
	//-1:share 0:indifferent 1:share
	int behavior;
	
	public Norm(int relType,int conType, int behavior) {
		this.relType = relType;
		this.conType = conType;
		this.behavior = behavior;
	}
	
	public int getRelType() {
		return this.relType;
	}
	
	public int getConType() {
		return this.conType;
	}
	
	public int getBehavior() {
		return this.behavior;
	}
	

}
