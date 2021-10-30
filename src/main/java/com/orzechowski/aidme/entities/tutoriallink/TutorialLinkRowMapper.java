package com.orzechowski.aidme.entities.tutoriallink;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialLinkRowMapper implements RowMapper<TutorialLink>
{
    @Override
    public TutorialLink mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TutorialLink(rs.getLong(1), rs.getLong(2), rs.getLong(3),
                rs.getInt(4));
    }
}
