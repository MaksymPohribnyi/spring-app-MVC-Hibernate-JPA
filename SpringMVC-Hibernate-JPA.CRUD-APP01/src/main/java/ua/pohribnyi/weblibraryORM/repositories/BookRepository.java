package ua.pohribnyi.weblibraryORM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.pohribnyi.weblibraryORM.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
