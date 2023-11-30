package ua.pohribnyi.weblibraryORM.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pohribnyi.weblibraryORM.model.Reader;
import ua.pohribnyi.weblibraryORM.repositories.ReaderRepository;

@Service
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

}
