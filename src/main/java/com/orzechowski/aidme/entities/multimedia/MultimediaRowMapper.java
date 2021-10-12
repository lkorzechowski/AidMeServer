package com.orzechowski.aidme.entities.multimedia;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MultimediaRowMapper implements RowMapper<Multimedia>
{
    @Override
    public Multimedia mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Multimedia(rs.getLong("multimedia_id"), rs.getLong("tutorial_id"),
                rs.getInt("display_time"), rs.getBoolean("multimedia_type"),
                rs.getString("file_name"), rs.getBoolean("multimedia_loop"),
                rs.getInt("multimedia_position"));
    }
}
