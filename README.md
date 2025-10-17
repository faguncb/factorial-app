# Factorial Calculator: Java Paradigms

A full-stack web application that demonstrates **three fundamental programming paradigms** in Java by implementing a factorial calculator using Procedural, Object-Oriented Programming (OOP), and Functional approaches.

![Java](https://img.shields.io/badge/Java-11+-orange.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Programming Paradigms](#programming-paradigms)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Technologies Used](#technologies-used)
- [Learning Objectives](#learning-objectives)

## 🎯 Overview

This project is an educational tool designed to help developers understand different programming paradigms by comparing implementations of the same algorithm (factorial calculation) using three distinct approaches:

- **Procedural**: Iterative approach using loops
- **Object-Oriented**: Recursive approach using class methods
- **Functional**: Stream-based approach using Java Streams API

## ✨ Features

- 🔢 Calculate factorial for numbers 0-20
- 🎨 Clean and intuitive web interface
- 🔄 Real-time paradigm switching
- 🧪 Comprehensive test suite (backend & frontend)
- 🚀 Lightweight HTTP server (no external frameworks)
- 📦 Zero external dependencies for core functionality

## 🧩 Programming Paradigms

### 1. Procedural Programming
```java
public static long compute(int n) {
    long result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
}
```
- Uses iterative loops
- Sequential execution
- Mutable state

### 2. Object-Oriented Programming (OOP)
```java
public long compute(int n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    return n * compute(n - 1); // Recursive
}
```
- Encapsulation in classes
- Recursive method calls
- Instance-based approach

### 3. Functional Programming
```java
public static long compute(int n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    return LongStream.rangeClosed(2, n)
            .reduce(1, (acc, val) -> acc * val);
}
```
- Declarative style
- Stream operations
- Immutable approach

## 📁 Project Structure

```
factorial-app/
├── src/
│   ├── backend/
│   │   ├── Main.java                  # Entry point & HTTP server
│   │   ├── ProceduralFactorial.java   # Procedural implementation
│   │   ├── OOPFactorial.java          # OOP implementation
│   │   └── FunctionalFactorial.java   # Functional implementation
│   ├── frontend/
│   │   ├── index.html                 # Main HTML interface
│   │   ├── styles.css                 # Styling
│   │   └── script.js                  # Frontend logic
│   └── tests/
│       ├── backend/
│       │   └── FactorialTests.java    # Backend unit tests
│       └── frontend/
│           └── test.js                # Frontend tests
├── out/                               # Compiled classes
└── README.md                          # Project documentation
```

## 🔧 Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
    - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- **Web Browser**: Any modern browser (Chrome, Firefox, Safari, Edge)

## 🚀 Installation & Setup

1. **Clone the repository** (or download the project):
   ```bash
   cd factorial-app
   ```

2. **Compile the Java files**:
   ```bash
   javac -d out src/backend/*.java
   ```

3. **Verify compilation**:
   ```bash
   ls out/
   # Should show: Main.class, ProceduralFactorial.class, OOPFactorial.class, FunctionalFactorial.class
   ```

## ▶️ Running the Application

1. **Start the server**:
   ```bash
   java -cp out Main
   ```

2. **Access the application**:
    - Open your browser and navigate to: `http://localhost:8000`

3. **Using the calculator**:
    - Select a programming paradigm from the dropdown
    - Enter a number between 0-20
    - Click "Compute" to see the result

4. **Stop the server**:
    - Press `Ctrl+C` in the terminal

## 🧪 Testing

### Backend Tests

Run the Java unit tests:
```bash
javac -cp out -d out src/tests/backend/*.java
java -cp out FactorialTests
```

### Frontend Tests

Run the JavaScript tests:
```bash
node src/tests/frontend/test.js
```

## 📡 API Documentation

### Endpoints

#### `GET /`
Returns the main HTML page.

#### `GET /styles.css`
Returns the CSS stylesheet.

#### `GET /script.js`
Returns the JavaScript file.

#### `GET /factorial/{paradigm}/{number}`
Calculates factorial using the specified paradigm.

**Parameters:**
- `paradigm` (string): One of `procedural`, `oop`, or `functional`
- `number` (integer): Number between 0-20

**Example:**
```
GET /factorial/functional/5
Response: 120
```

**Status Codes:**
- `200`: Success
- `400`: Invalid request (bad paradigm or number out of range)

## 🛠 Technologies Used

### Backend
- **Java 11+**: Core language
- **HttpServer**: Built-in Java HTTP server (`com.sun.net.httpserver`)

### Frontend
- **HTML5**: Structure
- **CSS3**: Styling
- **Vanilla JavaScript**: Interactivity and API calls

### Testing
- **Plain Java**: Backend unit tests
- **Node.js**: Frontend test runner

## 📚 Learning Objectives

By exploring this project, you will learn:

1. **Paradigm Comparison**: Understand how different programming paradigms solve the same problem
2. **Java Streams**: Learn functional programming concepts in Java
3. **HTTP Servers**: Build a simple web server without frameworks
4. **Full-Stack Development**: Connect frontend and backend components
5. **Testing**: Write and run tests for different parts of the application
6. **Clean Code**: See examples of clear, maintainable code in different styles

## 🤝 Contributing

Feel free to fork this project and experiment with:
- Adding new paradigms or algorithms
- Improving the UI/UX
- Adding more comprehensive tests
- Implementing additional features

## 📄 License

This project is open source and available under the MIT License.

---

**Happy Coding!** 🎉

For questions or suggestions, please open an issue or submit a pull request.
