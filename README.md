## Overview

This project contains a simple task management API.

## Database

The project uses MySQL database, consisting of a single table 'TASKS':

```
CREATE TABLE `tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
```

## Tools

- Language version: **Java 8**
- Build automation: **Gradle**
- Dependency injection: **Spring Web**
- ORM: **Hibernate**
- Annotation processor: **Project Lombok**

## HTTP endpoints

# Get all tasks

> GET /v1/task/getTasks

# Get a task by id

> GET /v1/task/getTask?id=<id>
