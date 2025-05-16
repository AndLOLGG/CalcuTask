-- Brug korrekt database
CREATE DATABASE IF NOT EXISTS CalcuTasker;
USE CalcuTasker;

-- Brugertabel
CREATE TABLE IF NOT EXISTS users (
                                     user_id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255),
                                     user_password VARCHAR(255),
                                     role VARCHAR(50)
);

-- Projekttabel
CREATE TABLE IF NOT EXISTS projects (
                                        project_id INT AUTO_INCREMENT PRIMARY KEY,
                                        project_name VARCHAR(255),
                                        project_description TEXT
);

-- Tasks og subtasks
CREATE TABLE IF NOT EXISTS tasks (
                                     task_id INT AUTO_INCREMENT PRIMARY KEY,
                                     task_name VARCHAR(255),
                                     task_description TEXT,
                                     project_id INT,
                                     FOREIGN KEY (project_id) REFERENCES projects(project_id)
);