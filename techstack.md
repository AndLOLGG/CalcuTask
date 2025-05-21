# 💻 CalcuTask – Technology Stack Overview
CalcuTask is a robust task management system developed by MALM Solutions, designed to streamline project and task organization for software teams. The system is built using modern, industry-standard tools and frameworks, emphasizing scalability, security, and ease of use.

## 🧱 Technology Stack
### 🖥️ Frontend
HTML / CSS / Thymeleaf
The user interface is built using standard web technologies for responsiveness and maintainability. Thymeleaf is used as the templating engine, enabling seamless integration with Spring Boot and dynamic rendering of server-side data.
- HTML5 – Markup language for structure
- CSS3 – Styling with responsive layout
- Thymeleaf 3.1.1.RELEASE – Java templating engine for rendering dynamic HTML

### 🔧 Backend
- Java 21 – Modern LTS version with enhanced performance and language features
- Spring Boot 3.2.4 – Framework for backend development and API setup
- JDBC (via Spring JDBC module) – Direct database interaction (version managed by Spring Boot)

### 🗄️ Database
- Production: Azure Database for MySQL – Version 8.0
  Cloud-hosted, scalable, and reliable relational database solution used in production.
- Testing: H2 In-Memory Database – Version 2.2.224
  Lightweight in-memory database used for unit and integration testing to ensure fast and isolated test runs.

### ☁️ Hosting & Deployment
- Azure App Service – Web app hosting (Linux Plan, Java 21 stack)
  The application is deployed on Microsoft's Azure cloud platform, ensuring high availability and scalability.
- GitHub Actions – CI/CD pipeline for build, test, and deployment (using Java 21 runner + Maven)
  Continuous integration and deployment pipelines are automated through GitHub Actions, allowing seamless code deployment and environment setup.

### 🛡️ Security
BCrypt (Spring Security) – Password hashing, version 6.2.1 (included via Spring Security 6.2.1)
All passwords are securely hashed using the BCrypt algorithm to protect user credentials and mitigate brute-force and rainbow table attacks.

### 🛠️ Development Tools
- Apache Maven 3.9.6 – Build automation and dependency management
  Project build and dependency management is handled via Apache Maven.
- IntelliJ IDEA 2024.3.5 Ultimate Edition – Development environment (IDE) used across the project
  The development environment used for coding, debugging, and managing the project lifecycle.

### 📊 Code Quality & Modeling
- Qodana 2024.1 – Static code analysis tool from JetBrains
  Static code analysis and quality checks are run automatically to maintain high code standards.
- Visual Paradigm 17.2 (Community Edition) – Modeling tool used for ERD and domain models
  Used for modeling the system architecture, including domain models and ER diagrams.

### 📌 Project Management
- GitHub Projects – Kanban-based project tracking and planning
  Tasks and feature tracking are managed through GitHub Projects for visibility and team coordination.
- GitHub Actions
  Used for version-controlled automated builds and deployments

### 🎨 Branding
Logo design: Canva – Versionless cloud-based design tool
The official MALM Solutions logo and UI design elements are created using Canva for a professional visual identity.
