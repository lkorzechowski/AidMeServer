package com.orzechowski.aidme.entities.version;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionRowMapper implements RowMapper<Version>
{
    @Override
    public Version mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Version(rs.getLong(1), rs.getString(2), rs.getLong(3),
                rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6),
                rs.getLong(7));
    }
}
