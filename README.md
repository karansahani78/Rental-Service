
# **🏠 Rental-Service**

**Rental-Service** is a full-stack property rental platform that allows users to browse, list, and book rental properties. Built using **Java Spring Boot** for the backend and **React.js** for the frontend, this project demonstrates a scalable architecture with RESTful APIs, MySQL integration, and modern UI/UX design.

**🔗 Live Website:** [rental-service-1.onrender.com](https://rental-service-1.onrender.com)

---

## **🌟 Features**

- 🔐 **User Authentication** – Secure registration and login  
- 🏘️ **Property Management** – List, update, and remove rental properties  
- 📅 **Booking System** – Book properties with automatic availability checks  
- 🔍 **Search & Filter** – Find rentals by location, price, and more  
- 🌐 **Live Deployment** – Hosted frontend & backend  
- 💡 **Full REST API** – Clean and consistent endpoint structure

---

## **🛠️ Tech Stack**

### **Backend**
- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL
- Maven
- Docker

### **Frontend**
- React.js
- Axios
- Bootstrap / Tailwind CSS
- React Router

---

## **🧭 Project Structure**

```
Rental-Service/
├── backend/                 # Spring Boot backend
│   ├── src/
│   └── pom.xml
├── frontend/                # React frontend
│   ├── public/
│   ├── src/
│   └── package.json
├── Dockerfile
├── README.md
```

---

## **🚀 Getting Started**

### 🔧 **Backend Setup**

1. Navigate to the backend folder:

   ```bash
   cd backend
   ```

2. Update `application.properties` with your local MySQL credentials.

3. Build and run:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### 🌐 **Frontend Setup**

1. Navigate to the frontend folder:

   ```bash
   cd frontend
   ```

2. Install dependencies:

   ```bash
   npm install
   ```

3. Start the development server:

   ```bash
   npm start
   ```

---

## **📖 API Endpoints**

### **User Endpoints**
- `POST /api/users/register` – Register a new user  
- `POST /api/users/login` – Login  
- `GET /api/users/{id}` – Get user details  
- `PUT /api/users/{id}` – Update user  
- `DELETE /api/users/{id}` – Delete user  

### **Property Endpoints**
- `POST /api/properties` – Add property  
- `GET /api/properties` – Get all properties  
- `GET /api/properties/{id}` – Get property by ID  
- `PUT /api/properties/{id}` – Update property  
- `DELETE /api/properties/{id}` – Delete property  
- `GET /api/properties/search?location=city&price=range` – Search properties  

### **Booking Endpoints**
- `POST /api/bookings` – Create a booking  
- `GET /api/bookings` – List all bookings  
- `GET /api/bookings/{id}` – Get booking by ID  
- `PUT /api/bookings/{id}` – Update booking  
- `DELETE /api/bookings/{id}` – Cancel booking  

---

## **🌍 Live Demo**

▶️ Visit: [https://rental-service-1.onrender.com](https://rental-service-1.onrender.com)  
Explore the UI, list properties, register as a user, and simulate the booking process!

---

## **🤝 Contributing**

1. Fork the repo  
2. Create a new branch (`git checkout -b feature/yourFeature`)  
3. Commit your changes (`git commit -m "Add feature"`)  
4. Push to the branch (`git push origin feature/yourFeature`)  
5. Open a Pull Request

---

## **📄 License**

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

## **👤 Author**

**Karan Sahani**  
📧 karansahani723@gmail.com  
🔗 [GitHub](https://github.com/karansahani78) • [LinkedIn](https://www.linkedin.com/in/karansahani78)

