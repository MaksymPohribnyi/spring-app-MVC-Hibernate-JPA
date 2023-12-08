package ua.pohribnyi.weblibraryORM.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pohribnyi.weblibraryORM.model.Book;
import ua.pohribnyi.weblibraryORM.model.Reader;
import ua.pohribnyi.weblibraryORM.repositories.BookRepository;

@Service
@Transactional(readOnly = true)
public class BookService {

	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAll(boolean sortByYear) {
		if (sortByYear)
			return bookRepository.findAll(Sort.by("yearOfRelease"));
		return bookRepository.findAll();
	}

	public List<Book> findAll(int page, int booksPerPage, boolean sortByYear) {
		if (sortByYear)
			return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfRelease"))).getContent();
		return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
	}

	public Book findOne(int id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Transactional
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Transactional
	public void update(int id, Book updatedBook) {
		updatedBook.setId(id);
		updatedBook.setOwner(bookRepository.findById(id).orElse(null).getOwner());
		bookRepository.save(updatedBook);
	}

	@Transactional
	public void delete(int id) {
		bookRepository.deleteById(id);
	}

	@Transactional
	public void setOwnerToBookByBookId(Reader reader, int bookId) {
		Book book = findOne(bookId);
		if (book != null) {
			book.setOwner(reader);
			if (reader != null) {
				book.setReaderAssignedAt(new Date());
			} else {
				book.setReaderAssignedAt(null);
			}
			bookRepository.save(book);
		}
	}

	public List<Book> findByTitlePattern(String likePattern) {
		return bookRepository.findByTitleLike("%" + likePattern + "%");
	}

}
