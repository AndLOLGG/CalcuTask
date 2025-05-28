//package org.example.calcutask.ModelTest;
//
//import org.example.calcutask.Model.Status;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class StatusTest {
//
//    @Test
//    void testEnumValues() {
//        Status[] statuses = Status.values();
//        assertNotNull(statuses);
//        assertEquals(5, statuses.length); // There are 5 constants in the Status enum
//        assertArrayEquals(new Status[]{Status.Todo, Status.Igang, Status.Blokeret, Status.Test, Status.Done}, statuses);
//    }
//
//    @Test
//    void testEnumValueOf() {
//        Status status = Status.valueOf("Todo");
//        assertNotNull(status);
//        assertEquals(Status.Todo, status);
//
//        status = Status.valueOf("Done");
//        assertNotNull(status);
//        assertEquals(Status.Done, status);
//    }
//}