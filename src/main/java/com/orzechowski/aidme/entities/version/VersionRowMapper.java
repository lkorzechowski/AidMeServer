package com.orzechowski.aidme.entities.version;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionRowMapper implements RowMapper<Version>
{
    @Override
    public Version mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Version(rs.getLong("version_id"), rs.getString("text"),
                rs.getLong("tutorial_id"), rs.getBoolean("delay_global_sound"),
                rs.getBoolean("has_children"), rs.getBoolean("has_parent"),
                rs.getLong("parent_version_id"));
    }
}
