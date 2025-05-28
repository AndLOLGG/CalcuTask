//package org.example.calcutask.ServiceTest;
//
//import org.example.calcutask.Model.UserProjectAccess;
//import org.example.calcutask.Repository.UserProjectAccessRepository;
//import org.example.calcutask.Service.UserProjectAccessService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//class UserProjectAccessServiceTest {
//
//    @Mock
//    private UserProjectAccessRepository userProjectAccessRepository;
//
//    @InjectMocks
//    private UserProjectAccessService userProjectAccessService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testHasUserAccessToProject_UserHasAccess() {
//        Integer userId = 1;
//        Integer projectId = 1;
//
//        when(userProjectAccessRepository.hasUserAccessToProject(userId, projectId)).thenReturn(new UserProjectAccess());
//
//        boolean result = userProjectAccessService.hasUserAccessToProject(userId, projectId);
//
//        assertTrue(result);
//        verify(userProjectAccessRepository, times(1)).hasUserAccessToProject(userId, projectId);
//    }
//
//    @Test
//    void testHasUserAccessToProject_UserHasNoAccess() {
//        Integer userId = 1;
//        Integer projectId = 1;
//
//        when(userProjectAccessRepository.hasUserAccessToProject(userId, projectId)).thenReturn(null);
//
//        boolean result = userProjectAccessService.hasUserAccessToProject(userId, projectId);
//
//        assertFalse(result);
//        verify(userProjectAccessRepository, times(1)).hasUserAccessToProject(userId, projectId);
//    }
//}