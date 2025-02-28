# Offshore-Ground-Sampling
The system will store sampling locations and measured parameters (unit weight, water content and shear strength), and will allow the frontend to view, add, and update these records. The frontend will interact with the backend via RESTful API endpoints.

# **Project: Eclipse RCP Frontend & Spring Boot Backend**

## **Table of Contents**
1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Backend Setup](#backend-setup)
4. [Frontend Setup (Eclipse RCP)](#frontend-setup-eclipse-rcp)
5. [Swagger API Documentation](#swagger-api-documentation)
6. [Unit Testing](#unit-testing)
7. [Graph Visualization](#graph-visualization)
8. [Additional Features](#additional-features)

---

## **Project Overview**
This project consists of a **Spring Boot backend** and an **Eclipse RCP frontend** for managing **ground sample data**.
The backend provides **REST APIs** for CRUD operations, statistical analysis, and threshold validation. The frontend is built using **Eclipse RCP (SWT & JFace)** to provide a GUI for interacting with the data.

---

## **Technologies Used**
### **Backend (Spring Boot 3.x)**
- Java 17
- Spring Boot
- Hibernate & JPA
- H2 Database (In-memory database)
- Swagger (Springdoc OpenAPI)
- JUnit 5 & Mockito (Unit Testing)

### **Frontend (Eclipse RCP)**
- Java 17
- Eclipse RCP (SWT, Dialog & JFace)
- REST API Integration using `HttpURLConnection`

---

## **Backend Setup**
### **1️⃣ Clone the Repository**
```bash
git clone <repository-url>
cd Offshore-Ground-Sampling-BackEnd
```

### **2️⃣ Run the Backend**
```bash
mvn spring-boot:run
```
The server runs at: **http://localhost:8080**

### **3️⃣ Swagger API Documentation**
Once the server is running, access Swagger UI at:
👉 **http://localhost:8080/swagger-ui.html**

![Screenshot 2025-02-28 131328](https://github.com/user-attachments/assets/7360b7e0-24a0-4dec-9d97-eb7024abcef1)

---


## **Frontend Setup (Eclipse RCP)**
### **1️⃣ Clone the Repository**
```bash
git clone <repository-url>
cd Offshore-Ground-Sampling-FrontEnd
```
### **2️⃣ Import the Eclipse RCP Project**
1. Open **Eclipse IDE for RCP and RAP Developers**
2. Go to **File → Import → Existing Projects into Workspace**
3. Select the **frontend project folder** and import it.

### **3️⃣ Run the Eclipse RCP Application**
1. Open `View.java`
2. Right-click → **Run As → Eclipse Application**

**OR Install the Eclipse RCP Plugins**
![Screenshot 2025-02-28 142355](https://github.com/user-attachments/assets/7233fb12-0139-4b9e-95c8-f7e3b6c4e689)

### **4️⃣ Features of the Frontend**
✅ Displays a table with **Sample ID, Location, Date, Unit Weight, Water Content, Shear Strength**
✅ **Add/Edit/Delete samples** via API requests
✅ **Dropdown selection for locations** (fetched from the backend)
✅ **Statistics report** (fetched from the backend)
✅ **Graph Visualization** (Yet to do)

---
![Add Sample](https://github.com/user-attachments/assets/a42729be-1a4b-49a6-b0f9-1fbe27e5d349)
![Edit Sample](https://github.com/user-attachments/assets/0eab89f2-2395-4873-99f6-f83773c4752e)
![Invalid Input](https://github.com/user-attachments/assets/874e7261-0c39-49ce-97ac-3d3ff12d05eb)

## **Unit Testing**
To run **unit tests** for the backend:
```bash
mvn test
```
Tests include:
✅ **SampleServiceTest** (CRUD Operations, Threshold Validation)
✅ **SampleControllerTest** (MockMVC API Tests)
✅ **StatisticsServiceTest** (Average Water Content, Threshold Exceedance Detection)

---

## **Additional Features**
- **Threshold Validation:** Prevents invalid sample data from being saved.
- **Statistics API:** Calculates **average water content** and **identifies threshold exceedances**.
- **Swagger API Documentation:** Provides a web interface for testing APIs.

---

## **License**
This project is **open-source** and licensed under the **MIT License**.

