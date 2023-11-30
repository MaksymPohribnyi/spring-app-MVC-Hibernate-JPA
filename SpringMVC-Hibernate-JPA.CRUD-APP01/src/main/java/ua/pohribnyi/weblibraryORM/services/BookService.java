package ua.pohribnyi.weblibraryORM.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pohribnyi.weblibraryORM.model.Book;
import ua.pohribnyi.weblibraryORM.repositories.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findOne(int id) {
		return bookRepository.findById(id).orElse(null);
	}
}
