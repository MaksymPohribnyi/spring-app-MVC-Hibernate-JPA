package ua.pohribnyi.weblibraryORM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.pohribnyi.weblibraryORM.model.Reader;

public class ReaderRowMapper implements RowMapper<Reader> {

	@Override
	public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reader reader = new Reader();
		reader.setId(rs.getInt("reader_id"));
		reader.setName(rs.getString("name"));
		reader.setBirthDate(rs.getString("birth_date"));
		return reader;
	}

}
