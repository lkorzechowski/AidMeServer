package com.orzechowski.aidme.entities.rating;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingRowMapper implements RowMapper<Rating>
{
    @Override
    public Rating mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Rating(rs.getLong(1), rs.getLong(2), rs.getString(3),
                rs.getInt(4));
    }
}
