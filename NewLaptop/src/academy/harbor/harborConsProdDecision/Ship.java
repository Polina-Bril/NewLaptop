package academy.harbor.harborConsProdDecision;

import java.util.Random;

public class Ship extends Thread {

	private int cargo;
	private static int toGetCargo = 15;
	private Harbor harbor;

	public Ship(Harbor harbor) {
		this.harbor = harbor;
		Random rand = new Random();
		cargo = rand.nextInt(7);
	}

	@Override
	public void run() {
		if (cargo == 0) {
			harbor.get(toGetCargo);
		} else {
			harbor.put(cargo);
		}
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

}
