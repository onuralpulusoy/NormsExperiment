package norm;

import java.util.ArrayList;

public class SocialNormBase {
	
	//Commented by OU on 13/04/2020
	//DESC:This class defines the s-norms according to the contextual clusters and relationship types between the agents
	//An s-norm cluster can become normative or non-normative according to the previous privacy decisions within the cluster
	
	int majorRelationship = 0;
	ArrayList<Integer> conType = new ArrayList<Integer>();
	int action = -1;
	int kClass = -1;
	int id = -1;
	Boolean isSNorm = false;
	
	public SocialNormBase(int majorRelationship, ArrayList<Integer> conType, int action, int id) {
		this.majorRelationship = majorRelationship;
		this.conType = conType;
		this.action = action;
		this.id = id;
	}
	
	public void setNormTrue() {
		this.isSNorm = true;
	}
	
	public void setNormFalse() {
		this.isSNorm = false;
	}
	
	public void setKClass(int kClass) {
		this.kClass = kClass;
	}
	
	public int getKClass() {
		return this.kClass;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public int getID() {
		return this.id;
	}
	
	public ArrayList<Integer> getConType() {
		return this.conType;
	}
}
