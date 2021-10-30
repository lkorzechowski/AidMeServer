package com.orzechowski.aidme.entities.tagkeyword;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagKeywordRowMapper implements RowMapper<TagKeyword>
{
    @Override
    public TagKeyword mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TagKeyword(rs.getLong(1), rs.getLong(3), rs.getLong(2));
    }
}
