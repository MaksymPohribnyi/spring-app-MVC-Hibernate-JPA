package ua.pohribnyi.weblibraryORM.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.pohribnyi.weblibraryORM.model.Reader;
import ua.pohribnyi.weblibraryORM.services.ReaderService;

@Component
public class ReaderValidator implements Validator {

	private final ReaderService readerService;

	@Autowired
	public ReaderValidator(ReaderService readerService) {
		this.readerService = readerService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Reader.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Reader reader = (Reader) target;
		if (readerService.findByName(reader.getName()).size() > 0) {
			errors.rejectValue("name", null, "This Name of reader is not unique");
		}
	}

}
