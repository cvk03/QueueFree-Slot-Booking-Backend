# 🧺 QueueFree Laundry – Backend System

A scalable, high-concurrency backend system for managing laundry slot bookings. Built with **Kotlin** and **Spring Boot**, this system ensures reliable scheduling and real-time availability in shared living environments.

---

## 🧠 Problem Statement
In high-density environments like hostels and apartments, laundry management is often chaotic. Common pain points include:
* **Unpredictable Availability:** Users don't know if a machine is free until they physically check.
* **The "Wait-Time" Trap:** Long queues and wasted time.
* **Double Bookings:** Scheduling conflicts caused by manual or outdated systems.

**QueueFree** eliminates these issues by providing a centralized, concurrency-safe booking engine.

---

## ⚙️ Tech Stack
* **Language:** [Kotlin](https://kotlinlang.org/)
* **Framework:** [Spring Boot 3.x](https://spring.io/projects/spring-boot)
* **Database:** [Google Firestore](https://firebase.google.com/docs/firestore) (NoSQL)
* **Build Tool:** Gradle (KTS)
* **Architecture:** Layered (Controller -> Service -> Repository)

---

## 🚀 Key Features

* ✅ **Smart Slot Booking:** Real-time availability checks that prevent overlapping schedules.
* ✅ **Concurrency Control:** Implements logic to handle simultaneous booking requests safely, ensuring a single slot cannot be claimed by two users at the exact same millisecond.
* ✅ **Robust Validation:** Centralized exception handling to ensure the API remains resilient and provides clear feedback for invalid requests.
* ✅ **Clean Architecture:** Strict separation of concerns using DTOs and service layers for maximum maintainability.

---

## 📚 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/users/register` | Register a new user |
| `GET` | `/available-slots` | Fetch all currently available time slots |
| `POST` | `/bookings` | Create a new laundry booking |
| `GET` | `/allbookings/upcoming` | Retrieve upcoming booking details |

> [!NOTE]  
> OpenAPI documentation is currently in progress.

---

## ⚡ Getting Started

### Prerequisites
* **Java 17+**
* **Gradle**

### Run Locally
```bash
# Clone the repository
git clone [https://github.com/your-username/queuefree-laundry-backend](https://github.com/your-username/queuefree-laundry-backend)

# Navigate to directory
cd queuefree-laundry-backend

# Build and Run
./gradlew bootRun

```
The server will start at: `http://localhost:8080`

---

## 🔐 Firestore Configuration

This project requires **Firebase Firestore**. Follow these steps to connect your instance:

1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Navigate to **Project Settings > Service Accounts**.
3. Click **Generate New Private Key** and download the JSON file.
4. Place the file at: `src/main/resources/firebase-service-account.json`.

**Set your environment variable:**

```bash
export GOOGLE_APPLICATION_CREDENTIALS="src/main/resources/firebase-service-account.json"

```
## 📁 Project Structure

```text
src/main/kotlin/com/queuefree/
│
├── config/       # Firebase & App configurations
├── controller/   # REST Controllers (API Endpoints)
├── dto/          # Data Transfer Objects
├── exception/    # Global Exception Handler & Custom Errors
├── model/        # Firestore Entities/Data Models
├── repository/   # Data Access Layer (Firestore)
└── service/      # Business Logic & Concurrency Handling

```
## 🧪 Testing

* **Manual Testing:** Conducted via Postman to verify all HTTP status codes and response structures.
* **Concurrency Testing:** Validated via script-based simultaneous requests to ensure zero double-bookings.

---

## 💡 Key Learnings

* **Scalable REST APIs:** Architecting endpoints with Kotlin's expressive syntax.
* **Concurrency Management:** Handling race conditions and data consistency in a NoSQL environment.
* **Clean Architecture:** Implementing a layered structure to separate business rules from external dependencies.
