# Student Management System

A professional Java console application designed for educational institutions to efficiently manage students, courses, and enrollments. The system follows SOLID principles and implements a clean multi-layered architecture with clear separation of concerns.

---

## Project Description

The Student Management System provides a centralized terminal-based platform for managing academic data.

**Key goals of the system:**

- Maintain data integrity through validation
- Provide fast search, sorting, and reporting
- Ensure persistent data storage using CSV
- Demonstrate clean architecture and OOP best practices

This project is ideal as a portfolio project for Java developers learning system design and architecture.

---

## Features

### Student Management

| Option | Feature | Description |
|--------|---------|-------------|
| 1 | Add Student | Register new students with unique ID |
| 2 | View Students | Display all students with average grades |
| 3 | Remove Student | Delete student records |
| 4 | Update Student | Modify student name |
| 11 | Search Student | Find students by name |
| 12 | Sort Students | Sort students by name or grade |
| 15 | View Student Courses | List courses taken by a student |
| 21 | Top Students | Display top N performing students |
| 24 | Failed Students | View students with average grade below 50 |

### Course Management

| Option | Feature | Description |
|--------|---------|-------------|
| 5 | Add Course | Create courses with unique code |
| 6 | Update Course | Modify course name |
| 7 | Remove Course | Delete courses |
| 8 | View Courses | Display all available courses |
| 17 | Search Course | Find courses by name or code |
| 18 | Sort Courses | Alphabetical sorting |
| 16 | View Students in Course | Display students enrolled in a course |
| 22 | Most Popular Courses | Display courses with highest enrollments |

### Enrollment & Grades

| Option | Feature | Description |
|--------|---------|-------------|
| 9 | Enroll Student | Register student in a course |
| 10 | Record Grade | Assign grades (0-100) |
| 14 | Unenroll Student | Remove student from course |
| 19 | Calculate GPA | Compute student's average grade |

### System Utilities

| Option | Feature | Description |
|--------|---------|-------------|
| 13 | View All Data | Complete system report |
| 20 | System Statistics | Dashboard with totals and averages |
| 0 | Save and Exit | Auto-save to CSV |

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java SE 17+ | Core programming language |
| Multi-Layered Architecture | Separation of responsibilities |
| Service / Repository Pattern | Business and data layer separation |
| CSV File Storage | Lightweight data persistence |
| JDK Tools | Compilation and execution |

---

## Project Architecture

The system follows a multi-layered architecture to maintain modularity and scalability.

```
Presentation Layer (CLI)
         │
         ▼
Service Layer (Business Logic)
         │
         ▼
Repository Layer (Data Access)
         │
         ▼
CSV Storage
```

### Layer Responsibilities

| Layer | Responsibility |
|-------|----------------|
| Presentation | User interaction via menu-driven CLI |
| Service | Business logic and validation |
| Repository | Data storage and CRUD operations |
| Model | Domain entities (Student, Course, Enrollment) |
| Utility | File operations and input handling |

---

## Project Structure

```
Final Student_Management_System/
├── main/
│   └── Main.java
│
├── model/
│   ├── Student.java
│   ├── Course.java
│   └── Enrollment.java
│
├── repository/
│   ├── IStudentRepository.java
│   ├── StudentRepository.java
│   ├── ICourseRepository.java
│   └── CourseRepository.java
│
├── service/
│   ├── IStudentService.java
│   ├── StudentService.java
│   ├── ICourseService.java
│   ├── CourseService.java
│   ├── IEnrollmentService.java
│   └── EnrollmentService.java
│
├── util/
│   ├── DataManager.java
│   └── InputHelper.java
│
├── .gitignore
└── README.md
```

---

## Installation

### Prerequisites

- Java JDK 17 or higher

Check installation:
```bash
java -version
```

### Compile the project

```bash
javac -d bin main/*.java model/*.java repository/*.java service/*.java util/*.java
```

### Run the application

```bash
java -cp bin main.Main
```

---

## Usage

When the application starts, a menu-driven interface will appear.

### Main Menu

```
--- Student Management System (Refactored) ---
[Students]
1. Add Student        2. View Students        3. Remove Student
4. Update Student     11. Search Student      12. Sort Students
15. View Student Courses   24. View Failed Students

[Courses]
5. Add Course         6. Update Course        7. Remove Course
8. View Courses       17. Search Course       18. Sort Courses
16. View Students in Course   22. Most Popular Courses

[Enrollment & Grades]
9. Enroll Student     10. Record Grade        14. Unenroll Student
19. Calculate GPA     21. Top Students

[System]
13. View All Data     20. System Statistics

0. Save and Exit
```

### Example Operations

**Add a Student**
```
Enter Student ID: 202401
Enter Name: Alex Rivers
Success: Student added.
```

**Add a Course**
```
Enter Course Code: 101
Available types: Math, Physics, Chemistry, Biology, Computer Science
Enter Course Name: Math
Success: Course added.
```

**Enroll Student**
```
Enter Student ID: 202401
Enter Course Code: 101
Success: Student enrolled.
```

**Record Grade**
```
Enter Student ID: 202401
Enter Course Code: 101
Enter Grade (0-100): 85
Success: Grade recorded.
```

---

## Validation & Error Handling

The system includes strong validation rules.

| Field | Validation |
|-------|------------|
| Student ID | Must be positive and unique |
| Student Name | 3-50 alphabetic characters |
| Course Code | Numeric and unique |
| Course Name | Valid predefined course (math, physics, chemistry, biology, computer science) |
| Grade | Value between 0-100 |

Error handling includes:

- Input validation
- User-friendly error messages
- Duplicate prevention
- Confirmation prompts for deletion

---

## Data Storage

The system stores data in a CSV file.

### Example Format

```csv
COURSE;101;math
COURSE;102;physics
STUDENT;202401;Alex Rivers;101:85.5,102:90
```

### Data Behavior

- Loaded automatically at startup
- Saved automatically on exit

---

## Environment Variables

No environment variables are required. The system uses:

- `school_data.csv` - Auto-created in project root for data persistence

---

## Future Improvements

Possible future enhancements:

- Add database support (MySQL / PostgreSQL)
- Implement REST API version
- Add Graphical User Interface (GUI)
- Implement JUnit testing
- Export reports to PDF / Excel
- Add authentication system

---

## Contributing

Contributions are welcome.

Steps to contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m "Add new feature"`
4. Push to your branch: `git push origin feature-name`
5. Open a Pull Request

---

## License

This project is licensed under the MIT License.

---

## Contact

| | |
|---|---|
| Developer | Mahmoud Olaim |
| GitHub | [mahmoudolaim682](https://github.com/mahmoudolaim682) |
| Project | [Student Management System](https://github.com/mahmoudolaim682-source/Final-Student_Management_System) |
