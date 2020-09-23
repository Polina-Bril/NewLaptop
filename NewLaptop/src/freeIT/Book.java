package freeIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Book {

//	private int id;
//	private static int forID = 0;

	private String name;
	private String author;
	private String genre;
	private boolean isSoftCover;
	private int yearOfPublishing;
	private int forTakingHome;
	private int quantityOfThisBooks;
	
	public Book(String name, String author, String genre, boolean isSoftCover, int yearOfPublishing, int forTakingHome, int quantityOfThisBooks) {
		this.name=name;
		this.author= author;
		this.genre= genre;
		this.isSoftCover=isSoftCover;
		this.yearOfPublishing=yearOfPublishing;
		this.forTakingHome=forTakingHome;
		this.quantityOfThisBooks=quantityOfThisBooks;
	}

	public Book() {
//		forID++;
//		id = forID;
		Random rand = new Random();
		forTakingHome = rand.nextInt(2); // если 0 - можно брать домой, если 1 - для чтения в читальном зале
		quantityOfThisBooks = rand.nextInt(4) + 1;
		ArrayList<String> authors = new ArrayList<>(
				List.of("Pushkin", "Kovalev", "Agata Kristi", "Sviridova", "Jul Vern"));
		author = authors.get(rand.nextInt(authors.size()));
		ArrayList<String> genres = new ArrayList<>(List.of("Roman", "Detective", "Classic", "Science", "Fantastic"));
		genre = genres.get(rand.nextInt(genres.size()));
		yearOfPublishing = rand.nextInt(71) + 1950; // года рандомно от 1950 до 2020 включительно
		int randForCover = rand.nextInt(2);
		if (randForCover == 0)
			isSoftCover = true;
		else
			isSoftCover = false;
	}

	public int getForTakingHome() {
		return forTakingHome;
	}

	public void setForTakingHome(int forTakingHome) {
		this.forTakingHome = forTakingHome;
	}

	public int getQuantityOfThisBooks() {
		return quantityOfThisBooks;
	}

	public void setQuantityOfThisBooks(int quantityOfThisBooks) {
		this.quantityOfThisBooks = quantityOfThisBooks;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", genre=" + genre + ", isSoftCover=" + isSoftCover
				+ ", yearOfPublishing=" + yearOfPublishing + ", forTakingHome=" + forTakingHome
				+ ", quantityOfThisBooks=" + quantityOfThisBooks + "]";
	}


}
