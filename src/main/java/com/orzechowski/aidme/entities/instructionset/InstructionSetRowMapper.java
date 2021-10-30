package com.orzechowski.aidme.entities.instructionset;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructionSetRowMapper implements RowMapper<InstructionSet>
{
    @Override
    public InstructionSet mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new InstructionSet(rs.getLong(1), rs.getString(2), rs.getString(3),
                rs.getInt(4), rs.getLong(5), rs.getInt(6), rs.getString(7));
    }
}
