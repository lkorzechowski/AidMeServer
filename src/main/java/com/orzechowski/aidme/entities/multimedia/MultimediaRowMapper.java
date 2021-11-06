package com.orzechowski.aidme.entities.multimedia;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MultimediaRowMapper implements RowMapper<Multimedia>
{
    @Override
    public Multimedia mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Multimedia(rs.getLong(1), rs.getLong(2), rs.getInt(3),
                rs.getBoolean(4), rs.getString(5), rs.getBoolean(6),
                rs.getInt(7));
    }
}
