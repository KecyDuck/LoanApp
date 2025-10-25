# Loan Management Mini Project

## Overview
This project is a **Spring Boot mini-application** demonstrating core backend concepts:
- **Spring IoC (Inversion of Control)** for dependency management  
- **Java Stream API** for data transformation and mapping  
- **Native SQL Queries** for flexible database access  

It simulates a small **loan management system**, where users can:
- Register their data  
- Submit loan requests  
- Admins can approve or reject loans  
- View loan history per user  

---

## Tech Stack
- **Java 17+**  
- **Spring Boot 3.x**  
- **Spring Data JPA**  
- **H2/PostgreSQL**  
- **Lombok**  
- **Jakarta Persistence API**

---

## How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/KecyDuck/LoanApp.git
   cd LoanApp
   ```

2. **Build and run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the API**
   - Base URL: `http://localhost:8080/api`
   - Example endpoints:
     - `GET /api/users` → list users (paginated)
     - `POST /api/users` → create a user  
     - `POST /api/loans/request` → submit loan request  
     - `GET /api/loans` → list all loans (paginated)  
     - `POST /api/loans/{loanId}/approval` → approve or reject  
     - `GET /api/loans/history/{userId}` → view approved loan history  

---

## 🧩 Project Structure

```
src/main/java/com/example/loanapp
│
├── model/             # Entity classes (UserData, LoanData, WFApprovalData)
├── repository/        # Repositories using JPA and native SQL
├── service/           # Business logic with transactional methods
├── controller/        # REST API endpoints
└── dto/               # Data Transfer Objects for responses
```

---

## Concept Highlights

### Spring IoC (Inversion of Control)
Used throughout the project via **`@Service`**, **`@Repository`**, and **`@Autowired`**.

```java
@Service
public class LoanService {
    @Autowired
    LoanDataRepository loanDataRepository;
}
```

Spring automatically injects dependencies (repositories/services), so you don’t manually instantiate objects.

---

### Java Stream API
Used for converting raw SQL query results (`List<Object[]>`) into DTOs.

```java
return userRawList.stream()
    .map(row -> new UserDataDTO(
        (String) row[0],
        (String) row[1],
        row[2] != null ? ((java.sql.Date) row[2]).toLocalDate() : null,
        (String) row[3],
        (String) row[4],
        (String) row[5]
    ))
    .toList();
```

---

### Native SQL Queries
Applied in repositories to allow **flexible joins** and **manual control** over the query.

```java
@Query(value = """
    SELECT l.id, u.user_name, l.amount, l.purpose, l.guarantee, w.approver_name, w.status
    FROM loan_data l
    JOIN user_data u ON u.id = l.user_id
    LEFT JOIN wf_approval_data w ON w.loan_id = l.id
    """, nativeQuery = true)
Page<Object[]> findAllLoanWithStatus(Pageable pageable);
```

---

## 📋 Example Flow

1. **User submits a loan**
   ```
   POST /api/loans/request
   {
     "userId": "1234",
     "amount": 5000,
     "purpose": "Buy Laptop",
     "guarantee": "Motorbike"
   }
   ```
   → Creates a new loan and workflow approval record with status `NEW`.

2. **Admin approves loan**
   ```
   POST /api/loans/{loanId}/approval
   {
     "action": "APPROVED",
     "comment": "All documents verified"
   }
   ```

3. **View loan list**
   ```
   GET /api/loans?page=0&size=5
   ```

4. **View user loan history**
   ```
   GET /api/loans/history/{userId}
   ```

---

## Author
**Jefry W**  
Gmail : jefrywilbert@gmail.com  
GitHub: [@KecyDuck](https://github.com/KecyDuck)
