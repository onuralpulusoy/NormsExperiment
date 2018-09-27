package content;

import java.util.ArrayList;

public class Content {

	int id;
	String type;
	ArrayList<Integer> coOwners = new ArrayList<Integer>();
	
	public Content(int id, String type, ArrayList<Integer> coOwners) {
		this.id = id;
		this.type = type;
		this.coOwners = coOwners;
	}
	
	public String getContentType() {
		return type;
	}
	
}
