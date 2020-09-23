package freeIT;

import java.util.Random;

public class Customer3 extends Thread {
	
	private int booksToOrFromHome;
	private int booksToOrFromRead;
	private Library library;
	private int taking; //если 0 - берем книги, если 1 - сдаем

	public Customer(Library library) {
		this.library = library;
		Random rand = new Random();
		taking = rand.nextInt(2);
		booksToOrFromHome = rand.nextInt(3);
		booksToOrFromRead = rand.nextInt(3);

	}

	@Override
	public void run() {
		if (taking == 0) {
			if(booksToOrFromHome!=0) library.getToHome(booksToOrFromHome);
			if(booksToOrFromRead!=0)library.getToRead(booksToOrFromRead);
		} else {
			if(booksToOrFromHome!=0)library.putFromHome(booksToOrFromHome);
			if(booksToOrFromRead!=0)library.putFromReading(booksToOrFromRead);
		}
	}
}
