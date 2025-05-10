# Family Payment System

This project is a Spring Boot application designed for managing family-based financial transactions between parents and students. It enables parents to fund their children's accounts with a dynamic payment adjustment, while supporting shared custody and enforcing role-based access control.

---

## 🔧 Features

- 👨‍👩‍👧‍👦 **Multi-table relationships** between parents and students.
- 💳 **Dynamic payment processing** with a configurable rate.
- 🔐 **Role-based access control** with Spring Security.
- 📄 **Transaction logging** using JPA and MySQL.
- ⚙️ Built with **Spring Boot, JPA, Lombok, and MySQL**.

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL (running on port `3306` with schema `familypayment`)
- Git

---

### 🔨 Build and Run

```bash
# Clone the project
git clone https://github.com/hayzon1/FamilyPaymentSystem.git
cd FamilyPaymentSystem

# Configure application.properties if needed
# Run the application
mvn spring-boot:run
```

Or build the JAR:

```bash
mvn clean install
java -jar target/Family-0.0.1-SNAPSHOT.jar
```

---

### 🔐 Testing Authentication

- **Username:** `admin`
- **Password:** `password`
- Role: `ADMIN`

Use Basic Auth headers for secured endpoints like `/api/payments`.

---

### 🔁 Test Payment API

POST `/api/payments`

```http
POST http://localhost:8080/api/payments
Authorization: Basic <base64(admin:password)>
Content-Type: application/json

{
  "parentId": 1,
  "studentId": 1,
  "paymentAmount": 100
}
```

---

## 📐 Design Decisions

### 🧾 Entity Relationships

- **Parent ↔ Student**: `@ManyToMany` relationship
- **Payment** logs each transaction and is linked by parent and student IDs.

### 🔐 Security

- Uses `InMemoryUserDetailsManager` for testing purposes.
- Secures endpoints with role `ADMIN`.

---

## ➗ Business Logic

### Payment Adjustment

Each payment adds **5%** to simulate a processing fee or interest.

```java
double adjustedAmount = paymentAmount * (1 + 0.05);
```

- If the student has **two parents**, the amount is split **evenly** between them.
- Otherwise, the full amount is deducted from the initiating parent’s balance.
- The student’s balance is **increased** by the adjusted amount.

---

## 📂 Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- Maven

---

## 🤝 Contribution

Feel free to fork the repo and submit pull requests!

---

## 📃 License

This project is open-source and available under the MIT License.