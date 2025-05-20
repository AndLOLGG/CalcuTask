package org.example.calcutask.Repository;

import org.example.calcutask.Model.Project;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;
    
    public UserProjectAccess getAccessByUserId(Integer userId) {
        String sql = "SELECT * FROM user_project_access WHERE user_id = ?";
        return template.queryForObject(sql, new UserProjectAccessRowMapper(), userId);
    }
}