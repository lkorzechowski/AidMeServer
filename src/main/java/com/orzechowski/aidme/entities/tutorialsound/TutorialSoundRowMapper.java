package com.orzechowski.aidme.entities.tutorialsound;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialSoundRowMapper implements RowMapper<TutorialSound>
{
    @Override
    public TutorialSound mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TutorialSound(rs.getLong(1), rs.getInt(2), rs.getBoolean(3),
                rs.getInt(4), rs.getLong(5), rs.getString(6));
    }
}
