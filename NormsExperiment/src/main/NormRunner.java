package main;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import content.Content;
import norm.Norm;



public class NormRunner {
	
	public static void main(String[] args) {
		
		System.out.println("test");
		ArrayList<Norm> norms = new ArrayList<Norm>();
		Norm norm = new Norm ("friend","beach",0);
		Norm norm2 = new Norm ("colleague","beach",0);
		Agent agent = new Agent(1,"a",norms);
		

		norms.add(norm2);
		norms.add(norm);
		

		norms = new ArrayList<Norm>();
		norms.add(norm);
		//Content content = new Content(1,"c");
		Agent agent2 = new Agent(1,"a2",norms);
		
		System.out.println(agent2.getAgentName());
		System.out.println(agent.getNorm());
		System.out.println(agent2.getNorm());
		//System.out.println(content.getContentType());
	}
}
