![CalcuTask Logo](src/main/resources/static/images/malm-logo.png)
# CalcuTask - Task Management System 

CalcuTask is a task management system built with IntelliJ IDEA 2024.3.5 (Ultimate Edition), Java SDK 21, and Spring Boot. It provides user role separation (Admin/Dev), supports projects with tasks and subtasks, and organizes work using categories. The frontend is built with HTML/CSS/Thymeleaf, and data is stored in an Azure Database for MySQL. The application is deployed on Azure using GitHub Actions.

---

## 🌐 Features

- Create and manage projects, tasks, and subtasks
- Assign tasks to multiple users
- Many-to-many relationship for task categorization
- Track task status (Todo, In Progress, etc.)
- Role-based access (Admin, Developer)
- Secure user authentication with bcrypt
- Responsive HTML/CSS UI
- Azure-hosted application with MySQL backend
- Logo design by Canva

---

## 🧱 Technologies

- **Frontend:** HTML / CSS / Thymeleaf
- **Backend:** Java 21, Spring Boot, JDBC
- **Database (Production):** Azure Database for MySQL
- **Database (Testing):** H2 In-Memory Database
- **Hosting:** Azure App Service
- **Build Tool:** Maven
- **Security:** Bcrypt password hashing
- **Code Quality:** Qodana
- **Modeling Tool:** Visual Paradigm
- **MALM Solutions-logo:** Canva

For a full technologies breakdown, see [TECHSTACK.md](https://github.com/AndLOLGG/CalcuTask/blob/main/TECHSTACK.md)

---

## 🔐 Password Security – Bcrypt

Passwords are hashed using the [BCrypt algorithm](https://en.wikipedia.org/wiki/Bcrypt):

1. Plaintext passwords are never stored
2. They are hashed on registration and stored in the `password_hash` field
3. User login compares hashes using `BCrypt.matches()`

This provides strong protection against brute-force and rainbow table attacks.

---

## 📐 Domain Model

![Domain Model](https://github.com/AndLOLGG/CalcuTask/blob/main/diagrams/CalcuTask%20Domain%20Model.png)

---

## 🗃️ Entity Relationship Diagram (ERD)

![ERD]([https://raw.githubusercontent.com/AndLOLGG/CalcuTask/main/diagrams/CalcuTask%20ERD3.png])

---

## 🚀 Deployment on Azure

- Deployed via [Azure Portal](https://portal.azure.com)
- Hosted with Azure App Service
- MySQL database provisioned via Azure Database for MySQL
- Visual Paradigm used for modeling
- CI/CD pipeline integrated via GitHub Actions

---

## 🗂️ Version Control & Repository
The project is managed using Git and hosted on GitHub:

Source code and diagrams are version-controlled

Branching model follows main for production and feature branches for development

Continuous deployment is connected via GitHub Actions (CI/CD)

Pull Requests (PRs) are reviewed before merging to main

GitHub Repository: https://github.com/AndLOLGG/CalcuTask

---

## 📬 Contributions
We welcome contributions to CalcuTask! If you’d like to report bugs, suggest features, or submit code:

Fork the repository

Create a branch (feature/your-feature)

Commit your changes with clear messages

Push the branch and open a Pull Request

Make sure your code:

Passes tests (run mvn test)

Follows existing style and naming conventions

Does not expose any sensitive credentials or config

For a full guide, see [CONTRIBUTING.md](https://github.com/AndLOLGG/CalcuTask/blob/main/CONTRIBUTING.md)

---

## 📁 Project Structure (Maven)

```plaintext
calcutask/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/calcutask/
│   │   │       ├── configuration/              # Configuration classes
│   │   │       │   └── WebConfig.java
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller/                 # Web controllers
│   │   │       │   └── ProjectController.java
│   │   │       │   └── SubtaskController.java
│   │   │       │   └── TaskController.java
│   │   │       │   └── UserController.java
│   │   │       ├── model/                      # DTOs and domain models
│   │   │       │   └── Project.java
│   │   │       │   └── Subtask.java
│   │   │       │   └── Task.java
│   │   │       │   └── User.java
│   │   │       │   └── UserProjectAccess.java
│   │   │       ├── repository/                 # Repositories
│   │   │       │   └── ProjectRepository.java
│   │   │       │   └── SubtaskRepository.java
│   │   │       │   └── TaskRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       │   └── UserProjectAccessRepository.java
│   │   │       ├── rowmapper/                  # RowMappers
│   │   │       │   └── ProjectRowMapper.java
│   │   │       │   └── SubtaskRowMapper.java
│   │   │       │   └── TaskRowMapper.java
│   │   │       │   └── UserRowMapper.java
│   │   │       │   └── UserProjectAccessRowMapper.java
│   │   │       ├── service/                    # Business logic
│   │   │       │   └── ProjectService.java
│   │   │       │   └── SubtaskService.java
│   │   │       │   └── TaskService.java
│   │   │       │   └── UserService.java
│   │   │       ├── util/                       # Utility classes
│   │   │       │   └── PasswordUtil.java
│   │   │       └── CalcuTaskApplication.java
│   │   └── resources/
│   │       ├── DB/                             # Database schema and initialization scripts
│   │       │   └── data.sql
│   │       │   └── schema.sql
│   │       ├── static/
│   │       │   └── css/                        # CSS styles
│   │       │   └── images/                     # Images
│   │       │       └── MALM-logo.png
│   │       ├── templates/                      # Thymeleaf HTML templates
│   │       │   └── add-project.html
│   │       │   └── edit-project.html
│   │       │   └── html.html
│   │       │   └── login.html
│   │       │   └── project.html
│   │       │   └── error/                      # Thymeleaf HTML templates for errors
│   │       │       └── 403.html
│   │       │       └── 404.html
│   │       │       └── 408.html
│   │       │       └── 500.html
│   │       └── application.properties          # Application properties
│   │       └── application-local.properties    # for localhost:8080
│   │       └── application-dev.properties      # for fully-deployed web-application / production
│
├── test/
│   └── java/
│       └── com/calcutasktest/                  # Unit and integration tests using H2
│
├── diagrams/
│   ├── CalcuTask ERD.png                       # 1st version
│   ├── CalcuTask ERD2.png                      # 2nd version
│   └── CalcuTask Domain Model.png
├── .qodana.yaml                                # Qodana configuration
└── pom.xml
