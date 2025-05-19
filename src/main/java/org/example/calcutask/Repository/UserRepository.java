package org.example.calcutask.Repository;

import org.example.calcutask.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.example.calcutask.RowMapper.UserRowMapper;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    public void save(User user) {
        String sql = "INSERT INTO user (username, password_hash, role) VALUES (?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPasswordHash(), user.getRole());
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return template.query(sql, new UserRowMapper());
    }

    public void deleteById(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        template.update(sql, userId);
    }



}