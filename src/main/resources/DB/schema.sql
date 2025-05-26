CREATE DATABASE IF NOT EXISTS CalcuTasker;
USE CalcuTasker;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS user_project_access;
DROP TABLE IF EXISTS subtask;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS user;
SET FOREIGN_KEY_CHECKS=1;

-- Brugertabel
CREATE TABLE IF NOT EXISTS user (
                                    user_id INT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255),
                                    user_password VARCHAR(255),
                                    role VARCHAR(50)
);

-- Projekttabel
CREATE TABLE IF NOT EXISTS project (
                                       project_id INT AUTO_INCREMENT PRIMARY KEY,
                                       project_name VARCHAR(255),
                                       project_description TEXT,
                                       owner_id INT,
                                       FOREIGN KEY (owner_id) REFERENCES user(user_id)
);

-- Mange-til-mange koblingstabel mellem user og project
CREATE TABLE IF NOT EXISTS user_project_access (
                                                   user_id INT,
                                                   project_id INT,
                                                   access_type ENUM('READ_ONLY', 'EDIT') NOT NULL DEFAULT 'READ_ONLY',
                                                   PRIMARY KEY (user_id, project_id),
                                                   FOREIGN KEY (user_id) REFERENCES user(user_id),
                                                   FOREIGN KEY (project_id) REFERENCES project(project_id)
);

-- Tasktabel med task_status til statusvisning
CREATE TABLE IF NOT EXISTS task (
                                    task_id INT AUTO_INCREMENT PRIMARY KEY,
                                    task_name VARCHAR(255),
                                    task_description TEXT,
                                    task_estimated_hours DECIMAL(4, 2),
                                    task_status VARCHAR(50) DEFAULT 'TO_DO',
                                    project_id INT,
                                    FOREIGN KEY (project_id) REFERENCES project(project_id)
);

-- Subtasktabel med assigned_user_id til påtagelse
CREATE TABLE IF NOT EXISTS subtask (
                                       subtask_id INT AUTO_INCREMENT PRIMARY KEY,
                                       subtask_name VARCHAR(255),
                                       subtask_description TEXT,
                                       subtask_estimated_hours INT,
                                       subtask_status VARCHAR(50),
                                       assigned_user_id INT DEFAULT NULL,
                                       task_id INT,
                                       FOREIGN KEY (task_id) REFERENCES task(task_id),
                                       FOREIGN KEY (assigned_user_id) REFERENCES user(user_id)
);
