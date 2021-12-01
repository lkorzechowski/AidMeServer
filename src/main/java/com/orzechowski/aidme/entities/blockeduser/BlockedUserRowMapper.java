package com.orzechowski.aidme.entities.blockeduser;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlockedUserRowMapper implements RowMapper<BlockedUser>
{
    @Override
    public BlockedUser mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new BlockedUser(rs.getString(1), rs.getString(2));
    }
}
