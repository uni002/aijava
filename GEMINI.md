# Diary Application Project

## Architecture
- **UI (View):** Swing based graphical interface.
- **DTO (Model):** Data Transfer Object representing diary entries.
- **DAO (Database):** MySQL connection and CRUD operations.

## Database Schema
```sql
CREATE DATABASE diary_db;
USE diary_db;

CREATE TABLE entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    date DATE
);
```

## Conventions
- Use `test` package or sub-packages for separation.
- Keep classes concise and focused (High Cohesion).
- Use `System LookAndFeel` for consistency.

## Dependencies
- MySQL Connector/J driver is required.
