-- Brugere
INSERT INTO user (user_id, username, user_password, role)
VALUES
    (1, 'Alice Developer', 'password123', 'DEVELOPER'),
    (2, 'Bob Manager', 'password456', 'MANAGER');

-- Projekter
INSERT INTO project (project_id, project_name, project_description, owner_id)
VALUES
    (1, 'Website Redesign', 'Revamp the landing page and dashboard', 1),
    (2, 'API Refactoring', 'Refactor the legacy REST API', 1),
    (3, 'Marketing Dashboard', 'Create dashboard for KPIs', 2);

-- Kobling: hvilke brugere har adgang til hvilke projekter
INSERT INTO user_project_access (user_id, project_id)
VALUES
    (1, 1),  -- Alice ejer projekt 1
    (2, 1),  -- Bob har også adgang til projekt 1
    (1, 2),  -- Alice ejer og har adgang til projekt 2
    (2, 3);  -- Bob ejer og har adgang til projekt 3

-- Tasks
INSERT INTO task (task_id, task_name, task_description, task_estimated_hours, project_id)
VALUES
    (1, 'Frontend Redesign', 'Update HTML/CSS structure', 15.0, 1),
    (2, 'Backend Cleanup', 'Refactor endpoints and DB calls', 20.0, 2);

-- Subtasks
INSERT INTO subtask (subtask_id, subtask_name, subtask_description, subtask_estimated_hours, subtask_status, task_id)
VALUES
    (1, 'Navbar update', 'Redesign navbar with new branding', 4.0, 'TO_DO', 1),
    (2, 'Auth endpoint', 'Secure login API refactor', 5.0, 'TO_DO', 2);
