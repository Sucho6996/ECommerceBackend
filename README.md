# eCommerce Backend API

This project is an eCommerce backend API built using Spring Boot and Spring Security. The application is divided into multiple microservices: Product, Seller, Customer, and Order. The database used is PostgreSQL.

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
- PostgreSQL database integration
- Secure API endpoints

## Technologies

- Java 11
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
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
- Product Service: `http://localhost:8081`
- Seller Service: `http://localhost:8082`
- Customer Service: `http://localhost:8083`
- Order Service: `http://localhost:8084`

### API Endpoints

#### Product Service
- `GET /products`: Retrieve all products
- `POST /products`: Create a new product
- `GET /products/{id}`: Retrieve a product by ID
- `PUT /products/{id}`: Update a product by ID
- `DELETE /products/{id}`: Delete a product by ID

#### Seller Service
- `GET /sellers`: Retrieve all sellers
- `POST /sellers`: Create a new seller
- `GET /sellers/{id}`: Retrieve a seller by ID
- `PUT /sellers/{id}`: Update a seller by ID
- `DELETE /sellers/{id}`: Delete a seller by ID

#### Customer Service
- `GET /customers`: Retrieve all customers
- `POST /customers`: Create a new customer
- `GET /customers/{id}`: Retrieve a customer by ID
- `PUT /customers/{id}`: Update a customer by ID
- `DELETE /customers/{id}`: Delete a customer by ID

#### Order Service
- `GET /orders`: Retrieve all orders
- `POST /orders`: Create a new order
- `GET /orders/{id}`: Retrieve an order by ID
- `PUT /orders/{id}`: Update an order by ID
- `DELETE /orders/{id}`: Delete an order by ID

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
