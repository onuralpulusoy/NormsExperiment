package main;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import content.Content;
import content.Sentiment;
import norm.Norm;
import norm.SocialNormBase;
import norm.SocialNormClasses;
import norm.pNorm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class NormRunner {
	public static void main(String[] args) throws IOException {
		
		ArrayList<SocialNormBase> sNormBase = new 	ArrayList<SocialNormBase>();
		ArrayList<SocialNormClasses> sClasses = new ArrayList<SocialNormClasses>();
		ArrayList<Agent> agents = new ArrayList<Agent>();
		agents = initAgents(agents);
		ArrayList<Content> contents = new ArrayList<Content>();
		contents = initContents(contents);
		
		//runWithoutNorms(agents,contents,sNormBase,sClasses);
		runWithNorms(agents,contents,sNormBase,sClasses);
		
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
				
				if(new Random().nextInt(100) >80)
					randoDec = 0;
				else
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
		
		for(Integer cc=0;cc<10000;cc++) {
			
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
	
	public static void runWithoutNorms(ArrayList<Agent> agents,ArrayList<Content> contents,ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses) {
		//Norm relation type isn't checked yet since only one relation is present but still stored so it can be modified later on
		int shared = 0;
		int notShared = 0;
		int indecisive = 0;
		
		int action = -1;
		
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
				action = 0;
				
			}
			else if(cNShare<cShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 1);
				sNormBase.add(sNBase);
				action = 1;
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}

			pNorm pnorm = new pNorm(action,contents.get(i).getCoowners(),contents.get(i).getHighestContentTypeIndex());
			for(int j=0;j< contents.get(i).getCoowners().size();j++) {
				for(int k=0;k< agents.size();k++) {
					if (agents.get(k).getAgentID() == contents.get(i).getCoowners().get(j) ) {
						agents.get(k).addpNorm(pnorm);
					}
				}
			}
			
		}
		
		System.out.println("SocialNormBase Size: " + sNormBase.size());
		//kMeans(sNormBase,sClasses);
		for(int i=0;i<sNormBase.size();i++) {
			//System.out.println(sNormBase.get(i).getKClass() + " - " + sNormBase.get(i).getAction());
			//System.out.println(sNormBase.get(i).getConType().get(0));
		}
		
		for(int i=0;i<sClasses.size();i++) {
			System.out.println(sClasses.get(i).getclassId());
			System.out.println(sClasses.get(i).getcT1mean() + " - " + sClasses.get(i).getcT2mean() + " - " + sClasses.get(i).getcT3mean() + " - " + sClasses.get(i).getcT4mean());
			System.out.println(sClasses.get(i).getPercentage());
			System.out.println(sNormBase.get(i).getKClass() + " - " + sNormBase.get(i).getAction());
			System.out.println(sNormBase.get(i).getConType().get(0));
		}
		
		for(int i=0;i<agents.size();i++) {
			System.out.println("Agent " + i);
			for(int j=0;j<agents.get(i).getpNorms().size();j++) {
				System.out.println("Content action and type: " + agents.get(i).getpNorms().get(j).getAction() + " - " + agents.get(i).getpNorms().get(j).gethighestConTypeIndex());
				for(int k=0; k<agents.get(i).getpNorms().get(j).getcoOwners().size();k++) {
					System.out.print(agents.get(i).getpNorms().get(j).getcoOwners().get(k) + " - ");
					System.out.println("");
				}
			}
		}
		
	}
	
	public static void runWithNorms(ArrayList<Agent> agents,ArrayList<Content> contents,ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses) throws IOException {
		int action = -1;
		
		int shared = 0;
		int notShared = 0;
		int indecisive = 0;
		
		int pNormDecisions = 0;
		int difpNormDecisions = 0;
		int corpNormDecisions = 0;
		int mNormDecisions = 0;
		int sNormDecisions = 0;
		int difsNormDecisions = 0;
		int corsNormDecisions = 0;
		
		FileWriter fw = new FileWriter("Results.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	    
	

		
		for(int i=0;i<contents.size();i++) {
			action = -1;

			int cShare = 0;
			int cNShare = 0;
			

			int pShare = 0;
			int pNShare = 0;
			int cpShare = 0;
			int cpNShare = 0;
			
			int sShare = 0;
			int sNShare = 0;
			int csShare = 0;
			int csNShare = 0;
			
			System.out.println("CONTENT " + i + "########################################################");
			for(int j=0;j<contents.get(i).getCoowners().size();j++) {
				for(int k=0;k<agents.size();k++) {
					if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
						System.out.println("Agent " + agents.get(k).getAgentID() + ":");
						int decision = checkpNorms(agents.get(k), contents.get(i));
						int decisionM = checkmNorms(agents.get(k), contents.get(i));
						if(decision == 0)
							pNShare++;
						if(decision == 1)
							pShare++;
						if(decisionM == 0)
							cpNShare++;
						if(decisionM == 1)
							cpShare++;
					}
				}
			}
			
			
			if(pNShare>pShare) {
				notShared++;
				System.out.println("Content not shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 0);
				sNormBase.add(sNBase);
				action = 0;
				pNormDecisions++;
				if(cpNShare>=cpShare){
					corpNormDecisions++;
				}
				else{
					difpNormDecisions++;
				}
			}
			else if(pNShare<pShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 1);
				sNormBase.add(sNBase);
				action = 1;
				pNormDecisions++;
				if(cpNShare<=cpShare){
					corpNormDecisions++;
				}
				else{
					difpNormDecisions++;
				}
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}
			
			if(action == -1) {
				for(int j=0;j<contents.get(i).getCoowners().size();j++) {
					for(int k=0;k<agents.size();k++) {
						if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
							System.out.println("Agent " + agents.get(k).getAgentID() + ":");
							int decision = checksNorms(agents.get(k), contents.get(i),sClasses);
							int decisionM = checkmNorms(agents.get(k), contents.get(i));
							if(decision == 0)
								sNShare++;
							if(decision == 1)
								sShare++;
							if(decisionM == 0)
								csNShare++;
							if(decisionM == 1)
								csShare++;
						}
					}
				}
				
				
				if(sNShare>sShare) {
					notShared++;
					System.out.println("Content not shared");
					action = 0;
					sNormDecisions++;
					if(csNShare>=csShare){
						corsNormDecisions++;
					}
					else{
						difsNormDecisions++;
					}
				}
				else if(sNShare<sShare) {
					shared++;
					System.out.println("Content shared");
					action = 1;
					sNormDecisions++;
					if(csNShare<=csShare){
						corsNormDecisions++;
					}
					else{
						difsNormDecisions++;
					}
				}
				else {
					indecisive++;
					System.out.println("Content indecisive");
				}
			}
			
			if(action == -1) {
			for(int j=0;j<contents.get(i).getCoowners().size();j++) {
				for(int k=0;k<agents.size();k++) {
					if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
						System.out.println("Agent " + agents.get(k).getAgentID() + ":");
						int decision = checkmNorms(agents.get(k), contents.get(i));
						if(decision == 0)
							cNShare++;
						if(decision == 1)
							cShare++;
					}
				}
			}
			
			if(cNShare>cShare) {
				notShared++;
				System.out.println("Content not shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 0);
				sNormBase.add(sNBase);
				action = 0;
				mNormDecisions++;
			}
			else if(cNShare<cShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 1);
				sNormBase.add(sNBase);
				action = 1;
				mNormDecisions++;
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}
			
			pNorm pnorm = new pNorm(action,contents.get(i).getCoowners(),contents.get(i).getHighestContentTypeIndex());
			for(int j=0;j< contents.get(i).getCoowners().size();j++) {
				for(int k=0;k< agents.size();k++) {
					if (agents.get(k).getAgentID() == contents.get(i).getCoowners().get(j) ) {
						agents.get(k).addpNorm(pnorm);
					}
				}
			}

			}
			
			if((i % 250) == 0 && i > 100) {

				Boolean contin = true;
				int sizeParam = 4;
				while(contin) {
				int normClCount = 0;
				sizeParam++;
				for(int k=0;k<sClasses.size();k++) {
					if (sClasses.get(k).getPercentage() > 66 || sClasses.get(k).getPercentage() < 34)
						normClCount++;
					if (sClasses.get(k).getSize() < 100)
						contin = false;
				}
				
				if(normClCount>sClasses.size()/4 && sClasses.size()>1)
					contin = false;
				
				sClasses.clear();
				System.out.println("SocialNormBase Size: " + sNormBase.size());
				kMeans(sNormBase,sClasses,sizeParam);
				}
			}
			if(i==9999) {
				Boolean contin = true;
				int sizeParam = 4;
				while(contin) {
				int normClCount = 0;
				sizeParam++;
				for(int k=0;k<sClasses.size();k++) {
					if (sClasses.get(k).getPercentage() > 66 || sClasses.get(k).getPercentage() < 33)
						normClCount++;
					if (sClasses.get(k).getSize() < 100)
						contin = false;
				}
				
				if(normClCount>sClasses.size()/4 && sClasses.size()>1)
					contin = false;

				sClasses.clear();
				System.out.println("SocialNormBase Size: " + sNormBase.size());
				kMeans(sNormBase,sClasses,sizeParam);
				}
			}
			
			if(mNormDecisions==0)
				mNormDecisions = 1;
			out.println(mNormDecisions+","+pNormDecisions+","+sNormDecisions+","+
					corpNormDecisions+","+difpNormDecisions+","+
					corsNormDecisions+","+difsNormDecisions+","+
					(double)100*mNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)+","+
					(double)100*pNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)+","+
					(double)100*sNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)
					);
		}
		out.close();
		System.out.println("mNorm: " + mNormDecisions + " pNorm: " + pNormDecisions + " sNorm: " + sNormDecisions);
		//System.out.println("SocialNormBase Size: " + sNormBase.size());
		//kMeans(sNormBase,sClasses);
		for(int i=0;i<sNormBase.size();i++) {
			//System.out.println(sNormBase.get(i).getKClass() + " - " + sNormBase.get(i).getAction());
			//System.out.println(sNormBase.get(i).getConType().get(0));
		}
		
		
		for(int i=0;i<sClasses.size();i++) {
			System.out.println("***" + sClasses.get(i).getclassId());
			System.out.println(sClasses.get(i).getcT1mean() + " - " + sClasses.get(i).getcT2mean() + " - " + sClasses.get(i).getcT3mean() + " - " + sClasses.get(i).getcT4mean());
			System.out.println(sClasses.get(i).getPercentage());
			System.out.println(sClasses.get(i).getSize());
		}
		
	}
	
	public static int checkmNorms(Agent agent, Content content) {
		System.out.println("Checking mNorms");
			for(int k=0;k<agent.getNorms().size();k++) {
				if(agent.getNorms().get(k).getConType() == content.getHighestContentTypeIndex()) {
					System.out.println("Norm found. Behavior is " + agent.getNorms().get(k).getBehavior() );
					if(agent.getNorms().get(k).getBehavior() == 0) {
						return 0;
					}
					else {
						return 1;
					}
				}
			}
			System.out.println("No related norm found for the coowner");
			return -1;
				//no norms for given content, possibilities, don't bid or bid randomly
	}
	
	public static int checkpNorms(Agent agent, Content content) {
		System.out.println("Checking pNorms");
		float share =0;
		float nShare=0;
		

		//System.out.println("cONTENT COOWNERS");
		for(int k=0; k<content.getCoowners().size();k++) {
			//System.out.print(content.getCoowners().get(k) + " - ");
		}
		
		for(int i = 0; i < agent.getpNorms().size();i++) {
			if(agent.getpNorms().get(i).gethighestConTypeIndex() == content.getHighestContentTypeIndex()) {
				int jumpDif = content.getCoowners().size() - agent.getpNorms().get(i).getcoOwners().size();
				Boolean allIncluded = true;
				

				//System.out.println("");
				
				for(int j = 0; j < agent.getpNorms().get(i).getcoOwners().size();j++) {
					Boolean coownerIncluded = false;
					for(int k = 0; k < content.getCoowners().size();k++) {
						if(agent.getpNorms().get(i).getcoOwners().get(j) == content.getCoowners().get(k))
							coownerIncluded = true;
					}
					if(!coownerIncluded) {
						allIncluded = false;
					}
				}
				if(allIncluded) {
					//System.out.println("Possible pNorm found. The jump distance is " + jumpDif);
					for(int k=0; k<agent.getpNorms().get(i).getcoOwners().size();k++) {
						//System.out.print(agent.getpNorms().get(i).getcoOwners().get(k) + " - ");
					}
					//System.out.println("");
					if(agent.getpNorms().get(i).getAction() == 0)
						nShare += 1/Math.pow(2, jumpDif);
					if(agent.getpNorms().get(i).getAction() == 1)
						share += 1/Math.pow(2, jumpDif);
				}
			}
		}
		
		
		if(share/(share+nShare) > 0.7) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Share pNorm found");
			return 1;
		}
		
		if(nShare/(share+nShare) > 0.7) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Not share pNorm found");
			return 0;
		}
		
		System.out.println("No related pNorm found for the coowner");
		return -1;
	}
	

	public static int checksNorms(Agent agent, Content content,ArrayList<SocialNormClasses> sClasses) {
		System.out.println("Checking sNorms");
		int closestClass = -1;
		int distance = 99999;
		int tempDistance;

		Boolean nShNormExists = false;
		Boolean sNormExists = false;
		
		for(int i=0;i<sClasses.size();i++) {
			
			tempDistance = Math.abs(content.getConType().get(0) - sClasses.get(i).getcT1mean()) +
					Math.abs(content.getConType().get(1) - sClasses.get(i).getcT2mean()) +
					Math.abs(content.getConType().get(2) - sClasses.get(i).getcT3mean()) +
					Math.abs(content.getConType().get(3) - sClasses.get(i).getcT4mean());
			if(tempDistance < distance) {
				distance = tempDistance;
				closestClass = i;
				if (sClasses.get(i).getPercentage() > 66) {
					nShNormExists = true;
				}
				else
					nShNormExists = false;
				if (sClasses.get(i).getPercentage() < 34) {
					sNormExists = true;
				}
				else
					sNormExists = false;
			}
		}
		
		if(sNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Share sNorm found");
			return 1;
		}
		if(nShNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Not Share sNorm found");
			return 0;
		}
		
		return -1;
	}
	
	public static void kMeans(ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses,int sizeParam) {
		int countK = sNormBase.size() / (sNormBase.size()/sizeParam) + 1;
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
		//System.out.println("Loop #: " + loop);
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
			//System.out.println("Class " +i+ " size:"+ classSize);
			if(classSize != 0) {
			t1=t1/classSize;
			t2=t2/classSize;
			t3=t3/classSize;
			t4=t4/classSize;
			}
			else {
				classSize = 1;
				t1=1;
				t2=1;
				t3=1;
				t4=1;
			}
			//System.out.println("Dimension means: " + t1 + "-" + t2 + "-" + t3 + "-" + t4);
			//actionPercentage.add(noShare*100/classSize);
			//System.out.println("Action Percentage: " + noShare*100 / classSize);
			
			Boolean classExists = false;
			int pickedClass = 0;
			for(int k=0;k<sClasses.size();k++) {
				if(i==sClasses.get(k).getclassId()) {
					classExists = true;
					pickedClass = i;
				}
			}
			
			if(classExists) {
				sClasses.get(pickedClass).setcT1mean(t1);
				sClasses.get(pickedClass).setcT2mean(t2);
				sClasses.get(pickedClass).setcT3mean(t3);
				sClasses.get(pickedClass).setcT4mean(t4);
				sClasses.get(pickedClass).setPercentage(noShare*100 / classSize);
				sClasses.get(pickedClass).setSize(classSize);
			}
			else {
				SocialNormClasses newClas = new SocialNormClasses();
				newClas.setclassId(i);;
				newClas.setcT1mean(t1);
				newClas.setcT2mean(t2);
				newClas.setcT3mean(t3);
				newClas.setcT4mean(t4);
				newClas.setPercentage(noShare*100 / classSize);
				newClas.setSize(classSize);
				sClasses.add(newClas);
			}
			conType.add(t1);
			conType.add(t2);
			conType.add(t3);
			conType.add(t4);
			classDimensionMeans.add(conType);
		}
		//System.out.println(actionPercentage.size());
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
		//System.out.println("Swap count: " + swapCount);
		
	}
		
		
	}
	
	public static void deepkMeans(ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses) {

		ArrayList<SocialNormBase> tempNormBase = new ArrayList<SocialNormBase>();
		for (int i=0;i<sNormBase.size();i++) {
			tempNormBase.add(sNormBase.get(i));
		}
		System.out.println("Snormbases:"+ sNormBase.size());
		tempNormBase.clear();
		System.out.println("Ssnormbases:"+ sNormBase.size());
		
		int countK = sNormBase.size() / (sNormBase.size()/5) + 1;
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
		//System.out.println("Loop #: " + loop);
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
			//System.out.println("Class " +i+ " size:"+ classSize);
			if(classSize != 0) {
			t1=t1/classSize;
			t2=t2/classSize;
			t3=t3/classSize;
			t4=t4/classSize;
			}
			else {
				classSize = 1;
				t1=1;
				t2=1;
				t3=1;
				t4=1;
			}
			//System.out.println("Dimension means: " + t1 + "-" + t2 + "-" + t3 + "-" + t4);
			//actionPercentage.add(noShare*100/classSize);
			//System.out.println("Action Percentage: " + noShare*100 / classSize);
			
			Boolean classExists = false;
			int pickedClass = 0;
			for(int k=0;k<sClasses.size();k++) {
				if(i==sClasses.get(k).getclassId()) {
					classExists = true;
					pickedClass = i;
				}
			}
			
			if(classExists) {
				sClasses.get(pickedClass).setcT1mean(t1);
				sClasses.get(pickedClass).setcT2mean(t2);
				sClasses.get(pickedClass).setcT3mean(t3);
				sClasses.get(pickedClass).setcT4mean(t4);
				sClasses.get(pickedClass).setPercentage(noShare*100 / classSize);
			}
			else {
				SocialNormClasses newClas = new SocialNormClasses();
				newClas.setclassId(i);;
				newClas.setcT1mean(t1);
				newClas.setcT2mean(t2);
				newClas.setcT3mean(t3);
				newClas.setcT4mean(t4);
				newClas.setPercentage(noShare*100 / classSize);
				sClasses.add(newClas);
			}
			conType.add(t1);
			conType.add(t2);
			conType.add(t3);
			conType.add(t4);
			classDimensionMeans.add(conType);
		}
		//System.out.println(actionPercentage.size());
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
		//System.out.println("Swap count: " + swapCount);
		
	}
	}
	
}
