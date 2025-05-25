package org.example.calcutask.Service;

import org.example.calcutask.Repository.UserProjectAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjectAccessService {

    @Autowired
    private UserProjectAccessRepository userProjectAccessRepository;

    public Boolean hasUserAccessToProject(Integer userId, Integer projectId) {
        if(userProjectAccessRepository.hasUserAccessToProject(userId, projectId) == null) {
            return false;
        }
        return true;
    }
}

