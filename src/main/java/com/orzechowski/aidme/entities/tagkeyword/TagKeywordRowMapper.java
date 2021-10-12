package com.orzechowski.aidme.entities.tagkeyword;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagKeywordRowMapper implements RowMapper<TagKeyword>
{
    @Override
    public TagKeyword mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TagKeyword(rs.getLong("tag_keyword_id"), rs.getLong("keyword_id"),
                rs.getLong("tag_id"));
    }
}
