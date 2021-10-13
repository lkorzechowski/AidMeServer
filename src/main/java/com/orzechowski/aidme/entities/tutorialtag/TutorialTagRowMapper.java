package com.orzechowski.aidme.entities.tutorialtag;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialTagRowMapper implements RowMapper<TutorialTag>
{
    @Override
    public TutorialTag mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TutorialTag(rs.getLong("tutorial_tag_id"), rs.getLong("tutorial_id"),
                rs.getLong("tag_id"));
    }
}
