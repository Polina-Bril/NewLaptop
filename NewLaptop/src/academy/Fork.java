package academy;

public class Fork {
	
	private int id;
	private static int forNumeration = 0;
	
	public Fork(){
		forNumeration++;
		id=forNumeration;
	}

	public int getId() {
		return id;
	}
	
	

}
