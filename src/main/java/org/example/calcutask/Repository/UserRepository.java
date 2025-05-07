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
        String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPasswordHash(), user.getRole());
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findById(int user_id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), user_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return template.query(sql, new UserRowMapper());
    }

    public void deleteById(int user_id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        template.update(sql, user_id);
    }
}
