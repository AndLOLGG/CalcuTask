-- Brugere
INSERT INTO user (username, user_password, role)
VALUES
    ('Alice Manager', 'password123', 'DEVELOPER'),
    ('Bob Developer', 'password456', 'MANAGER'),
    ('Admin', 'admin', 'ADMIN');

-- Projekter
INSERT INTO project (project_name, project_description, owner_id)
VALUES
    ('Website Redesign', 'Revamp the landing page and dashboard', 1),
    ('API Refactoring', 'Refactor the legacy REST API', 1),
    ('Marketing Dashboard', 'Create dashboard for KPIs', 2);

-- Adgang til projekter
INSERT INTO user_project_access (user_id, project_id, access_type)
VALUES
    (1, 1, 'EDIT'),        -- Alice ejer projekt 1
    (2, 1, 'READ_ONLY'),   -- Bob kan kun læse projekt 1
    (1, 2, 'EDIT'),        -- Alice redigerer projekt 2
    (2, 3, 'EDIT');        -- Bob ejer projekt 3

-- Tasks (inkl. actual_hours som INT)
INSERT INTO task (task_name, task_description, task_estimated_hours, actual_hours, project_id)
VALUES
    ('Frontend Redesign', 'Update HTML/CSS structure', 15, NULL, 1),
    ('Backend Cleanup', 'Refactor endpoints and DB calls', 20, NULL, 2),
    ('Grimrian', 'Flotte fyr', 25, NULL, 3);

-- Subtasks
INSERT INTO subtask (subtask_name, subtask_description, subtask_estimated_hours, subtask_status, task_id)
VALUES
    ('Navbar update', 'Redesign navbar with new branding', 4, 'TO_DO', 1),
    ('Auth endpoint', 'Secure login API refactor', 5, 'TO_DO', 2),
    ('Grimrian', 'Flotte fyr', 5, 'TO_DO', 3);

-- Opdater estimeret tid på tasks baseret på subtasks (INT-venlig version)
UPDATE task t
SET task_estimated_hours = (
    SELECT IFNULL(SUM(s.subtask_estimated_hours), 0)
    FROM subtask s
    WHERE s.task_id = t.task_id
);
