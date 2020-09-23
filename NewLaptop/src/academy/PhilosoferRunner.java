package academy;

import java.util.concurrent.ConcurrentHashMap;

public class PhilosoferRunner {

	public static void main(String[] args) {
		ConcurrentHashMap<Integer, Fork> forks = new ConcurrentHashMap<>();
		for (int i = 0; i < 5; i++) {
			Fork fork = new Fork();
			forks.put(fork.getId(), fork);
		}

		Philosofer ph1 = new Philosofer(forks);
		Philosofer ph2 = new Philosofer(forks);
		Philosofer ph3 = new Philosofer(forks);
		Philosofer ph4 = new Philosofer(forks);
		Philosofer ph5 = new Philosofer(forks);

		ph1.start();
		ph2.start();
		ph3.start();
		ph4.start();
		ph5.start();
	}

}
