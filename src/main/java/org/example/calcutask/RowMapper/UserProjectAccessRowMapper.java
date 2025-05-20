package org.example.calcutask.RowMapper;

import org.example.calcutask.Model.UserProjectAccess;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProjectAccessRowMapper implements RowMapper<UserProjectAccess> {
    @Override
    public UserProjectAccess mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserProjectAccess upa = new UserProjectAccess();
        upa.setUserId(rs.getInt("user_id"));
        upa.setProjectId(rs.getString("project_id"));
        upa.setAccessType(rs.getAccessType("access_type"));
        return upa;
    }
}