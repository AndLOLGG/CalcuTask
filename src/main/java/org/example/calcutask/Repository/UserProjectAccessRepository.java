package org.example.calcutask.Repository;
import org.example.calcutask.Model.UserProjectAccess;
import org.example.calcutask.RowMapper.UserProjectAccessRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return template.queryForObject(sql, new UserProjectAccessRowMapper(), userId, projectId);
    }
    public void removeAllAccessForUser(int userId) {
        String sql = "DELETE FROM user_project_access WHERE user_id = ?";
        template.update(sql, userId);
    }

    public void addAccess(UserProjectAccess access) {
        String sql = "INSERT INTO user_project_access (user_id, project_id, access_type) VALUES (?, ?, ?)";
        template.update(sql, access.getUserId(), access.getProjectId(), access.getAccessType());
    }
    public List<Integer> findProjectIdsByUserId(int userId) {
        String sql = "SELECT project_id FROM user_project_access WHERE user_id = ?";
        return template.queryForList(sql, Integer.class, userId);
    }

}