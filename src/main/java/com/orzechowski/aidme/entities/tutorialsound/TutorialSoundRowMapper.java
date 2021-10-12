package com.orzechowski.aidme.entities.tutorialsound;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialSoundRowMapper implements RowMapper<TutorialSound>
{
    @Override
    public TutorialSound mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TutorialSound(rs.getLong("sound_id"), rs.getInt("sound_start"), rs.getBoolean("soundLoop"), rs.getInt("interval"), rs.getLong("tutorial_id"), rs.getString("file_name"));
    }
}
