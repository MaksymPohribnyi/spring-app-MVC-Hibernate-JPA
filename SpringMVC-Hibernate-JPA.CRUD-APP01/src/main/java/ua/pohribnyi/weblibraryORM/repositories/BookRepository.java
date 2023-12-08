package ua.pohribnyi.weblibraryORM.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.pohribnyi.weblibraryORM.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByTitleLike(String likePattern);

}
