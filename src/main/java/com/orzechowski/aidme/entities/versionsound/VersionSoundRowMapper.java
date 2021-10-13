package com.orzechowski.aidme.entities.versionsound;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionSoundRowMapper implements RowMapper<VersionSound>
{
    @Override
    public VersionSound mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new VersionSound(rs.getLong("version_sound_id"), rs.getLong("sound_id"),
                rs.getLong("version_id"));
    }
}
