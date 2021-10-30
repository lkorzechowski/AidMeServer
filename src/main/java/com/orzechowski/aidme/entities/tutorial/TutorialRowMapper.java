package com.orzechowski.aidme.entities.tutorial;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialRowMapper implements RowMapper<Tutorial>
{
    @Override
    public Tutorial mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Tutorial(rs.getLong(1), rs.getString(2), rs.getLong(3),
                rs.getString(4), rs.getFloat(5));
    }
}
