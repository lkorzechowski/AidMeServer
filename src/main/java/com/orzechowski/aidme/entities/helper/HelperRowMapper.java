package com.orzechowski.aidme.entities.helper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperRowMapper implements RowMapper<Helper>
{
    @Override
    public Helper mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Helper(rs.getLong("helper_id"), rs.getString("helper_name"),
                rs.getString("helper_surname"), rs.getString("helper_title"),
                rs.getString("helper_profession"), rs.getString("email"),
                rs.getString("phone"));
    }
}
