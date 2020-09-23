package academy;

import java.util.concurrent.ConcurrentHashMap;

public class Philosofer extends Thread {

	private static ConcurrentHashMap<Integer, Fork> forks;
	private int numberOfSeat;
	private static int forNumberOfSeat = 0;
	private static final int PHILOSOFERS = 5;
	private Fork leftFork;
	private Fork rightFork;

	public Philosofer(ConcurrentHashMap<Integer, Fork> forks) {
		forNumberOfSeat++;
		numberOfSeat = forNumberOfSeat;
		Philosofer.forks = forks;
		if (numberOfSeat == PHILOSOFERS) {
			rightFork = forks.get(numberOfSeat);
			leftFork = forks.get(1);
		} else {
			rightFork = forks.get(numberOfSeat);
			leftFork = forks.get(numberOfSeat+1);
		}
	}

	@Override
	public void run() {
		boolean exit = false;
		while (!exit) {
			if (forks.containsKey(leftFork.getId()) && forks.containsKey(rightFork.getId())) {
				synchronized (rightFork) {
					synchronized (leftFork) {
						Fork rf = forks.remove(numberOfSeat);
						System.out.println("Философ " + numberOfSeat + " взял правую вилку");
						Fork lf =null;
						if (numberOfSeat<PHILOSOFERS) {
						lf = forks.remove(numberOfSeat+1);
						}else {
						lf = forks.remove(1);
						}
						System.out.println("Философ " + numberOfSeat + " взял левую вилку");
						System.out.println("Философ " + numberOfSeat + " ест");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						forks.put(rf.getId(), rf);
						System.out.println("Философ " + numberOfSeat + " вернул левую вилку");
						forks.put(lf.getId(), lf);
						System.out.println("Философ " + numberOfSeat + " вернул правую вилку");
						System.out.println("Философ " + numberOfSeat + " размышляет");
						exit = true;
					}
				}
			} else {
				try {
					System.out.println("Философ " + numberOfSeat + " размышляет");
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}