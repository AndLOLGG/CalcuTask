-- Skift til rigtig database
CREATE DATABASE IF NOT EXISTS CalcuTasker;
USE CalcuTasker;

-- Deaktiver FK tjek for at kunne droppe tabeller i rigtig rækkefølge
SET FOREIGN_KEY_CHECKS=0;
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

-- Tasktabel
CREATE TABLE IF NOT EXISTS task (
                                    task_id INT AUTO_INCREMENT PRIMARY KEY,
                                    task_name VARCHAR(255),
                                    task_description TEXT,
                                    task_estimated_hours DECIMAL(4, 2),
                                    project_id INT,
                                    FOREIGN KEY (project_id) REFERENCES project(project_id)
);

-- Subtasktabel
CREATE TABLE IF NOT EXISTS subtask (
                                       subtask_id INT AUTO_INCREMENT PRIMARY KEY,
                                       subtask_name VARCHAR(255),
                                       subtask_description TEXT,
                                       subtask_estimated_hours DECIMAL(4, 2),
                                       subtask_status VARCHAR(50),
                                       task_id INT,
                                       FOREIGN KEY (task_id) REFERENCES task(task_id)
);
