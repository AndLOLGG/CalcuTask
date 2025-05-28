-- src/test/resources/data-h2.sql

INSERT INTO "SCHEMA_H2"."APP_USER" (USERNAME, USER_PASSWORD, ROLE)
VALUES
    ('Alice Manager', 'password123', 'DEVELOPER'),
    ('Bob Developer', 'password456', 'MANAGER'),
    ('Admin', 'admin', 'ADMIN');

INSERT INTO "SCHEMA_H2"."PROJECT" (PROJECT_NAME, PROJECT_DESCRIPTION, OWNER_ID)
VALUES
    ('Website Redesign', 'Revamp the landing page and dashboard', 1),
    ('API Refactoring', 'Refactor the legacy REST API', 1),
    ('Marketing Dashboard', 'Create dashboard for KPIs', 2);

INSERT INTO "SCHEMA_H2"."TASK" (TASK_NAME, TASK_DESCRIPTION, TASK_ESTIMATED_HOURS, TASK_STATUS, PROJECT_ID)
VALUES
    ('Frontend Redesign', 'Update HTML/CSS structure', 15, 'TO_DO', 1),
    ('Backend Cleanup', 'Refactor endpoints and DB calls', 20, 'TO_DO', 2),
    ('Grimrian', 'Flotte fyr', 25, 'TO_DO', 3);

INSERT INTO "SCHEMA_H2"."SUBTASK" (SUBTASK_NAME, SUBTASK_DESCRIPTION, SUBTASK_ESTIMATED_HOURS, SUBTASK_STATUS, TASK_ID)
VALUES
    ('Navbar update', 'Redesign navbar with new branding', 4, 'TO_DO', 1),
    ('Auth endpoint', 'Secure login API refactor', 5, 'TO_DO', 2),
    ('Grimrian', 'Flotte fyr', 5, 'TO_DO', 3);