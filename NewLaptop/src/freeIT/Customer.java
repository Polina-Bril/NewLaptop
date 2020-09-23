package freeIT;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Customer extends Thread {

	private CopyOnWriteArrayList<Book> hisBooksForRead;
	private CopyOnWriteArrayList<Book> hisBooksForHome;
	private int booksToHome;
	private int booksToRead;
	private Library library;

	public Customer(Library library) {
		hisBooksForRead = new CopyOnWriteArrayList<>();
		hisBooksForHome = new CopyOnWriteArrayList<>();
		this.library = library;
		Random rand = new Random();
		booksToHome = rand.nextInt(3);
		booksToRead = rand.nextInt(3);
	}

	@Override
	public void run() {
		Random rand = new Random();
		if (booksToHome != 0) {
			for (int i = 0; i < booksToHome; i++) {
				int bookID = rand.nextInt(library.getBooksForHome().size());
				Book hisBookToHome = library.getBooksForHome().get(bookID);
				library.getToHome(this, hisBookToHome, bookID);
			}
		}
		if (booksToRead != 0) {
			for (int i = 0; i < booksToRead; i++) {
				int bookID = rand.nextInt(library.getBooksForRead().size());
				Book hisBookToRead = library.getBooksForRead().get(bookID);
				hisBooksForRead.add(hisBookToRead);
				library.getToRead(this, hisBookToRead, bookID);
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (!hisBooksForRead.isEmpty()) {
			for (int i = 0; i < hisBooksForRead.size(); i++) {
				Book hisBookFromRead = hisBooksForRead.get(i);
				int bookID = library.getBooksForRead().indexOf(hisBookFromRead);
				library.putFromReading(this, hisBookFromRead, bookID);
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!hisBooksForHome.isEmpty()) {
			for (int i = 0; i < hisBooksForHome.size(); i++) {
				Book hisBookFromHome = hisBooksForHome.get(i);
				int bookID = library.getBooksForHome().indexOf(hisBookFromHome);
				library.putFromHome(this, hisBookFromHome, bookID);
			}
		}
	}

	public CopyOnWriteArrayList<Book> getHisBooksForRead() {
		return hisBooksForRead;
	}

	public void setHisBooksForRead(CopyOnWriteArrayList<Book> hisBooksForRead) {
		this.hisBooksForRead = hisBooksForRead;
	}

	public CopyOnWriteArrayList<Book> getHisBooksForHome() {
		return hisBooksForHome;
	}

	public void setHisBooksForHome(CopyOnWriteArrayList<Book> hisBooksForHome) {
		this.hisBooksForHome = hisBooksForHome;
	}

}
