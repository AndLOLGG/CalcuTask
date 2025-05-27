package org.example.calcutask.Repository;

import org.example.calcutask.Model.User;
import org.example.calcutask.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    // Gem ny bruger
    public void save(User user) {
        String sql = "INSERT INTO user (username, user_password, role) VALUES (?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getRole());
    }

    // Find bruger ud fra brugernavn
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Find bruger ud fra ID
    public User findById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Hent alle brugere (bruges til admin)
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return template.query(sql, new UserRowMapper());
    }

    // Slet bruger ud fra ID
    public void deleteById(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        template.update(sql, userId);
    }

    // Opdater bruger
    public void update(User user) {
        String sql = "UPDATE user SET username = ?, user_password = ? WHERE user_id = ?";
        template.update(sql, user.getUsername(), user.getPassword(), user.getUserId());
    }


}
