# Spring School Student Portal Backend

This is the backend for the Student Portal application, built with Spring Boot. It provides APIs for user registration, login, two-factor authentication (2FA), and password reset.

## Features
- **User Registration**: Students can register with their name, email, password, and enrollment date.
- **Login with 2FA**: Students log in with their email and password, then verify a 6-digit code sent to their email.
- **Password Reset**: Old students (enrolled in 2024) are prompted to reset their passwords.
- **JWT Authentication**: Secure API access using JSON Web Tokens (JWT).

## Technologies Used
- **Spring Boot**: Backend framework.
- **Spring Data JPA**: Database interaction.
- **MySQL**: Database for storing student data.
- **Spring Security**: Authentication and authorization.
- **JavaMailSender**: Sending emails for 2FA.
- **JWT**: Token-based authentication.

## Setup Instructions

### Prerequisites
- Java 17 or higher
- MySQL database
- SMTP email service (Gmail)


