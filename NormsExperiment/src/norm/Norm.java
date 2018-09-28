package norm;

public class Norm {
	String relType;
	String conType;
	int behavior;
	
	public Norm(String relType,String conType, int behavior) {
		this.relType = relType;
		this.conType = conType;
		this.behavior = behavior;
	}
	
	public String getRelType() {
		return this.relType;
	}
	
	public String getConType() {
		return this.conType;
	}
	
	public int getBehavior() {
		return this.behavior;
	}
	

}
