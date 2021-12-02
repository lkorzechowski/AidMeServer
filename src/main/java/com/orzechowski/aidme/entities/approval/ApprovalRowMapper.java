package com.orzechowski.aidme.entities.approval;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApprovalRowMapper implements RowMapper<Approval>
{
    @Override
    public Approval mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Approval(rs.getLong(1), rs.getString(2), rs.getBoolean(3),
                rs.getLong(4));
    }
}
