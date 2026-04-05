#finance-dashboard-backend

🚀 Features

•	🔐 Secure Authentication using JWT 

•	👤 Role-Based Access Control (Admin / User) 

•	💰 Financial Record Management (CRUD) 

•	📅 Filter records by category and date range 

•	📊 Structured backend for dashboard integration 

•	⚡ Clean architecture (Controller → Service → Repository) 

•	🛡️ Spring Security integration 

________________________________________
🛠️ Tech Stack

•	Backend: Java, Spring Boot 

•	Security: Spring Security + JWT 

•	Database: MySQL 

•	ORM: Hibernate / JPA 

•	Build Tool: Maven 

•	Testing Tool: Postman 

________________________________________
📂 Project Structure

finance-dashboard-system

│── controller       # REST Controllers (Auth, Financial Records)

│── service          # Business Logic Layer
  
│── repository       # Database Access Layer

│── model            # Entity Classes

│── dto              # Data Transfer Objects

│── security         # JWT + Security Configuration

│── config           # Application Configuration
________________________________________

🔐 Authentication Flow

1.	User registers using /auth/register 

2.	User logs in via /auth/login 

3.	Server returns a JWT token 

4.	Token is used in headers for secured APIs 
Authorization: Bearer <JWT_TOKEN>
________________________________________

🌐 API Endpoints

🔑 Auth APIs

Method	Endpoint	Description

POST	/auth/register	Register new user

POST	/auth/login	Login & get JWT

________________________________________
💰 Financial Record APIs

Method	Endpoint	Description

POST	/records	Create record

GET	/records	Get all records

GET	/records/category/{category}	Filter by category

GET	/records/date	Filter by date range

DELETE	/records/{id}	Delete record

________________________________________
📦 Sample Request & Response

✅ Register User

POST /auth/register
{
  "username": "sandesh",
  "email": "sandesh@gmail.com",
  "password": "123456",
  "role": "USER"
}

________________________________________
✅ Login Response
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
________________________________________
✅ Create Financial Record

POST /records
{
  "amount": 5000,
  "category": "Food",
  "date": "2026-04-03",
  "description": "Lunch"
}

________________________________________
⚙️ Setup Instructions

1️⃣ Clone Repository
git clone https://github.com/your-username/finance-dashboard-system.git
cd finance-dashboard-system
________________________________________
2️⃣ Configure Database

spring.application.name=finance-dashboard-system

spring.datasource.url=jdbc:mysql://localhost:3306/finance_db

spring.datasource.username=root

spring.datasource.password=


spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true


jwt.secret=mysupersecretkeymysupersecretkey12345

jwt.expiration=86400000

________________________________________
3️⃣ Run Application

mvn spring-boot:run
App will run at:
http://localhost:8080

________________________________________
4️⃣ Test APIs

Use:
•	Postman 
•	Swagger (optional if added) 

________________________________________
🧪 Test Cases

🔐 Authentication

•	Register with valid data ✅ 

•	Register with duplicate email ❌ 

•	Login with correct credentials ✅ 

•	Login with wrong password ❌ 

________________________________________
💰 Financial Records

•	Create record ✅ 

•	Fetch all records ✅ 

•	Filter by category ✅ 

•	Filter by date range ✅ 

•	Delete record ✅ 

________________________________________
🔒 Security

•	Access API without token ❌ 

•	Access with invalid token ❌ 

•	Access with valid token ✅ 

________________________________________
⚠️ Known Improvements

•	Add DTO validation (@Valid, @NotNull) 

•	Implement global exception handling 

•	Add Swagger documentation 

•	Add logging (SLF4J) 

•	Improve response structure using ResponseEntity 

________________________________________
📈 Future Enhancements

•	📊 Dashboard analytics (monthly summary, charts) 

•	📱 Frontend integration (Angular / React) 

•	☁️ Cloud deployment (AWS / Railway) 

•	📑 Export reports (PDF/Excel) 

________________________________________
👨‍💻 Author

Sandesh Solanki
B.Tech CSE | Java Backend Developer

