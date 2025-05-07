package org.example.calcutask.Repository;
import org.apache.catalina.User;
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
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getRole());
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            return template.queryForObject(sql, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return template.query(sql, new UserRowMapper());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        template.update(sql, id);
    }
}
