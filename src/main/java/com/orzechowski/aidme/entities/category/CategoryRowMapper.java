package com.orzechowski.aidme.entities.category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category>
{
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Category(rs.getLong(1), rs.getString(2), rs.getBoolean(3),
                rs.getString(4), rs.getInt(5));
    }
}
