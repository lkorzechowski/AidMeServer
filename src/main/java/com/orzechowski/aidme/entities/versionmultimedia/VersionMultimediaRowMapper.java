package com.orzechowski.aidme.entities.versionmultimedia;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionMultimediaRowMapper implements RowMapper<VersionMultimedia>
{
    @Override
    public VersionMultimedia mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new VersionMultimedia(rs.getLong("version_multimedia_id"),
                rs.getLong("multimedia_id"), rs.getLong("version_id"));
    }
}
