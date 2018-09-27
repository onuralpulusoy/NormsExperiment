package agent;

import java.util.ArrayList;
import norm.Norm;

public class Agent {
	
	int id;
	String name;
	ArrayList<AgentRelation> relations = new ArrayList<AgentRelation>();
	ArrayList<Norm> norms = new ArrayList<Norm>();
	
	
	
	
	public Agent(int id, String name, ArrayList<Norm> norms) {
		this.id = id;
		this.name = name;
		this.norms = norms;
	}
	
	public String getAgentName() {
		return name;
	}
	
	public void addNorm(Norm norm) {
		norms.add(norm);
	}
	
	public String getNorm() {
		return this.norms.get(0).getRelType();
	}
	
}
