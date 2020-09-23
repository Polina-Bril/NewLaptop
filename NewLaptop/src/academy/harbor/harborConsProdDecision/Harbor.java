package academy.harbor.harborConsProdDecision;

public class Harbor {

	private int cargoAtHarbor;
	private static int harborCapacity = 30;

	public synchronized void get(int toGetCargo) {
		while (cargoAtHarbor < toGetCargo) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		cargoAtHarbor-=toGetCargo;
		System.out.println(Thread.currentThread().getName() + " ------------");
		System.out.println(Thread.currentThread().getName() + " ship loaded "+toGetCargo+" tons of cargo");
		System.out.println(Thread.currentThread().getName() + " " + cargoAtHarbor+" tons of cargo left at Harbor");
		System.out.println(Thread.currentThread().getName() + " ------------");
		notify();
	}

	public synchronized void put(int cargoFromShip) {
		while (cargoAtHarbor+cargoFromShip >= harborCapacity) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e);

			}
		}
		cargoAtHarbor+=cargoFromShip;
		System.out.println(Thread.currentThread().getName() + "------------");
		System.out.println(Thread.currentThread().getName() + " ship unloaded "+cargoFromShip+" tons of cargo");
		System.out.println(Thread.currentThread().getName() +" "+ cargoAtHarbor+" tons of cargo now at Harbor");
		notify();
	}

}
