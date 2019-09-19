package norm;

import java.util.ArrayList;

public class SocialNormBase {
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
