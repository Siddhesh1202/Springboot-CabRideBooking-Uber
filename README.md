# Uber Ride Booking App Backend

This is a monolithic backend application for an Uber-like cab booking system. The project is built using Java Spring Boot, Spring MVC, and Spring Data JPA with MySQL/PostgreSQL integration. It provides a RESTful API that handles rider and driver interactions, real-time distance calculations, secure user authentication, and a variety of booking and payment features. The application is hosted on AWS Elastic Beanstalk.

## Features

1. **Signup as Rider**
   - Allows new users to register as riders.
2. **Signin**
   - Enables riders and drivers to authenticate themselves via Google OAuth and JWT-based security.
3. **Request Ride**
   - Riders can request rides by specifying their destination.
4. **Accept Ride by Driver**
   - Drivers can accept ride requests from nearby riders.
5. **Onboard New Driver (Admin Only)**
   - Admin users can onboard new drivers to the system.
6. **Make Payment Transaction**
   - Riders can complete payments for rides.
7. **Rate Rider**
   - Drivers can rate riders after a ride is completed.
8. **Rate Driver**
   - Riders can rate drivers after a ride is completed.
9. **Get All My Rides**
   - Riders and drivers can view a history of all their rides.

## API Documentation

The complete API documentation is available at:  
[Swagger UI](http://uberapp-springboot-env.eba-wkcd9jcr.us-east-1.elasticbeanstalk.com/swagger-ui/index.html)

## Technology Stack

- **Java Spring Boot** - Backend framework for building the RESTful APIs.
- **Spring MVC** - Handling HTTP requests and routing.
- **Spring Data JPA** - ORM framework used for data persistence with MySQL/PostgreSQL integration.
- **Spring Security** - Used for authentication and authorization with Google OAuth and JWT.
- **PostGIS** - Spatial database extender for PostgreSQL used for handling location-based queries.
- **OSRM API** - Used for efficient and real-time distance calculations in ride booking workflows.
- **AWS Elastic Beanstalk** - Used for hosting the application with CI/CD pipelines set up via AWS CodePipeline and CodeDeploy.
- **JUnit and Mockito** - Used for unit and integration testing.

## Prerequisites

1. Java 17+
2. MySQL/PostgreSQL database
3. AWS account for deployment (Optional for local setup)
4. Maven

## Security Features:

1. Authentication: JWT tokens are used for secure authentication and session management.
2. Authorization: Role-based access control is implemented to manage permissions, ensuring that only authorized users can perform specific actions.
3. Data Encryption: Sensitive data, including passwords and payment details, is encrypted to protect user information from unauthorized access.
