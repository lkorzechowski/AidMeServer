package com.orzechowski.aidme.entities.category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category>
{
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Category(rs.getLong("category_id"), rs.getString("category_name"),
                rs.getBoolean("has_subcategories"), rs.getString("miniature_name"),
                rs.getInt("category_level"));
    }
}
