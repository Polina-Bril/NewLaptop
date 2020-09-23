package academy.harbor.harborConsProdDecision;

public class HarborRunner {

	public static void main(String[] args) {

		Harbor harbor = new Harbor();
		Ship ship1 = new Ship(harbor);
		Ship ship2 = new Ship(harbor);
		Ship ship3 = new Ship(harbor);
		Ship ship4 = new Ship(harbor);
		Ship ship5 = new Ship(harbor);
		Ship ship6 = new Ship(harbor);
		Ship ship7 = new Ship(harbor);
		Ship ship8 = new Ship(harbor);

		ship1.start();
		ship2.start();
		ship3.start();
		ship4.start();
		ship5.start();
		ship6.start();
		ship7.start();
		ship8.start();
	}
}
