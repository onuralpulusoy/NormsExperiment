package agent;

public class AgentRelation {
	
	//Commented by OU on 13/04/2020
	//DESC:This class describes bi-directional relationships within the social network
	//The relationship type is currently an integer, and we only use 0 as the friendship relation as of now
	//Variations of relationship types can be expanded within this class
	
	int a1Id;
	int a2Id;
	int relType;
	
	public AgentRelation(int a1Id, int a2Id, int relType) {
		this.a1Id = a1Id;
		this.a2Id = a2Id;
		this.relType = relType;
	}

}
