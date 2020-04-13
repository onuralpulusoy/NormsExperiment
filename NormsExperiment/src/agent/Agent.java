package agent;

import java.util.ArrayList;
import norm.Norm;
import norm.pNorm;

public class Agent {
	
	//Commented by OU on 13/04/2020
	//DESC:Agent is a class to store and process information about privacy agents
	//Agents store m-norms and p-norms when instantiated with this class
	
	int id;
	String name;
	ArrayList<AgentRelation> relations = new ArrayList<AgentRelation>();
	ArrayList<Norm> norms = new ArrayList<Norm>();
	ArrayList<pNorm> pNorms = new ArrayList<pNorm>();
	
	
	
	
	public Agent(int id, String name, ArrayList<Norm> norms) {
		this.id = id;
		this.name = name;
		this.norms = norms;
	}
	
	public String getAgentName() {
		return this.name;
	}
	
	public int getAgentID() {
		return this.id;
	}
	
	public void addNorm(Norm norm) {
		norms.add(norm);
	}
	
	public ArrayList<Norm> getNorms() {
		return this.norms;
	}
	
	public void addpNorm(pNorm pnorm) {
		this.pNorms.add(pnorm);
	}
	
	public ArrayList<pNorm> getpNorms() {
		return this.pNorms;
	}
	
}
