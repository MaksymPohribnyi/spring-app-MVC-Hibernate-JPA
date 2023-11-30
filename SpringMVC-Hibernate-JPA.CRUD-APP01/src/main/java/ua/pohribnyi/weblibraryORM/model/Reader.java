package ua.pohribnyi.weblibraryORM.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "reader")
public class Reader {

	@Id
	@Column(name = "reader_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	@NotEmpty(message = "Name should be not empty")
	@Size(min = 2, max = 30, message = "Name should be more than 2 but not more than 30 characters")
	private String name;

	@Column(name = "birth_date")
	@NotEmpty(message = "Birth date should be not empty")
	@Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Birth date should be a DATE type and format like YYYY-MM-DD")
	private String birthDate;

	@OneToMany(mappedBy = "owner")
	private List<Book> books;

	public Reader() {

	}

	public Reader(int id, String name, String birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
