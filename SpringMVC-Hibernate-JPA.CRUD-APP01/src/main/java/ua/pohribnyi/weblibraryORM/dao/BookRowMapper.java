package ua.pohribnyi.weblibraryORM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.pohribnyi.weblibraryORM.model.Book;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("book_id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setYearOfRelease(rs.getInt("year_of_release"));
		return book;
	}

}
