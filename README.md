# 🧭 CalcuTask - Task Management System

CalcuTask is a task management system built with IntelliJ IDEA 2024.3.5 (Ultimate Edition), Java SDK 21, and Spring Boot. It provides user role separation (Admin/Dev), supports projects with tasks and subtasks, and organizes work using categories. The frontend is built with HTML/CSS, and data is stored in an Azure Database for MySQL.

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

- **Frontend:** HTML / CSS
- **Backend:** Java 21, Spring Boot
- **Database (Production):** Azure Database for MySQL
- **Database (Testing):** H2 In-Memory Database
- **Hosting:** Azure App Service
- **Build Tool:** Maven
- **Security:** Bcrypt password hashing
- **Code Quality:** Qodana
- **Modeling Tool:** Visual Paradigm
- **MALM Solutions-logo:** Canva

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

![ER-Model](https://github.com/AndLOLGG/CalcuTask/blob/main/diagrams/CalcuTask%20ERD.png)

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
│   │   │       ├── controller/         # Web controllers
│   │   │       ├── model/              # DTOs and domain models
│   │   │       ├── repository/         # repositories
│   │   │       ├── rowmapper/          # rowmappers
│   │   │       ├── service/            # Business logic
│   │   │       └── CalcuTaskApplication.java
│   │   └── resources/
│   │           │── DB/                 # JPA repositories and database entities
│   │           ├── static/
│   │           │   └── css/                # CSS styles
│   │           ├── templates/
│   │           │   ├── html/               # Thymeleaf HTML templates
│   │           └── application.properties
│
├── test/
│   └── java/
│       └── com/calcutasktest/          # Unit and integration tests using H2
│
├── diagrams/
│   ├── CalcuTask ERD.png
│   └── CalcuTask Domain Model.png
├── .qodana.yaml                        # Qodana configuration
└── pom.xml
