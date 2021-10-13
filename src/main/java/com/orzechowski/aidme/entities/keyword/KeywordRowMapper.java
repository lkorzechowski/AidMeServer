package com.orzechowski.aidme.entities.keyword;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KeywordRowMapper implements RowMapper<Keyword>
{
    @Override
    public Keyword mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Keyword(rs.getLong(1), rs.getString(2));
    }
}
