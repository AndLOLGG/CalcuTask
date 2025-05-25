package org.example.calcutask.Repository;
import org.example.calcutask.Model.UserProjectAccess;
import org.example.calcutask.RowMapper.UserProjectAccessRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserProjectAccessRepository {

    @Autowired
    private JdbcTemplate template;
    
    public UserProjectAccess getAccessByUserId(Integer userId) {
        String sql = "SELECT * FROM user_project_access WHERE user_id = ?";
        return template.queryForObject(sql, new UserProjectAccessRowMapper(), userId);
    }

    public UserProjectAccess hasUserAccessToProject(Integer userId, Integer projectId) {
        String sql = "SELECT * FROM user_project_access WHERE user_id = ? AND project_id = ?";
        return template.queryForObject(sql, userId, projectId, new UserProjectAccessRepositoryRowMapper());
    }
}