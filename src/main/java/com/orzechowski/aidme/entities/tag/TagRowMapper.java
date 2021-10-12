package com.orzechowski.aidme.entities.tag;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagRowMapper implements RowMapper<Tag>
{
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Tag(rs.getLong("tag_id"), rs.getString("tag_name"),
                rs.getInt("tag_level"));
    }
}
