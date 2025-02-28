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
- Eclipse RCP (SWT & JFace)
- REST API Integration using `HttpURLConnection`
- Nebula XYGraph (Graph Visualization)

---

## **Backend Setup**
### **1️⃣ Clone the Repository**
```bash
git clone <repository-url>
cd backend
```

### **2️⃣ Configure Database & Threshold Values**
Modify `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

threshold.waterContent.min=5.0
threshold.waterContent.max=150.0
threshold.unitWeight.min=12.0
threshold.unitWeight.max=26.0
threshold.shearStrength.min=2.0
threshold.shearStrength.max=1000.0
```

### **3️⃣ Run the Backend**
```bash
mvn spring-boot:run
```
The server runs at: **http://localhost:8080**

### **4️⃣ Swagger API Documentation**
Once the server is running, access Swagger UI at:
👉 **http://localhost:8080/swagger-ui.html**

---

## **Frontend Setup (Eclipse RCP)**
### **1️⃣ Import the Eclipse RCP Project**
1. Open **Eclipse IDE for RCP and RAP Developers**
2. Go to **File → Import → Existing Projects into Workspace**
3. Select the **frontend project folder** and import it.

### **2️⃣ Run the Eclipse RCP Application**
1. Open `MainView.java`
2. Right-click → **Run As → Eclipse Application**

### **3️⃣ Features of the Frontend**
✅ Displays a table with **Sample ID, Location, Date, Unit Weight, Water Content, Shear Strength**
✅ **Add/Edit/Delete samples** via API requests
✅ **Dropdown selection for locations** (fetched from the backend)
✅ **Graph Visualization** (Unit Weight vs Water Content using Nebula XYGraph)

---

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

## **Graph Visualization**
The frontend includes a **graph visualization** feature using Nebula XYGraph.
- **X-axis:** Water Content (%)
- **Y-axis:** Unit Weight (kN/m³)
- **Graph is displayed when the user clicks 'Show Graph'.**

---

## **Additional Features**
- **Threshold Validation:** Prevents invalid sample data from being saved.
- **Statistics API:** Calculates **average water content** and **identifies threshold exceedances**.
- **Swagger API Documentation:** Provides a web interface for testing APIs.

---

## **🚀 Future Enhancements**
- Implement **pagination and sorting** for the sample list.
- Add **JWT authentication** for API security.
- Improve UI with **Eclipse Nebula widgets**.

---

## **Contributing**
1. Fork the repository
2. Create a feature branch (`git checkout -b feature-xyz`)
3. Commit changes (`git commit -m 'Added new feature'`)
4. Push to GitHub (`git push origin feature-xyz`)
5. Submit a Pull Request

---

## **License**
This project is **open-source** and licensed under the **MIT License**.

