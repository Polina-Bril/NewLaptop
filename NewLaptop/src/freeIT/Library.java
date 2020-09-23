package freeIT;

import java.util.concurrent.CopyOnWriteArrayList;

public class Library {

	private CopyOnWriteArrayList<Book> booksForRead;
	private CopyOnWriteArrayList<Book> booksForHome;

	public Library(CopyOnWriteArrayList<Book> booksForRead, CopyOnWriteArrayList<Book> booksForHome) {
		this.booksForHome = booksForHome;
		this.booksForRead = booksForRead;
	}

	public synchronized void getToHome(Customer customer, Book hisBookToHome, int bookID) {
		while (hisBookToHome.getQuantityOfThisBooks() == 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " ожидает запрошенную книгу. Ее взяли домой: "
						+ hisBookToHome);
				wait();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		customer.getHisBooksForHome().add(hisBookToHome);
		booksForHome.get(bookID).setQuantityOfThisBooks(hisBookToHome.getQuantityOfThisBooks() - 1);

		System.out.println(Thread.currentThread().getName() + " читатель взял 1 книгу домой: " + hisBookToHome);
		System.out.println(Thread.currentThread().getName() + " " + hisBookToHome.getQuantityOfThisBooks()
				+ " этих книг для взятия домой осталось в библиотеке");
		notify();
	}

	public synchronized void getToRead(Customer customer, Book hisBookToRead, int bookID) {
		while (hisBookToRead.getQuantityOfThisBooks() == 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " ожидает запрошенную книгу из читального зала: "
						+ hisBookToRead);
				wait();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		customer.getHisBooksForRead().add(hisBookToRead);
		booksForRead.get(bookID).setQuantityOfThisBooks(hisBookToRead.getQuantityOfThisBooks() - 1);

		System.out
				.println(Thread.currentThread().getName() + " читатель взял 1 книгу в читальный зал: " + hisBookToRead);
		System.out.println(Thread.currentThread().getName() + " " + hisBookToRead.getQuantityOfThisBooks()
				+ " этих книг для чтения в читальном зале осталось в библиотеке");
		notify();
	}

	public synchronized void putFromReading(Customer customer, Book hisBookFromRead, int bookID) {

		booksForRead.get(bookID).setQuantityOfThisBooks(hisBookFromRead.getQuantityOfThisBooks() + 1);

		System.out.println(Thread.currentThread().getName() + " читатель вернул 1 книгу после чтения в читальном зале: "
				+ hisBookFromRead);
		System.out.println(Thread.currentThread().getName() + " " + hisBookFromRead.getQuantityOfThisBooks()
				+ " этих книг для чтения в читальном зале сейчас в библиотеке");
		customer.getHisBooksForRead().remove(hisBookFromRead);
		notify();
	}

	public synchronized void putFromHome(Customer customer, Book hisBookFromHome, int bookID) {

		booksForHome.get(bookID).setQuantityOfThisBooks(hisBookFromHome.getQuantityOfThisBooks() + 1);

		System.out.println(
				Thread.currentThread().getName() + " читатель вернул 1 книгу после чтения дома: " + hisBookFromHome);
		System.out.println(Thread.currentThread().getName() + " " + hisBookFromHome.getQuantityOfThisBooks()
				+ " этих книг для чтения дома сейчас в библиотеке");
		customer.getHisBooksForHome().remove(hisBookFromHome);
		notify();
	}

	public CopyOnWriteArrayList<Book> getBooksForRead() {
		return booksForRead;
	}

	public void setBooksForRead(CopyOnWriteArrayList<Book> booksForRead) {
		this.booksForRead = booksForRead;
	}

	public CopyOnWriteArrayList<Book> getBooksForHome() {
		return booksForHome;
	}

	public void setBooksForHome(CopyOnWriteArrayList<Book> booksForHome) {
		this.booksForHome = booksForHome;
	}

}
