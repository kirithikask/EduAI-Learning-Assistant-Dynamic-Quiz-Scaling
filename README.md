# EduAI: Learning Assistant with Dynamic Quiz Scaling

📌 Problem Statement

Students often struggle to find structured, topic-wise learning resources and adaptive guidance. Traditional learning platforms lack personalization, progress tracking, and interactive elements, making it difficult for school/college learners and self-learners to stay engaged and improve effectively.

📌 Proposed Solution

An AI-inspired adaptive e-learning system using Java + MySQL that provides notes, resources, AI tutor guidance, adaptive quizzes, progress tracking, and gamification.

📌 Targeted Users

School & college students

Self-learners preparing for exams

Institutions looking for smart study platforms

📌 Features

Student login/registration

Topic search → notes + resources

AI tutor with hints & guidance

Adaptive quiz (dynamic difficulty)

Progress tracking & personalized study plan

Gamified rewards & badges

Admin panel to manage content

📌 Tech Stack

Frontend/UI: Java Swing / JavaFX for building interactive user
interfaces.

Backend Logic: Core Java with JDBC for handling application
logic and database connectivity.

Database: nosql-mongodb for storing subjects, topics, notes, resources,
and student progress.

PDF Handling: Apache PDFBox for extracting text from PDF
notes.

Libraries for gamification, charts, or progress visualization in
Java.

📌 OOP Concepts Used

Encapsulation → Secure student/admin data with getters & setters

Abstraction → Interfaces/abstract classes for common operations (e.g., user, content, quiz)

Inheritance → Base class User extended by Student and Admin

Polymorphism → Method overloading (e.g., search by topic/subject) and method overriding (e.g., dashboard views)

Association → Linking users with quizzes, notes, and progress records

Aggregation & Composition → A Course contains multiple Topics; a Quiz contains Questions

Exception Handling → Handles invalid input, login errors, and database exceptions securely
