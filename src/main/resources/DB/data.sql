-- Brugere
INSERT INTO user (user_id, username, user_password, role)
VALUES
    (1, 'Alice Manager', 'password123', 'DEVELOPER'),
    (2, 'Bob Developer', 'password456', 'MANAGER');

-- Projekter
INSERT INTO project (project_id, project_name, project_description, owner_id)
VALUES
    (1, 'Website Redesign', 'Revamp the landing page and dashboard', 1),
    (2, 'API Refactoring', 'Refactor the legacy REST API', 1),
    (3, 'Marketing Dashboard', 'Create dashboard for KPIs', 2);

-- Kobling: hvilke brugere har adgang til hvilke projekter
-- Kobling: hvilke brugere har adgang til hvilke projekter + adgangstype
INSERT INTO user_project_access (user_id, project_id, access_type)
VALUES
    (1, 1, 'EDIT'),         -- Alice ejer projekt 1 og kan redigere
    (2, 1, 'READ_ONLY'),    -- Bob må kun læse projekt 1
    (1, 2, 'EDIT'),         -- Alice har adgang og kan redigere projekt 2
    (2, 3, 'EDIT');         -- Bob ejer projekt 3 og kan redigere det


-- Tasks
INSERT INTO task (task_id, task_name, task_description, task_estimated_hours, project_id)
VALUES
    (1, 'Frontend Redesign', 'Update HTML/CSS structure', 15.0, 1),
    (2, 'Backend Cleanup', 'Refactor endpoints and DB calls', 20.0, 2),
    (3, 'Grimrian', 'Flotte fyr', 25.0, 3);

-- Subtasks
INSERT INTO subtask (subtask_id, subtask_name, subtask_description, subtask_estimated_hours, subtask_status, task_id)
VALUES
    (1, 'Navbar update', 'Redesign navbar with new branding', 4.0, 'To-do', 1),
    (2, 'Auth endpoint', 'Secure login API refactor', 5.0, 'To-do', 2),
    (3, 'Grimrian', 'Flotte fyr', 5.0, 'To-do', 3);

UPDATE task t
SET task_estimated_hours = (
    SELECT IFNULL(SUM(s.subtask_estimated_hours), 0)
    FROM subtask s
    WHERE s.task_id = t.task_id
);


