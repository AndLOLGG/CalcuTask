#🤝 Contributing to CalcuTask
Thank you for considering contributing to CalcuTask – a task and project management system built with Spring Boot, HTML/CSS, and MySQL on Azure.

We welcome all kinds of contributions: new features, bug fixes, documentation improvements, and ideas!

---

##🧰 Project Setup
Make sure you have:

- Java 21 (SDK)
- Maven
- IntelliJ IDEA (or similar IDE)
- Access to Azure Database for MySQL (or use H2 for local testing)
- Git & GitHub

---

## 🚧 How to Contribute
1. Fork the repository to your own GitHub

2. Clone the project locally:
git clone https://github.com/AndLOLGG/CalcuTask.git

3. Create a new branch for your feature/fix:
git checkout -b feature/your-feature-name

4. Make your changes in:
src/main/java/com/calcutask/ (for backend logic)
resources/templates/html/ or css/ (for frontend)

5. Run tests with H2:
mvn test

6. Commit your changes clearly:
git commit -m "Add: new task filtering feature"

7. Push your branch and open a Pull Request

---

## 🧪 Testing
We use H2 In-Memory Database for testing. All unit and integration tests should pass before submitting a pull request.

Run tests with:
mvn clean test

---

## ✅ Guidelines
- Follow existing code style and structure
- Use meaningful names and write clean, readable code
- Ensure all security standards are followed (e.g., passwords use bcrypt)
- Avoid hardcoding credentials or sensitive data
- Document your code where necessary

---

## 🔐 Passwords & Security
- Do not log or expose password hashes or tokens
- All passwords must be hashed using bcrypt

💡 Need Help?
If you're stuck or have questions, feel free to open an issue or start a discussion.

Thanks for your contribution! 🙌
