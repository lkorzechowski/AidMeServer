package com.orzechowski.aidme.entities.document;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentRowMapper implements RowMapper<Document>
{
    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Document(rs.getLong(1), rs.getString(2), rs.getString(3),
                rs.getLong(4));
    }
}
