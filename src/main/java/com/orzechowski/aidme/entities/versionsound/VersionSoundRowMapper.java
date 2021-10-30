package com.orzechowski.aidme.entities.versionsound;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionSoundRowMapper implements RowMapper<VersionSound>
{
    @Override
    public VersionSound mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new VersionSound(rs.getLong(1), rs.getLong(3),
                rs.getLong(2));
    }
}
