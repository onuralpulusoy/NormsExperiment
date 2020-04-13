package norm;

import java.util.ArrayList;

public class pNorm {

	//Commented by OU on 13/04/2020
	//DESC:Class defining p-norms. Each agent have p-norms formed according to collaborative decisions with other agents
	//p-norms can be shared or kept private by the agents
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
