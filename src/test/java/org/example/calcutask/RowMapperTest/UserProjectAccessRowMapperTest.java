//package org.example.calcutask.RowMapperTest;
//
//import org.example.calcutask.Model.UserProjectAccess;
//import org.example.calcutask.RowMapper.UserProjectAccessRowMapper;
//import org.junit.jupiter.api.Test;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UserProjectAccessRowMapperTest {
//
//    @Test
//    void testMapRow() throws SQLException {
//        // Mock ResultSet
//        ResultSet resultSet = mock(ResultSet.class);
//        when(resultSet.getInt("user_id")).thenReturn(1);
//        when(resultSet.getInt("project_id")).thenReturn(101);
//        when(resultSet.getString("access_type")).thenReturn("READ_WRITE");
//
//        // Test the RowMapper
//        UserProjectAccessRowMapper rowMapper = new UserProjectAccessRowMapper();
//        UserProjectAccess access = rowMapper.mapRow(resultSet, 1);
//
//        // Assertions
//        assertAll(
//                () -> assertEquals(1, access.getUserId(), "User ID mismatch"),
//                () -> assertEquals(101, access.getProjectId(), "Project ID mismatch"),
//                () -> assertEquals("READ_WRITE", access.getAccessType(), "Access Type mismatch")
//        );
//    }
//}