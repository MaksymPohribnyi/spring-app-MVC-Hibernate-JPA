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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import ua.pohribnyi.weblibraryORM.model.Book;
import ua.pohribnyi.weblibraryORM.model.Reader;
import ua.pohribnyi.weblibraryORM.services.BookService;
import ua.pohribnyi.weblibraryORM.services.ReaderService;

@Controller
@RequestMapping("/web-library/books")
public class WebLibraryBookController {

	private BookService bookService;
	private ReaderService readerService;

	@Autowired
	public WebLibraryBookController(BookService bookService, ReaderService readerService) {
		this.bookService = bookService;
		this.readerService = readerService;
	}

	@GetMapping()
	public String index(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
			@RequestParam(value = "sort_by_year", required = false) boolean sortByYear, Model model) {

		if (page == null || booksPerPage == null) {
			model.addAttribute("books", bookService.findAll(sortByYear));
		} else {
			model.addAttribute("books", bookService.findAll(page, booksPerPage, sortByYear));
		}
		return "books/index";
	}

	@GetMapping("/{id}")
	public String show(@ModelAttribute("reader") Reader reader, @PathVariable("id") int id, Model model) {

		Book book = bookService.findOne(id);

		model.addAttribute("book", book);

		Reader optionalReader = book.getOwner();
		if (optionalReader != null)
			model.addAttribute("owner", optionalReader);
		else
			model.addAttribute("listOfReaders", readerService.findAll());

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
		bookService.save(book);
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
		bookService.update(id, book);
		return "redirect:/web-library/books";
	}

	@PatchMapping("/{id}/release")
	public String releaseBook(@PathVariable("id") int id) {
		bookService.setOwnerToBookByBookId(null, id);
		return "redirect:/web-library/books/" + id;
	}

	@PatchMapping("/{id}/assign")
	public String assignReader(@ModelAttribute("reader") Reader reader, @PathVariable("id") int bookId) {
		bookService.setOwnerToBookByBookId(reader, bookId);
		return "redirect:/web-library/books/" + bookId;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		bookService.delete(id);
		return "redirect:/web-library/books";
	}

	@GetMapping("/search")
	public String searchPage(@ModelAttribute("book") Book book) {
		return "books/search";
	}

	@PostMapping("/search")
	public String searchTheBookByTitlePattern(Model model,
			@RequestParam(name = "title", required = true) String titleForSearch) {
		model.addAttribute("books", bookService.findByTitlePattern(titleForSearch));
		return "books/search";
	}

}
