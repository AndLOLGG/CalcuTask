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

## 📁 Project Structure (Maven)

```plaintext
calcutask/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/calcutask/
│   │   │       ├── controller/         # Web controllers
│   │   │       ├── model/              # DTOs and domain models
│   │   │       ├── db/                 # JPA repositories and database entities
│   │   │       ├── service/            # Business logic
│   │   │       └── CalcuTaskApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── html/               # Thymeleaf HTML templates
│   │       │   └── css/                # CSS styles
│   │       └── application.properties
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
