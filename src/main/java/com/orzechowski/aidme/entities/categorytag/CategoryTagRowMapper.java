package com.orzechowski.aidme.entities.categorytag;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryTagRowMapper implements RowMapper<CategoryTag>
{
    @Override
    public CategoryTag mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new CategoryTag(rs.getLong("category_tag_id"), rs.getLong("category_id"),
                rs.getLong("tag_id"));
    }
}
