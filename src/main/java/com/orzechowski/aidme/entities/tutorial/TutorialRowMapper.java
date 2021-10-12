package com.orzechowski.aidme.entities.tutorial;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialRowMapper implements RowMapper<Tutorial>
{
    @Override
    public Tutorial mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Tutorial(rs.getLong("tutorial_id"), rs.getString("tutorial_name"),
                rs.getLong("author_id"), rs.getString("miniature_name"),
                rs.getFloat("rating"));
    }
}
