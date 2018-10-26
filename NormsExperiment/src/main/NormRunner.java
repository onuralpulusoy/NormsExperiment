package main;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import content.Content;
import content.Sentiment;
import norm.Norm;
import norm.SocialNormBase;



public class NormRunner {
	public static void main(String[] args) {
		
		ArrayList<SocialNormBase> sNormBase = new 	ArrayList<SocialNormBase>();
		ArrayList<Agent> agents = new ArrayList<Agent>();
		agents = initAgents(agents);
		ArrayList<Content> contents = new ArrayList<Content>();
		contents = initContents(contents);
		
		runWithoutNorms(agents,contents,sNormBase);
		
		/*
		for(int i=0;i<100;i++) {
			System.out.println(agents.get(i).getAgentName());
			for(int j=0;j<agents.get(i).getNorms().size();j++) {
				System.out.println(agents.get(i).getNorms().get(j).getRelType() + "-" +
						agents.get(i).getNorms().get(j).getConType() + "-" +
						agents.get(i).getNorms().get(j).getBehavior() + "-");
			}
		}
		
		/*
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
		*/

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
				randoDec = new Random().nextInt(2);

				Boolean conTypeNormExists = false;
				
				for(int j=0;j<norms.size();j++) {
					if(norms.get(j).getConType()==randoConType)
							conTypeNormExists = true;
				}
				
				if(!conTypeNormExists) {
					Norm norm = new Norm (relType,randoConType,randoDec);
					norms.add(norm);
				}
				
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
				sentimentType = new Random().nextInt(10);
				belonginess = new Random().nextInt(100);
				
				Boolean sentimentExists = false;
				
				for(int j=0; j< content.getSentiments().size();j++) {
					if(content.getSentiments().get(j).getSentimentType()==sentimentType) {
						sentimentExists = true;
					}
				}
				if(sentimentExists)
					i--;
				else {
					Sentiment sentiment = new Sentiment(sentimentType,belonginess);
					content.addSentiment(sentiment);
				}
			}
			contents.add(content);
		}
		return contents;
	}
	
	public static void runWithoutNorms(ArrayList<Agent> agents,ArrayList<Content> contents,ArrayList<SocialNormBase> sNormBase) {
		//Norm relation type isn't checked yet since only one relation is present but still stored so it can be modified later on
		int shared = 0;
		int notShared = 0;
		int indecisive = 0;
		for(int i=0;i<contents.size();i++) {

			System.out.println("Content " + i + " - Type " + contents.get(i).getHighestContentTypeIndex());
			System.out.println("Coowners:");
			int cShare = 0;
			int cNShare = 0;
			for(int j=0;j< contents.get(i).getCoowners().size();j++) {
				Boolean normFound = false;
				System.out.println("Coowner " + contents.get(i).getCoowners().get(j) + ":");
				for(int k=0;k<agents.get(contents.get(i).getCoowners().get(j)).getNorms().size();k++) {
					if(agents.get(contents.get(i).getCoowners().get(j)).getNorms().get(k).getConType() == contents.get(i).getHighestContentTypeIndex()) {
						System.out.println("Norm found. Behavior is " + agents.get(contents.get(i).getCoowners().get(j)).getNorms().get(k).getBehavior() );
						if(agents.get(contents.get(i).getCoowners().get(j)).getNorms().get(k).getBehavior() == 0) {
							cNShare++;
						}
						else {
							cShare++;
						}
						normFound = true;
					}
				}
				if(!normFound) {
					System.out.println("No related norm found for the coowner");
					//no norms for given content, possibilities, don't bid or bid randomly
				}
			}
			if(cNShare>cShare) {
				notShared++;
				System.out.println("Content not shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 0);
				sNormBase.add(sNBase);
				
			}
			else if(cNShare<cShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 1);
				sNormBase.add(sNBase);
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}
		}
		
		System.out.println("SocialNormBase Size: " + sNormBase.size());
		kMeans(sNormBase);
	}
	
	public static void kMeans(ArrayList<SocialNormBase> sNormBase) {
		int countK = sNormBase.size() / 100 + 1;
		System.out.println("Count k:"+ countK);
		
		int cCounter = 0;
		int clas = 0;
		for(int i=0;i<sNormBase.size();i++) {
			sNormBase.get(i).setKClass(clas);
			cCounter++;
			if(cCounter==(sNormBase.size()/countK)&&i<sNormBase.size()-countK) {
				clas++;
				cCounter = 0;
			}
		}
	Boolean swap=true;	
for(int loop=0;loop<100&&swap;loop++) {
		ArrayList<Integer> actionPercentage = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> classDimensionMeans = new ArrayList<ArrayList<Integer>>();
		System.out.println("Loop #: " + loop);
		for(int i=0;i<clas+1;i++) {
			int classSize = 0;
			int noShare = 0;
			//actionPercentage.add(0);
			ArrayList<Integer> conType = new ArrayList<Integer>();
			int t1=0;
			int t2=0;
			int t3=0;
			int t4=0;
			//classDimensionMeans.add(conType);
			for(int j=0;j<sNormBase.size();j++) {
				if(sNormBase.get(j).getKClass() == i) {
					classSize++;
					if(sNormBase.get(j).getAction() == 0)
						noShare++;
					t1+=sNormBase.get(j).getConType().get(0);
					t2+=sNormBase.get(j).getConType().get(1);
					t3+=sNormBase.get(j).getConType().get(2);
					t4+=sNormBase.get(j).getConType().get(3);
				}
			}
			System.out.println("Class " +i+ " size:"+ classSize);
			t1=t1/classSize;
			t2=t2/classSize;
			t3=t3/classSize;
			t4=t4/classSize;
			System.out.println("Dimension means: " + t1 + "-" + t2 + "-" + t3 + "-" + t4);
			actionPercentage.add(noShare*100/classSize);
			System.out.println("Action Percentage: " + noShare*100 / classSize);
			conType.add(t1);
			conType.add(t2);
			conType.add(t3);
			conType.add(t4);
			classDimensionMeans.add(conType);
		}
		System.out.println(actionPercentage.size());
		swap = false;
		int swapCount = 0;
		for(int i=0;i<sNormBase.size();i++) {
			int lowestDif = 999;
			for(int j=0;j<classDimensionMeans.size();j++) {
				int dif = 0;
				dif += Math.abs(sNormBase.get(i).getConType().get(0) - classDimensionMeans.get(j).get(0));
				dif += Math.abs(sNormBase.get(i).getConType().get(1) - classDimensionMeans.get(j).get(1));
				dif += Math.abs(sNormBase.get(i).getConType().get(2) - classDimensionMeans.get(j).get(2));
				dif += Math.abs(sNormBase.get(i).getConType().get(3) - classDimensionMeans.get(j).get(3));
				if(dif<lowestDif && sNormBase.get(i).getKClass()!=j) {
					lowestDif = dif;
					//System.out.println("Changed " + sNormBase.get(i).getKClass()+ " to " + j);
					sNormBase.get(i).setKClass(j);
					swap = true;
					swapCount++;
				}
			}
		}
		System.out.println("Swap count: " + swapCount);
		
	}
		/*
		actionPercentage = new ArrayList<Integer>();
		classDimensionMeans = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0;i<clas+1;i++) {
			int classSize = 0;
			int noShare = 0;
			//actionPercentage.add(0);
			ArrayList<Integer> conType = new ArrayList<Integer>();
			int t1=0;
			int t2=0;
			int t3=0;
			int t4=0;
			//classDimensionMeans.add(conType);
			for(int j=0;j<sNormBase.size();j++) {
				if(sNormBase.get(j).getKClass() == i) {
					classSize++;
					if(sNormBase.get(j).getAction() == 0)
						noShare++;
					t1+=sNormBase.get(j).getConType().get(0);
					t2+=sNormBase.get(j).getConType().get(1);
					t3+=sNormBase.get(j).getConType().get(2);
					t4+=sNormBase.get(j).getConType().get(3);
				}
			}
			System.out.println("Class " +i+ " size:"+ classSize);
			t1=t1/classSize;
			t2=t2/classSize;
			t3=t3/classSize;
			t4=t4/classSize;
			System.out.println("Dimension means: " + t1 + "-" + t2 + "-" + t3 + "-" + t4);
			actionPercentage.add(noShare*100/classSize);
			System.out.println("Action Percentage: " + noShare*100 / classSize);
			conType.add(t1);
			conType.add(t2);
			conType.add(t3);
			conType.add(t4);
			classDimensionMeans.add(conType);
		}
		*/
		
	}
}
