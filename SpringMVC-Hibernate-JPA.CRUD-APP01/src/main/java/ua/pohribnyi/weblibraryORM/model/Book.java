package ua.pohribnyi.weblibraryORM.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

	@Column(name = "title")
	@NotEmpty(message = "Title should be not empty")
	@Size(min = 2, max = 30, message = "Title should be more than 2 but not more than 30 characters")
	private String title;

	@Column(name = "author")
	@NotEmpty(message = "Author should be not empty")
	private String author;

	@Column(name = "year_of_release")
	@Min(value = 1850, message = "Year of release should be greater then 1850")
	private int yearOfRelease;

	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "reader_id", referencedColumnName = "reader_id")
	private Reader owner;

	public Book() {

	}

	public Book(String title, String author, int yearOfRelease, int id) {
		this.title = title;
		this.author = author;
		this.yearOfRelease = yearOfRelease;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reader getOwner() {
		return owner;
	}

	public void setOwner(Reader owner) {
		this.owner = owner;
	}

}
