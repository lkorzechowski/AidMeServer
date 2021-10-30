package com.orzechowski.aidme.entities.versionmultimedia;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionMultimediaRowMapper implements RowMapper<VersionMultimedia>
{
    @Override
    public VersionMultimedia mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new VersionMultimedia(rs.getLong(1), rs.getLong(3), rs.getLong(2));
    }
}
