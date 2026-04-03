# finance-dashboard-backend

A robust and scalable backend system for managing financial records with role-based access control, secure authentication, and dashboard analytics.

This project demonstrates real-world backend engineering practices using Spring Boot, JWT authentication, and clean architecture principles.


🚀 Project Overview

This backend system is designed to power a finance dashboard where users interact with financial data based on their roles.

It supports:

Secure user authentication & authorization
Role-based access control (RBAC)
Full financial records management (CRUD + filtering)
Aggregated dashboard analytics
Clean and maintainable backend architecture

🛠 Tech Stack
Backend: Java (Spring Boot)
Security: Spring Security + JWT
Database: MySQL
ORM: JPA / Hibernate
Build Tool: Maven
API Testing: Postman

📂 Project Structure

com.finance.dashboard.system
│

├── config          # Security configurations

├── controller      # REST API endpoints

├── dto             # Data Transfer Objects

├── exception       # Global exception handling

├── model           # Entity classes

├── repository      # JPA repositories

├── security        # JWT + filters

├── service         # Business logic


🔐 Authentication & Authorization

JWT-based authentication
Secure login system
Role-based authorization using Spring Security

👤 Roles

Role	Permissions
Viewer	View dashboard data only
Analyst	View records + analytics
Admin	Full access (CRUD + user management)

📌 Core Features

1️⃣ User & Role Management
Create and manage users
Assign roles (Viewer, Analyst, Admin)
Enable/disable user accounts
Restrict access based on roles

2️⃣ Financial Records Management

Each financial record includes:

Amount
Type (Income / Expense)
Category
Date
Description
Supported Operations:
Create records
View records
Update records
Delete records
Filter by:
Date
Category
Type

3️⃣ Dashboard Summary APIs

Provides aggregated insights:

💵 Total Income
💸 Total Expenses
📊 Net Balance
📂 Category-wise totals
📅 Monthly/Weekly trends
🕒 Recent activity

4️⃣ Access Control Logic

Strict backend-level access control:

Viewer → Read-only access
Analyst → Read + analytics
Admin → Full control

Implemented via:

Spring Security configuration
JWT filter
Role-based authorization

5️⃣ Validation & Error Handling
Input validation using DTOs
Centralized exception handling (GlobalExceptionHandler)
Proper HTTP status codes
Meaningful error messages

6️⃣ Data Persistence
MySQL database
JPA/Hibernate ORM
Structured relational schema

⚙️ Setup Instructions

1️⃣ Clone Repository

git clone https://github.com/your-username/finance-dashboard-backend.git
cd finance-dashboard-backend

2️⃣ Configure Database

Update application.properties:
spring.application.name=finance-dashboard-system
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=mysupersecretkeymysupersecretkey12345
jwt.expiration=86400000


🧪 API Testing Guide (Postman)

This section provides test cases for all APIs in the Finance Dashboard Backend.

🔐 1. Authentication APIs
Register User

Endpoint

POST /auth/register

Request Body

{
"name": "Sandesh",
"email": "sandesh@gmail.com",
"password": "123456",
"role": "USER"
}

Expected Response

User registered successfully

 Login User

Endpoint

POST /auth/login

Request Body

{
"email": "sandesh@gmail.com",
"password": "123456"
}

Expected Response

{
"token": "JWT_TOKEN"
}

Save this token → required for all protected APIs.

🔑 Authorization Setup (IMPORTANT)

Add this header in all requests below:

Authorization: Bearer <JWT_TOKEN>

💰 2. Financial Records APIs


Create Record
POST /records

Body

{
"title": "Salary",
"amount": 50000,
"type": "INCOME",
"category": "Job",
"date": "2026-04-02"
}

Expected

{
"id": 1,
"title": "Salary",
...
}

Get All Records
GET /records

Expected

[
{
"id": 1,
"title": "Salary",
"amount": 50000
}
]

Get Record by ID
GET /records/1

Update Record
PUT /records/1

Body

{
"title": "Updated Salary",
"amount": 60000,
"type": "INCOME",
"category": "Job",
"date": "2026-04-02"
}

Delete Record
DELETE /records/1

Expected

Record deleted successfully
Filter by Category

GET /records/category/Job

Filter by Type
GET /records/type/INCOME

Filter by Date Range

GET /records/date?startDate=2026-04-01&endDate=2026-04-30

📊 3. Dashboard APIs

Get Summary
GET /dashboard/summary

Expected

{
"totalIncome": 100000,
"totalExpense": 20000,
"balance": 80000
}

Category Summary

GET /dashboard/category

Expected

{
"Food": 5000,
"Transport": 2000
}

Recent Transactions
GET /dashboard/recent

Monthly Trend
GET /dashboard/monthly

Negative Test Cases (Important for Interview 🔥)
Invalid Login
{
"email": "wrong@gmail.com",
"password": "wrong"
}

👨‍💻 Author

Sandesh Solanki
B.Tech Computer Science |
Backend Developer (Java + Spring Boot)