package com.orzechowski.aidme.entities.tutoriallink;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialLinkRowMapper implements RowMapper<TutorialLink>
{
    @Override
    public TutorialLink mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new TutorialLink(rs.getLong("tutorial_link_id"), rs.getLong("tutorial_id"),
                rs.getLong("origin_id"), rs.getInt("instruction_number"));
    }
}
