package com.orzechowski.aidme.entities.categorytag;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryTagRowMapper implements RowMapper<CategoryTag>
{
    @Override
    public CategoryTag mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new CategoryTag(rs.getLong(1), rs.getLong(2), rs.getLong(3));
    }
}
