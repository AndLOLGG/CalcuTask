# 🧭 CalcuTask - Task Management System

CalcuTask is a task management system built with IntelliJ IDEA 2024.3.5 (Ultimate Edition) on Java SDK 21, Spring Boot, and HTML/CSS on the front-end. It supports task and subtask assignments, project categorization, and user role separation (Admin/Dev). It uses Azure Database for MySQL as the backend data storage.

## 🌐 Features

- Create and manage projects and tasks
- Assign tasks to multiple users
- Categorize tasks using many-to-many relationships
- Support for subtasks and task status tracking
- Role-based access (Admin and Developer)
- Secure password storage with hashing
- Responsive UI using HTML/CSS
- Persistent storage using Azure MySQL database

## 🧱 Technologies

- **Frontend:** HTML / CSS
- **Backend:** Java 21 / Azure
- **Database:** MySQL hosted with Azure SQL
- **Hosting:** Azure App Service
- **Diagrams:** Visual Paradigm
- **Tests:** H2 Database

## 📐 Domain Model

See the domain model UML class diagram below:

![Domain Model](https://github.com/AndLOLGG/CalcuTask/blob/main/diagrams/CalcuTask%20Domain%20Model.png)

## 📐 Entity Relationship Diagram

See the ER-Diagram below: 
![ER-Model](https://github.com/AndLOLGG/CalcuTask/blob/main/diagrams/CalcuTask%20ERD.png)

## 🚀 Deployment on Azure

1. Project deployed on [Azure Portal](https://portal.azure.com)
2. Hosted frontend via Azure App Service
3. Used Azure SQL Database for storage
4. Set up CI/CD using GitHub
5. Visual Paradigm (for modeling)

## 📁 Project Structure

```plaintext
CalcuTask
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/CalcuTask/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── CalcuTaskApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
│       └── java/
│            └── com/example/CalcuTaskTest/
├── diagrams/
│   ├── CalcuTask ERD.png
│   └── CalcuTask Domain Model.png
├── pom.xml
└── README.md
```
