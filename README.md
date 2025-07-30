👩‍💻 User API
A simple and secure User Management API built with Spring Boot and JWT Authentication.
This API allows user registration, login, and protected access to user data using JSON Web Tokens.

#🚀 Features
✅ Register New Users with unique email
✅ Login with email & password
✅ Generate & Validate JWT Tokens
✅ Get All Users (Secured)
✅ Delete User by ID (Secured)

# Layer      Technology Used              
| ---------- | ---------------------------- |
| Language   | Java 8                       |
| Framework  | Spring Boot, Spring Security |
| Database   | MySQL                        |
| ORM Tool   | Hibernate (JPA)              |
| Build Tool | Maven                        |
| Auth       | JWT Token                    |

#📌 API Endpoints
🔐 Authentication
Method |	Endpoint  |	 Description	Auth Required
POST	 | /register	|  Register new user	
POST	 | /login     |	 Login and get JWT token

#👩 User Management
| Method | Endpoint  | Description       | Auth Required  |
| ------ | ----------- | ----------------- | ------------   |
| GET    | /users      | Fetch all users   | ✅ Yes         |
| DELETE | /users/{id} | Delete user by ID | ✅ Yes         |

#🔑 JWT Token Flow
User registers via /register
User logs in via /login → gets JWT token

#⚡ Setup Instructions
1️⃣ Clone Repository
git clone https://github.com/AnjaliBodke02/userapi.git

#2️⃣ Configure Database application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
jwt.secret=yourSecretKey

#3️⃣Build & Run
mvn clean install
mvn spring-boot:run

#4️⃣ Test APIs in Postman
Import the collection (provided in /postman_collection)
Use JWT token for secured requests

