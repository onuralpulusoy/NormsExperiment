package norm;

import java.util.ArrayList;

public class pNorm {

	int action;
	ArrayList<Integer> coOwners = new ArrayList<Integer>();
	int highestConTypeIndex;
	
	
	public pNorm(int action,ArrayList<Integer> coOwners,int highestConTypeIndex ) {
		this.action = action;
		this.coOwners = coOwners;
		this.highestConTypeIndex = highestConTypeIndex;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public ArrayList<Integer> getcoOwners() {
		return this.coOwners;
	}
	
	public int gethighestConTypeIndex() {
		return this.highestConTypeIndex;
	}
}
