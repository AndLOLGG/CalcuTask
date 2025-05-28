-- BRUGERE
INSERT INTO APP_USER (USERNAME, USER_PASSWORD, ROLE)
VALUES
    ('Alice Manager', 'password123', 'DEVELOPER'),
    ('Bob Developer', 'password456', 'MANAGER'),
    ('Admin', 'admin', 'ADMIN');

-- PROJEKTER
INSERT INTO PROJECT (PROJECT_NAME, PROJECT_DESCRIPTION, OWNER_ID)
VALUES
    ('Website Redesign', 'Revamp the landing page and dashboard', 1),
    ('API Refactoring', 'Refactor the legacy REST API', 1),
    ('Marketing Dashboard', 'Create dashboard for KPIs', 2);

-- ADGANG TIL PROJEKTER
INSERT INTO USER_PROJECT_ACCESS (USER_ID, PROJECT_ID, ACCESS_TYPE)
VALUES
    (1, 1, 'EDIT'),        -- Alice ejer projekt 1
    (2, 1, 'READ_ONLY'),   -- Bob kan kun læse projekt 1
    (1, 2, 'EDIT'),        -- Alice redigerer projekt 2
    (2, 3, 'EDIT');        -- Bob ejer projekt 3

-- TASKS
INSERT INTO TASK (TASK_NAME, TASK_DESCRIPTION, TASK_ESTIMATED_HOURS, ACTUAL_HOURS, PROJECT_ID)
VALUES
    ('Frontend Redesign', 'Update HTML/CSS structure', 15, NULL, 1),
    ('Backend Cleanup', 'Refactor endpoints and DB calls', 20, NULL, 2),
    ('Grimrian', 'Flotte fyr', 25, NULL, 3);

-- SUBTASKS
INSERT INTO SUBTASK (SUBTASK_NAME, SUBTASK_DESCRIPTION, SUBTASK_ESTIMATED_HOURS, SUBTASK_STATUS, TASK_ID)
VALUES
    ('Navbar update', 'Redesign navbar with new branding', 4, 'TO_DO', 1),
    ('Auth endpoint', 'Secure login API refactor', 5, 'TO_DO', 2),
    ('Grimrian', 'Flotte fyr', 5, 'TO_DO', 3);

-- OPDATER TASKS ESTIMERET TID BASERET PÅ SUBTASKS
UPDATE TASK T
SET TASK_ESTIMATED_HOURS = (
    SELECT COALESCE(SUM(S.SUBTASK_ESTIMATED_HOURS), 0)
    FROM SUBTASK S
    WHERE S.TASK_ID = T.TASK_ID
);
