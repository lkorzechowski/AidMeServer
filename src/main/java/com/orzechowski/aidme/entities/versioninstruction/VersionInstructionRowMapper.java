package com.orzechowski.aidme.entities.versioninstruction;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionInstructionRowMapper implements RowMapper<VersionInstruction>
{
    @Override
    public VersionInstruction mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new VersionInstruction(rs.getLong("version_instruction_id"),
                rs.getLong("version_id"), rs.getInt("instruction_position"));
    }
}
