package content;

import java.util.ArrayList;
import java.util.Random;

public class Content {

	int id;
	int highestConTypeIndex = -1;
	//change this to map int,int. first one is conType(work etc.) second one is 0-100 how much it fits to that type
	ArrayList<Integer> conType = new ArrayList<Integer>();
	int majorRelationship = 0;
	ArrayList<Sentiment> sentiments = new ArrayList<Sentiment>();
	//time of day is an integer between 0-23, representing hours
	int timeOfDay;
	ArrayList<Integer> coOwners = new ArrayList<Integer>();
	
	public Content(int id) {
		this.id = id;
	}
	
	public Content(int id, ArrayList<Integer> coOwners) {
		this.id = id;
		this.coOwners = coOwners;
	}
	
	public void addSentiment(Sentiment sentiment) {
		this.sentiments.add(sentiment);
		this.highestConTypeIndex = -1;
		updateContentTypes(this.sentiments);
	}
	
	public ArrayList<Sentiment> getSentiments() {
		return this.sentiments;
	}
	
	public ArrayList<Integer> getCoowners() {
		return this.coOwners;
	}
	
	public int getRelationship(){
		//will use coOwners to determine the major relationship between coowners
		return 0;
	}
	
	public int getHighestContentTypeIndex(){
		return this.highestConTypeIndex;
	}
	
	public void updateContentTypes(ArrayList<Sentiment> sentiments){
		int rando = 0;
		int highestConType = -1;
		for(int i=0;i<sentiments.size();i++) {
			//compute all sentiments' distance to conTypes
		}
		
		for(int i=0;i<4;i++) {
			rando =  new Random().nextInt(100);
			this.conType.add(rando);
			if(rando >highestConType) {
				highestConType = rando;
				this.highestConTypeIndex = i;
			}
		}
			
	}
	
	public ArrayList<Integer> getConType(){
		return this.conType;
	}
	
	
}
