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

## Authentication

### AuthController

Handles user authentication and driver onboarding tasks.

#### Endpoints:

- **POST /auth/signup**  
  Register a new user.
  - Request Body: `SignupDto`
  - Response: `UserDto` (201 Created)

- **POST /auth/onBoardNewDriver/{userId}**  
  Onboard a new driver (admin only).
  - Path Variable: `userId` (Long)
  - Request Body: `OnBoardDriverDto`
  - Response: `DriverDto` (201 Created)

- **POST /auth/login**  
  Log in a user and return an access token and set refresh token in a cookie.
  - Request Body: `LoginRequestDto`
  - Response: `LoginResponseDto` (200 OK)

- **POST /auth/refresh**  
  Refresh the access token using a refresh token from cookies.
  - Response: `LoginResponseDto` (200 OK)

---

## Driver Operations

### DriverController

Handles operations for drivers, including ride management and profile access.

#### Endpoints:

- **POST /drivers/acceptRide/{rideRequestId}**  
  Accept a ride request.
  - Path Variable: `rideRequestId` (Long)
  - Response: `RideDto` (200 OK)

- **POST /drivers/startRide/{rideRequestId}**  
  Start a ride (with OTP validation).
  - Path Variable: `rideRequestId` (Long)
  - Request Body: `RideStartDto`
  - Response: `RideDto` (200 OK)

- **POST /drivers/endRide/{rideRequestId}**  
  End a ride.
  - Path Variable: `rideRequestId` (Long)
  - Response: `RideDto` (200 OK)

- **POST /drivers/cancelRide/{rideId}**  
  Cancel a ride.
  - Path Variable: `rideId` (Long)
  - Response: `RideDto` (200 OK)

- **POST /drivers/rateRider**  
  Rate a rider after completing a ride.
  - Request Body: `RatingDto`
  - Response: `RiderDto` (200 OK)

- **GET /drivers/getMyProfile**  
  Retrieve driver profile details.
  - Response: `DriverDto` (200 OK)

- **GET /drivers/getMyRides**  
  Get all rides handled by the driver.
  - Query Params: `pageOffset` (default: 0), `pageSize` (default: 10)
  - Response: `Page<RideDto>` (200 OK)

---

## Rider Operations

### RiderController

Handles operations for riders, including ride requests, profile access, and rating drivers.

#### Endpoints:

- **POST /riders/requestRide**  
  Request a new ride.
  - Request Body: `RideRequestDto`
  - Response: `RideRequestDto` (200 OK)

- **POST /riders/cancelRide/{rideId}**  
  Cancel a ride.
  - Path Variable: `rideId` (Long)
  - Response: `RideDto` (200 OK)

- **POST /riders/rateDriver**  
  Rate a driver after completing a ride.
  - Request Body: `RatingDto`
  - Response: `DriverDto` (200 OK)

- **GET /riders/getMyProfile**  
  Retrieve rider profile details.
  - Response: `RiderDto` (200 OK)

- **GET /riders/getMyRides**  
  Get all rides taken by the rider.
  - Query Params: `pageOffset` (default: 0), `pageSize` (default: 10)
  - Response: `Page<RideDto>` (200 OK)

---

## Health Check

### HealthCheckController

Basic health check endpoint to verify the service is running.

#### Endpoints:

- **POST /**  
  Health check endpoint that returns "OK".
  - Response: `String` (200 OK)

---

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
