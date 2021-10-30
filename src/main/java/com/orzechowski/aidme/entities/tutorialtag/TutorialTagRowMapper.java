package com.orzechowski.aidme.entities.tutorialtag;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialTagRowMapper implements RowMapper<TutorialTag>
{
    @Override
    public TutorialTag mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TutorialTag(rs.getLong(1), rs.getLong(2), rs.getLong(3));
    }
}
