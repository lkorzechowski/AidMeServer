package com.orzechowski.aidme.entities.helper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperLoginMapper implements RowMapper<Login>
{
    @Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Login(rs.getBoolean(1), rs.getBoolean(2));
    }
}
