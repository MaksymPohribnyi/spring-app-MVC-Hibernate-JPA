package ua.pohribnyi.weblibraryORM.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pohribnyi.weblibraryORM.model.Book;
import ua.pohribnyi.weblibraryORM.model.Reader;
import ua.pohribnyi.weblibraryORM.repositories.ReaderRepository;

@Service
@Transactional(readOnly = true)
public class ReaderService {

	private ReaderRepository readerRepository;

	@Autowired
	public ReaderService(ReaderRepository readerRepository) {
		this.readerRepository = readerRepository;
	}

	public List<Reader> findAll() {
		return readerRepository.findAll();
	}

	public Reader findOne(int id) {
		return readerRepository.findById(id).orElse(null);
	}

	public List<Reader> findByName(String name) {
		return readerRepository.findByName(name);
	}

	@Transactional
	public void save(Reader reader) {
		readerRepository.save(reader);
	}

	@Transactional
	public void update(int id, Reader updatedReader) {
		updatedReader.setId(id);
		readerRepository.save(updatedReader);
	}

	@Transactional
	public void delete(int id) {
		readerRepository.deleteById(id);
	}

	public List<Book> getBookListByReaderId(int id) {
		Optional<Reader> reader = readerRepository.findById(id);
		if (reader.isPresent()) {
			Hibernate.initialize(reader.get().getBooks());
			List<Book> books = reader.get().getBooks();
			checkIsDateOfReturnIsExpired(books);
			return books;
		} else {
			return Collections.emptyList();
		}
	}

	private void checkIsDateOfReturnIsExpired(List<Book> books) {
		for (Book book : books) {
			if (book.getReaderAssignedAt() != null) {
				long currentTime = System.currentTimeMillis();
				book.setDateOfReturnIsExpired(
						TimeUnit.MILLISECONDS.toDays(currentTime - book.getReaderAssignedAt().getTime()) > 10);
			}
		}

	}

}
