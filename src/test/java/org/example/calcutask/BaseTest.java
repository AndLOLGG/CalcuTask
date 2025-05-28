package org.example.calcutask;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test") // Use application-test.properties
@SpringBootTest
public abstract class BaseTest {
}