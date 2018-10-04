package content;

import java.util.ArrayList;

public class Sentiment {
	
	int sentimentType;
	int belonginess;
	
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
