package com.orzechowski.aidme.entities.helper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperBasicMapper implements RowMapper<Helper>
{
    @Override
    public Helper mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Helper(rs.getLong(1), rs.getString(2), rs.getString(3),
                rs.getString(4), rs.getString(5));
    }
}
