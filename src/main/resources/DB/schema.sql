CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255),
                                     password VARCHAR(255),
                                     role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS projects (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255),
                                        description TEXT,
                                        owner_id INT,
                                        FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS tasks (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255),
                                     description TEXT,
                                     project_id INT,
                                     parent_task_id INT,
                                     FOREIGN KEY (project_id) REFERENCES projects(id),
                                     FOREIGN KEY (parent_task_id) REFERENCES tasks(id)
);
