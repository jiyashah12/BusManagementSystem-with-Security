# Bus Management System 

## Overview
The Bus Management System is a Spring Boot application designed to manage bus schedules, bookings, and customer information. It provides RESTful APIs for adding, updating, deleting, and retrieving bus, schedule, and booking details.

## Features
- Manage buses, routes, and schedules
- Customer registration and authentication
- Booking management
- JWT-based authentication

## Technologies Used
- Java
- Spring Boot
- Spring Security
- Maven
- JPA (Java Persistence API)
- JWT (JSON Web Token)
- Hibernate
- PostgreSQL (or any other relational database)
- IntelliJ IDEA

## Project Structure
- `src/main/java/com/example/BusManagementSystem`
  - `controllers`: Contains the REST controllers for handling HTTP requests.
  - `entities`: Contains the entity classes representing the database tables.
  - `exceptions`: Contains custom exception classes and global exception handlers.
  - `repositories`: Contains the repository interfaces for data access.
  - `services`: Contains the service classes implementing the business logic.


## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- PostgreSQL (or any other relational database)

## API Endpoints

### Bus Controller
- **POST** `/api/bus/admin/addbus/{route_id}/{schedule_id}`: Add a new bus.
- **DELETE** `/api/bus/admin/removebusbyid/{id}`: Remove a bus by ID.
- **GET** `/api/bus/getalldetails`: Get all bus details.
- **GET** `/api/bus/getbusbyid/{bus_id}`: Get bus details by ID.
- **PUT** `/api/bus/admin/updatebus/{bus_id}`: Update bus details.

### Booking Controller
- **GET** `/api/booking/search/{source}/{destination}`: Search for buses by source and destination.
- **POST** `/api/booking/bookseats/{cust_id}/{date}/{bus_id}/{seats}`: Book seats on a bus.
- **DELETE** `/api/booking/deletebooking/{booking_id}`: Delete a booking by ID.

### Schedule Controller
- **POST** `/api/schedule/addschedule`: Add a new schedule.
- **GET** `/api/schedule/getallschedules`: Get all schedules.
- **GET** `/api/schedule/getschedulebyid/{schedule_id}`: Get schedule details by ID.
- **PUT** `/api/schedule/updateschedule/{schedule_id}`: Update schedule details.
- **DELETE** `/api/schedule/deleteschedulebyid/{schedule_id}`: Delete a schedule by ID.

### Route Controller
- **POST** `/api/route/addroute`: Add a new route.
- **GET** `/api/route/getallroutes`: Get all routes.
- **GET** `/api/route/getroutebyid/{route_id}`: Get route details by ID.
- **PUT** `/api/route/updateroute/{route_id}`: Update route details.
- **DELETE** `/api/route/deleteroutebyid/{route_id}`: Delete a route by ID.

### Customer Controller
- **POST** `/api/customer/register`: Add a new customer.
- **GET** `/api/customer/admin/viewAllCustomers`: Get all customers.
- **GET** `/api/customer/getcustomerbyid/{cust_id}`: Get customer details by ID.
- **PUT** `/api/customer/updatecustomer/{cust_id}`: Update customer details.
- **DELETE** `/api/customer/deletecustomerbyid/{cust_id}`: Delete a customer by ID.
- **DELETE** `/api/customer/admin/deleteallcustomers`: Delete all customers.
- **GET** `/api/customer/admin/getcustomersbyemail/{email}`: Get customer details by email
- **POST** `/api/customer/login`: Customer login.

### Exception Handling
The application uses a global exception handler to manage exceptions and provide appropriate HTTP status codes and messages.

### Authentication
The application uses JWT for authentication. To access protected endpoints, you need to include the JWT token in the Authorization header of your requests.

