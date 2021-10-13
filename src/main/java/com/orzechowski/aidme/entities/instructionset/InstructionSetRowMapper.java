package com.orzechowski.aidme.entities.instructionset;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructionSetRowMapper implements RowMapper<InstructionSet>
{
    @Override
    public InstructionSet mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new InstructionSet(rs.getLong("instruction_set_id"), rs.getString("title"),
                rs.getString("instructions"), rs.getInt("time"),
                rs.getLong("tutorial_id"), rs.getInt("set_position"),
                rs.getString("narration_name"));
    }
}
