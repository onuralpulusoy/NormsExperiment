package main;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import content.Content;
import content.Sentiment;
import norm.Norm;



public class NormRunner {
	
	public static void main(String[] args) {
		
		
		ArrayList<Agent> agents = new ArrayList<Agent>();
		agents = initAgents(agents);
		ArrayList<Content> contents = new ArrayList<Content>();
		contents = initContents(contents);
		
		for(int i=0;i<100;i++) {
			System.out.println(agents.get(i).getAgentName());
			for(int j=0;j<agents.get(i).getNorms().size();j++) {
				System.out.println(agents.get(i).getNorms().get(j).getRelType() + "-" +
						agents.get(i).getNorms().get(j).getConType() + "-" +
						agents.get(i).getNorms().get(j).getBehavior() + "-");
			}
		}
		
		for(int i=0;i<1000;i++) {
			System.out.println("Content " + i);
			System.out.println("Coowners:");
			for(int j=0;j<contents.get(i).getCoowners().size();j++) {
				System.out.println(contents.get(i).getCoowners().get(j) + "-");
			}
			System.out.println("Sentiments:");
			for(int j=0;j<contents.get(i).getSentiments().size();j++) {
				System.out.println(contents.get(i).getSentiments().get(j).getSentimentType() + "-"
						+ contents.get(i).getSentiments().get(j).getBelonginess());
			}
		}
		

		/*
		Norm norm = new Norm (0,0,-1);
		int rando = 0;
		rando = new Random().nextInt(100);
		
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
		*/
	}
	
	public static ArrayList<Agent> initAgents(ArrayList<Agent> agents){
		
		int relType = 0;
		int randoConType = 0;
		int randoDec = 0;
		
		int randoNormSize = 0;
		
		
		for(Integer ac=0;ac<100;ac++) {
			
			randoNormSize = new Random().nextInt(5);
			ArrayList<Norm> norms = new ArrayList<Norm>();
			for(int i=0;i<randoNormSize;i++) {
				randoConType = new Random().nextInt(5);
				randoDec = new Random().nextInt(3) - 1;

				Norm norm = new Norm (relType,randoConType,randoDec);
				norms.add(norm);
				
			}

			Agent agent = new Agent(ac,"a"+ac.toString(),norms);
			agents.add(agent);
		}
		
		return agents;
	}
	
	public static ArrayList<Content> initContents(ArrayList<Content> contents){

		int coOwnerSize = 0;
		int agentID = 0;
		int sentimentType = 0;
		int belonginess = 0;
		
		for(Integer cc=0;cc<1000;cc++) {
			
			coOwnerSize = new Random().nextInt(3) + 2;
			ArrayList<Integer> coOwners = new ArrayList<Integer>();
			for(Integer i=0;i<coOwnerSize;i++) {
				agentID = new Random().nextInt(100);
				if(!coOwners.contains(agentID)) {
					coOwners.add(agentID);
				}
				else {
					i--;
				}
			}
			
			Content content = new Content(cc,coOwners);
			
			for(Integer i=0;i<3;i++) {
				sentimentType = new Random().nextInt(5);
				belonginess = new Random().nextInt(100);
				Sentiment sentiment = new Sentiment(sentimentType,belonginess);
				content.addSentiment(sentiment);
			}
			contents.add(content);
		}
		return contents;
	}
}
