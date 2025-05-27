package org.example.calcutask.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.calcutask.Model.User;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
     // user.setUserEmail(rs.getString("user_email"));
        user.setPassword(rs.getString("user_password"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
