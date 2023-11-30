package ua.pohribnyi.weblibraryORM.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ua.pohribnyi.weblibraryORM.model.Book;
import ua.pohribnyi.weblibraryORM.model.Reader;
import ua.pohribnyi.weblibraryORM.services.BookService;

@Controller
@RequestMapping("/web-library/books")
public class WebLibraryBookController {

	private BookService bookService;

	@Autowired
	public WebLibraryBookController(BookService bookService) {
		// this.readerDAO = readerDAO;
		//testing switch default branch
		this.bookService = bookService;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "books/index";
	}

	@GetMapping("/{id}")
	public String show(@ModelAttribute("reader") Reader reader, @PathVariable("id") int id, Model model) {
		model.addAttribute("book", bookService.findOne(id));
		/*
		 * Optional<Reader> optionalR = readerDAO.showBookHolder(id); if
		 * (optionalR.isPresent()) model.addAttribute("owner", optionalR.get()); else
		 * model.addAttribute("listOfReaders", readerDAO.index());
		 */
		return "books/show";
	}

	@GetMapping("/new")
	public String create(@ModelAttribute("book") Book book) {
		return "books/new";
	}

	@PostMapping()
	public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "books/new";
		// bookDAO.save(book);
		return "redirect:/web-library/books";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("book", bookService.findOne(id));
		return "books/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "books/edit";
		// bookDAO.update(id, book);
		return "redirect:/web-library/books";
	}

	@PatchMapping("/{id}/release")
	public String releaseBook(@PathVariable("id") int id) {
		// bookDAO.releaseBook(id);
		return "redirect:/web-library/books/" + id;
	}

	@PatchMapping("/{id}/assign")
	public String assignReader(@ModelAttribute("reader") Reader reader, @PathVariable("id") int id) {
		// bookDAO.assignReader(reader.getId(), id);
		return "redirect:/web-library/books/" + id;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		// bookDAO.delete(id);
		return "redirect:/web-library/books";
	}
}
