//package org.example.calcutask.Model;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProjectTest {
//
//    @Test
//    void testProjectGettersAndSetters() {
//        Project project = new Project();
//        project.setProjectId(1);
//        project.setProjectName("Test Project");
//        project.setProjectDescription("Test Description");
//        project.setCreatedDate(LocalDate.now());
//        project.setCreatedBy("User1");
//        project.setUserId(100);
//        project.setAccessType("Private");
//
//        List<Task> tasks = new ArrayList<>();
//        project.setTasks(tasks);
//
//        assertEquals(1, project.getProjectId());
//        assertEquals("Test Project", project.getProjectName());
//        assertEquals("Test Description", project.getProjectDescription());
//        assertEquals(LocalDate.now(), project.getCreatedDate());
//        assertEquals("User1", project.getCreatedBy());
//        assertEquals(100, project.getUserId());
//        assertEquals("Private", project.getAccessType());
//        assertEquals(tasks, project.getTasks());
//    }
//}