package ua.pohribnyi.weblibraryORM.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.pohribnyi.weblibraryORM.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

	List<Reader> findByName(String name);

}
