-- DROP EXISTING TABLES
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS USER_PROJECT_ACCESS;
DROP TABLE IF EXISTS SUBTASK;
DROP TABLE IF EXISTS TASK;
DROP TABLE IF EXISTS PROJECT;
DROP TABLE IF EXISTS APP_USER;
SET FOREIGN_KEY_CHECKS=1;

-- USER TABLE
CREATE TABLE IF NOT EXISTS APP_USER (
                                        USER_ID INT AUTO_INCREMENT PRIMARY KEY,
                                        USERNAME VARCHAR(255),
                                        USER_PASSWORD VARCHAR(255),
                                        ROLE VARCHAR(50)
);

-- PROJECT TABLE
CREATE TABLE IF NOT EXISTS PROJECT (
                                       PROJECT_ID INT AUTO_INCREMENT PRIMARY KEY,
                                       PROJECT_NAME VARCHAR(255),
                                       PROJECT_DESCRIPTION TEXT,
                                       OWNER_ID INT,
                                       FOREIGN KEY (OWNER_ID) REFERENCES APP_USER(USER_ID)
);

-- USER_PROJECT_ACCESS TABLE
CREATE TABLE IF NOT EXISTS USER_PROJECT_ACCESS (
                                                   USER_ID INT,
                                                   PROJECT_ID INT,
                                                   ACCESS_TYPE VARCHAR(20) DEFAULT 'READ_ONLY',
                                                   PRIMARY KEY (USER_ID, PROJECT_ID),
                                                   FOREIGN KEY (USER_ID) REFERENCES APP_USER(USER_ID),
                                                   FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT(PROJECT_ID)
);

-- TASK TABLE
CREATE TABLE IF NOT EXISTS TASK (
                                    TASK_ID INT AUTO_INCREMENT PRIMARY KEY,
                                    TASK_NAME VARCHAR(255),
                                    TASK_DESCRIPTION TEXT,
                                    TASK_ESTIMATED_HOURS INT,
                                    ACTUAL_HOURS INT,
                                    TASK_STATUS VARCHAR(50) DEFAULT 'TO_DO',
                                    PROJECT_ID INT,
                                    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT(PROJECT_ID)
);

-- SUBTASK TABLE
CREATE TABLE IF NOT EXISTS SUBTASK (
                                       SUBTASK_ID INT AUTO_INCREMENT PRIMARY KEY,
                                       SUBTASK_NAME VARCHAR(255),
                                       SUBTASK_DESCRIPTION TEXT,
                                       SUBTASK_ESTIMATED_HOURS INT,
                                       SUBTASK_STATUS VARCHAR(50),
                                       ASSIGNED_USER_ID INT DEFAULT NULL,
                                       TASK_ID INT,
                                       FOREIGN KEY (TASK_ID) REFERENCES TASK(TASK_ID),
                                       FOREIGN KEY (ASSIGNED_USER_ID) REFERENCES APP_USER(USER_ID)
);