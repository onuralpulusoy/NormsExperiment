package content;

import java.util.ArrayList;

public class Sentiment {
	
	int sentimentType;
	int belonginess;
	
	//Commented by OU on 13/04/2020
	//DESC:Sentiments describe contextual properties for the content
	//Each sentiment type has an integer belonginess value, which is set between 0 and 100 for our experiments
	
	public Sentiment(int sentimentType,int belonginess) {
		this.sentimentType = sentimentType;
		this.belonginess = belonginess;
	}
	
	public int getSentimentType() {
		return this.sentimentType;
	}
	
	public int getBelonginess() {
		return this.belonginess;
	}

}
