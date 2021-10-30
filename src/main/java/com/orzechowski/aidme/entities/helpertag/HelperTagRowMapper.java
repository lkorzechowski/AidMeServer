package com.orzechowski.aidme.entities.helpertag;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperTagRowMapper implements RowMapper<HelperTag>
{
    @Override
    public HelperTag mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new HelperTag(rs.getLong(1), rs.getLong(2), rs.getLong(3));
    }
}
