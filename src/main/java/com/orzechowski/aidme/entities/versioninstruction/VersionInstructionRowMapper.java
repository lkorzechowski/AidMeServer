package com.orzechowski.aidme.entities.versioninstruction;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionInstructionRowMapper implements RowMapper<VersionInstruction>
{
    @Override
    public VersionInstruction mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new VersionInstruction(rs.getLong(1), rs.getLong(2), rs.getInt(3));
    }
}
