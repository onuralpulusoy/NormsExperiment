package main;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import content.Content;
import norm.Norm;



public class NormRunner {
	
	public static void main(String[] args) {
		
		//System.out.println("test");
		//ArrayList<Norm> norms = new ArrayList<Norm>();
		//Norm norm = new Norm ("friend","beach",0);
		//Norm norm2 = new Norm ("colleague","beach",0);
		//Agent agent = new Agent(1,"a",norms);
		

		//norms.add(norm2);
		//norms.add(norm);
		
		ArrayList<Agent> agents = new ArrayList<Agent>();
		//norms = new ArrayList<Norm>();
		//norms.add(norm);
		//Content content = new Content(1,"c");
		//Agent agent2 = new Agent(1,"a2",norms);
		
		//System.out.println(agent2.getAgentName());
		//System.out.println(agent.getNorm());
		//System.out.println(agent2.getNorm());
		Norm norm = new Norm ("friend","beach",0);
		Norm norm2 = new Norm ("friend","work",0);
		Norm norm3 = new Norm ("friend","party",0);
		
		for(Integer i=0;i<100;i++){
			
			ArrayList<Norm> norms = new ArrayList<Norm>();
			int rando = 0;
			rando = new Random().nextInt(100);
			if (rando < 33)
			norms.add(norm);
			rando = new Random().nextInt(100);
			if (rando < 66)
				norms.add(norm2);
			rando = new Random().nextInt(100);
			if (rando < 0)
				norms.add(norm3);
			
			Agent agent = new Agent(i,"a"+i.toString(),norms);
			agents.add(agent);
		}

		ArrayList<Norm> normEmergence = new ArrayList<Norm>();
		for(Integer i=0;i<10000;i++){
			
			int rando = 0;
			ArrayList<Integer> coOwners = new ArrayList<Integer>();
			String conType="";
			rando = new Random().nextInt(33);
			coOwners.add(rando);
			rando = new Random().nextInt(33);
			coOwners.add(rando+33);
			rando = new Random().nextInt(33);
			coOwners.add(rando+66);
			
			rando = new Random().nextInt(100);
			if(rando<=32)
				conType= "beach";
			if(rando>32&&rando<=65)
				conType= "work";
			if(rando>65)
				conType= "party";
			
			int ns=0;
			int s=0;
			for(int j=0;j<coOwners.size();j++){
				Boolean normExists = false;
				for(int k=0;k<agents.get(coOwners.get(j)).getNorms().size();k++){
					if(agents.get(coOwners.get(j)).getNorms().get(k).getConType()==conType)
						normExists = true;
				}
				if(normExists)
					ns++;
				else
				{
					rando = new Random().nextInt(100);
					if(rando<50)
						ns++;
					else
						s++;
				}
			}
			if(ns>s){
				Norm emerging = new Norm("friend",conType,0);
				normEmergence.add(emerging);
			}
			else{
				Norm emerging = new Norm("friend",conType,1);
				normEmergence.add(emerging);
			}
			
		}
		int beach0 = 0;
		int beach1 = 0;
		int work0 = 0;
		int work1 = 0;
		int party0 = 0;
		int party1 = 0;
		
		System.out.println(normEmergence.size());
		for(int i=0;i<normEmergence.size();i++){
			//System.out.println(normEmergence.get(i).getConType() + normEmergence.get(i).getBehavior());
			if(normEmergence.get(i).getConType() == "beach" && normEmergence.get(i).getBehavior() == 0)
				beach0++;
			if(normEmergence.get(i).getConType() == "beach" && normEmergence.get(i).getBehavior() == 1)
				beach1++;
			if(normEmergence.get(i).getConType() == "work" && normEmergence.get(i).getBehavior() == 0)
				work0++;
			if(normEmergence.get(i).getConType() == "work" && normEmergence.get(i).getBehavior() == 1)
				work1++;
			if(normEmergence.get(i).getConType() == "party" && normEmergence.get(i).getBehavior() == 0)
				party0++;
			if(normEmergence.get(i).getConType() == "party" && normEmergence.get(i).getBehavior() == 1)
				party1++;
		}
		
		System.out.println("beach: " + beach0 + " - " + beach1 + " - " + (float)((float)beach0/(float)(beach0+beach1)));
		System.out.println("work: " + work0 + " - " + work1 + " - " + (float)((float)work0/(float)(work0+work1)));
		System.out.println("party: " + party0 + " - " + party1 + " - " + (float)((float)party0/(float)(party0+party1)));
	}
}
