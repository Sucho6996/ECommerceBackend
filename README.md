# 🛒 Ecommerce Backend API

This project is a *Microservices-based Ecommerce Backend API* built using *Spring Boot* and *Spring Security. The backend manages product listings, sellers, customers, orders, and integrates **Razorpay* for payment processing. All services communicate using REST APIs, and *PostgreSQL* is used for data management.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=flat&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=flat&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396.svg?style=flat&logo=java&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192.svg?style=flat&logo=postgresql&logoColor=white)
![Razorpay](https://img.shields.io/badge/Razorpay-2C64C3.svg?style=flat&logo=razorpay&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?style=flat&logo=docker&logoColor=white)

## 📝 *Table of Contents*
1. [Features](#features)
2. [Technologies](#technologies)
3. [Architecture](#architecture)
4. [Setup](#setup)
5. [Usage](#usage)
6. [API Endpoints](#api-endpoints)
7. [Contributing](#contributing)
8. [License](#license)

## 🌟 *Features*
* 🔑 *User authentication and authorization* with *Spring Security*.
* 🛍️ *CRUD operations* for products, sellers, customers, and orders.
* 🏗️ *Microservices architecture* for better scalability and maintainability.
* 💳 *Integration with Razorpay* for secure transaction handling.
* 📦 *PostgreSQL* for database management.
* 🔐 *Secure API endpoints*.

## 💻 *Technologies*
![Java](https://img.shields.io/badge/Java-007396.svg?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=flat&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=flat&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192.svg?style=flat&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?style=flat&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36.svg?style=flat&logo=apache-maven&logoColor=white)
![Razorpay](https://img.shields.io/badge/Razorpay-2C64C3.svg?style=flat&logo=razorpay&logoColor=white)

## 🏛️ *Architecture*
This project follows a *Microservices Architecture*:

* *Product Service*: Manages product details.
* *Seller Service*: Handles seller operations.
* *Customer Service*: Handles user data and customer operations.
* *Order Service*: Manages order details and processes transactions.

Each microservice has its own database schema and communicates with other services through REST APIs.

## 🛠 *Setup*

### 🛑 *Prerequisites*
* Java 11+
* Maven
* PostgreSQL
* Razorpay API key
* Docker (optional for containerization)

### 📦 *Installation*

1. *Clone the repository*:
   bash
   git clone https://github.com/Sucho6996/EcommerceBackend.git
   cd EcommerceBackend
   

2. *Set up PostgreSQL*:
   - Create a separate database for each microservice: product_db, seller_db, customer_db, order_db.
   - Update the application.properties file of each microservice with your PostgreSQL configurations.

3. *Build and run each microservice*:
   bash
   # Product Service
   cd product-service
   mvn clean install
   mvn spring-boot:run

   # Seller Service
   cd ../seller-service
   mvn clean install
   mvn spring-boot:run

   # Customer Service
   cd ../customer-service
   mvn clean install
   mvn spring-boot:run

   # Order Service
   cd ../order-service
   mvn clean install
   mvn spring-boot:run
   

### 🐳 *Docker (Optional)*

1. *Build Docker images*:
   bash
   docker build -t product-service ./product-service
   docker build -t seller-service ./seller-service
   docker build -t customer-service ./customer-service
   docker build -t order-service ./order-service
   

2. *Run Docker containers*:
   bash
   docker run -d -p 8081:8081 product-service
   docker run -d -p 8082:8082 seller-service
   docker run -d -p 8083:8083 customer-service
   docker run -d -p 8084:8084 order-service
   

## 🧑‍💻 *Usage*

Each microservice is accessible via different ports:

* *Product Service*: http://localhost:8081
* *Seller Service*: http://localhost:8082
* *Customer Service*: http://localhost:8083
* *Order Service*: http://localhost:8084

## 📬 *API Endpoints*

### 🔖 *Product Service* /product
* `GET /findAll`: Retrieve all products.
* `POST /add`: Create a new product.
* `GET /find/{id}`: Retrieve a product by ID.
* `GET /search/{keyword}`: Retrieve products based on a keyword search.
* `GET /image/{id}`: Retrieve a product image by ID.
* `PUT /modify`: Update a product by ID.
* `DELETE /delete/{id}`: Delete a product by ID.


### 🛍️ *Seller Service* /seller
* `GET /sellers`: Retrieve all sellers
* `POST /sellers`: Create a new seller
* `GET /sellers/{id}`: Retrieve a seller by ID
* `PUT /sellers/{id}`: Update a seller by ID
* `DELETE /sellers/{id}`: Delete a seller by ID


### 👤 *Customer Service* /user
* `POST /signin`: Register a new user.
* `POST /login`: Authenticate a user and return a JWT token.
* `GET /findAll`: Retrieve all products.
* `GET /find/{id}`: Retrieve a product by ID.
* `GET /search/{keyword}`: Retrieve products based on a keyword search.
* `GET /order`: Place an order with parameters for product ID, seller name, price, and phone number.
* `GET /cart`: Add a product to the cart with parameters for product ID, seller name, price, and phone number.
* `GET /view/{id}`: Retrieve user details by user ID (phone number).
* `GET /orders/{id}`: Retrieve all orders for a user by user ID (phone number).
* `GET /carts/{id}`: Retrieve all cart items for a user by user ID (phone number).
* `GET /order/{id}`: Retrieve order details by order ID.
* `GET /cart/{id}`: Retrieve cart details by cart ID.
* `POST /save`: Save user details.


### 📦 *Order Service* /order
* `GET /view/orders/{id}`: Retrieve all orders for a user by user ID.
* `GET /view/carts/{id}`: Retrieve all cart items for a user by user ID.
* `GET /view/orderDetails/{id}`: Retrieve order details by order ID.
* `GET /view/cartDetails/{id}`: Retrieve cart details by cart ID.
* `POST /create/order`: Create a new order with product details (integrated with Razorpay for payment).
* `POST /paymentCallback`: Update order status based on the payment response (callback from Razorpay).
* `POST /save/cart`: Add a product to the cart with product details.
* `GET /user/{phNo}`: Retrieve user details by phone number.
* `GET /seller/{sellername}`: Retrieve seller details by seller name.
* `GET /product/{pId}`: Retrieve product details by product ID.


## 🤝 *Contributing*
Contributions are welcome! Please follow these steps:
1. Fork the repository 🍴
2. Create a feature branch 🚀
   bash
   git checkout -b feature-branch
   
3. Commit your changes 💾
   bash
   git commit -m 'Add new feature'
   
4. Push to the branch 📤
   bash
   git push origin feature-branch
   
5. Open a pull request 📬

## 📜 *License*
This project is licensed under the MIT License. See the LICENSE file for details.
```
