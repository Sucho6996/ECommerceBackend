# eCommerce Backend API

This project is an Ecommerce Backend API built using Spring Boot and Spring Security. The application is divided into multiple microservices: Product, Seller, Customer, and Order. The database used is PostgreSQL. in this project the `Product` service and `Order` service will only be accessed through `Seller` and `Customer` service. The payment endpoint used is `Razorpay`.

## Table of Contents

- Features
- Technologies
- Architecture
- Setup
- Usage
- API Endpoints
- Contributing
- License

## Features

- User authentication and authorization using Spring Security
- CRUD operations for products, sellers, customers, and orders
- Microservices architecture for scalability and maintainability
- Integration with Razorpay for handling transactions.
- PostgreSQL database integration
- Secure API endpoints

## Technologies

- Java 11
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- Razorpay API
- Docker (optional for containerization)

## Architecture

The project is divided into the following microservices:

1. **Product Service**: Manages product-related operations.
2. **Seller Service**: Manages seller-related operations.
3. **Customer Service**: Manages customer-related operations.
4. **Order Service**: Manages order-related operations.

Each microservice has its own database schema and communicates with other services via REST APIs.

## Setup

### Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL
- Razorpay
- Docker (optional)

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/Sucho6996/EcommerceBackend.git
    cd EcommerceBackend
    ```

2. **Set up PostgreSQL**:
    - Create a database for each microservice (e.g., `product_db`, `seller_db`, `customer_db`, `order_db`).
    - Update the database configurations in each microservice's `application.properties` file.

3. **Build and run each microservice**:
    ```bash
    cd product-service
    mvn clean install
    mvn spring-boot:run

    cd ../seller-service
    mvn clean install
    mvn spring-boot:run

    cd ../customer-service
    mvn clean install
    mvn spring-boot:run

    cd ../order-service
    mvn clean install
    mvn spring-boot:run
    ```

### Docker (Optional)

You can use Docker to containerize the microservices. Ensure Docker is installed and running on your machine.

1. **Build Docker images**:
    ```bash
    docker build -t product-service ./product-service
    docker build -t seller-service ./seller-service
    docker build -t customer-service ./customer-service
    docker build -t order-service ./order-service
    ```

2. **Run Docker containers**:
    ```bash
    docker run -d -p 8081:8081 product-service
    docker run -d -p 8082:8082 seller-service
    docker run -d -p 8083:8083 customer-service
    docker run -d -p 8084:8084 order-service
    ```

## Usage

### Accessing the API

Each microservice runs on a different port:
- Product Service: `http://localhost:8080`
- Seller Service: `http://localhost:8081`
- Customer Service: `http://localhost:8090`
- Order Service: `http://localhost:9090`

### API Endpoints

**Product Service**
`Base URL`
/product

Endpoints
-`GET /findAll`: Retrieve all products.
-`POST /add`: Create a new product.
-`GET /find/{id}`: Retrieve a product by ID.
-`GET /search/{keyword}`: Retrieve products based on a keyword search.
-`GET /image/{id}`: Retrieve a product image by ID.
-`PUT /modify`: Update a product by ID.
-`DELETE /delete/{id}`: Delete a product by ID.

**Seller Service**
`Base URL`
/seller
- `GET /sellers`: Retrieve all sellers
- `POST /sellers`: Create a new seller
- `GET /sellers/{id}`: Retrieve a seller by ID
- `PUT /sellers/{id}`: Update a seller by ID
- `DELETE /sellers/{id}`: Delete a seller by ID

**Customer Service**
`Base URL`
/user

Endpoints
-`POST /signin`: Register a new user.
-`POST /login`: Authenticate a user and return a JWT token.
-`GET /findAll`: Retrieve all products.
-`GET /find/{id}`: Retrieve a product by ID.
-`GET /search/{keyword}`: Retrieve products based on a keyword search.
-`GET /order`: Place an order with parameters for product ID, seller name, price, and phone number.
-`GET /cart`: Add a product to the cart with parameters for product ID, seller name, price, and phone number.
-`GET /view/{id}`: Retrieve user details by user ID (phone number).
-`GET /orders/{id}`: Retrieve all orders for a user by user ID (phone number).
-`GET /carts/{id}`: Retrieve all cart items for a user by user ID (phone number).
-`GET /order/{id}`: Retrieve order details by order ID.
-`GET /cart/{id}`: Retrieve cart details by cart ID.
-`POST /save`: Save user details.

**Order Service**
`Base URL`
/order&cart

Endpoints
-`GET /view/orders/{id}`: Retrieve all orders for a user by user ID.
-`GET /view/carts/{id}`: Retrieve all cart items for a user by user ID.
-`GET /view/orderDetails/{id}`: Retrieve order details by order ID.
-`GET /view/cartDetails/{id}`: Retrieve cart details by cart ID.
-`POST /create/order`: Create a new order with product details (integrated with Razorpay for payment).
-`POST /paymentCallback`: Update order status based on the payment response (callback from Razorpay).
-`POST /save/cart`: Add a product to the cart with product details.
-`GET /user/{phNo}`: Retrieve user details by phone number.
-`GET /seller/{sellername}`: Retrieve seller details by seller name.
-`GET /product/{pId}`: Retrieve product details by product ID.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
