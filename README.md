# Plastic Sheet Factory Management System (Java + MySQL + Swing)

Developed by **Sivan Lasri** and **Elinor Srur**  
As part of the *Introduction to System Programming* course.    
Institution: **Kinneret Academic College**.

---

## 🏭 Project Overview

This project simulates a control system for a **plastic sheet factory** that manufactures greenhouse covers.  
It reads production data from a CSV file (`Factory.csv`), imports it into a **MySQL** database, and provides a graphical user interface (GUI) built using **Java Swing**.

The system allows users to perform **six analytical SQL queries** that help visualize and analyze factory operations, including total production, defect rates, and efficiency statistics.  
Additionally, **multithreading (Threads)** is used in the sixth query to improve performance and enable parallel data analysis.

---

## ⚙️ Main Features

- ✅ **CSV file parsing and validation** (`Factory.csv`)  
- 🧠 **Connection between Java and MySQL** using JDBC  
- 💻 **Swing-based GUI** for query selection and results display  
- 📊 **Six predefined SQL queries** for production insights  
- ⚡ **Multithreaded execution** (Query 6) for efficient data analysis  
- 🧩 Demonstrates core principles of **system programming**, **database integration**, and **GUI development**

---

## 🔍 SQL Queries Overview

1. **Total Production per Machine**  
   Calculates how many sheets were produced by each machine.

2. **Average Defect Rate per Machine**  
   Computes the average number of defective sheets for each machine to identify problematic equipment.

3. **Daily Production Summary**  
   Displays total production per day to monitor performance trends.

4. **Production Efficiency Comparison**  
   Compares each machine’s efficiency (good sheets vs. total sheets).

5. **Top Performing Machines**  
   Finds the most productive and reliable machines based on performance metrics.

6. **Overall Factory Statistics (Multithreaded)**  
   Executes complex calculations such as total sheets, total defects, and factory-wide averages **using Threads**
   improving performance by processing multiple datasets in parallel.

---

## 🧰 Technologies Used

- **Java 17**
- **Java Swing**
- **MySQL**
- **JDBC**
- **CSV File Handling**
- **Multithreading**
- **IntelliJ IDEA**

---

## 🪄 Installation & Setup Instructions

### 1. Install JDK
Download and install the **JDK Development Kit** from:  
👉 [https://www.oracle.com/il-en/java/technologies/downloads](https://www.oracle.com/il-en/java/technologies/downloads)

Choose your operating system and install the **x64 MSI Installer**.  

---

### 2. Install IntelliJ IDEA
Download **IntelliJ IDEA** (Community or Ultimate edition):  
👉 [https://www.jetbrains.com/idea/download](https://www.jetbrains.com/idea/download)

Follow the installation steps for your OS.

---

### 3. Install MySQL
Download **MySQL Community Installer**:  
👉 [https://dev.mysql.com/downloads/installer](https://dev.mysql.com/downloads/installer)

- Select the **"Full"** installation option.  
- During installation, set your own **username** and **password** you’ll need them later.  
- After installation, open **MySQL Workbench**.

---

### 4. Create the Database
1. In **MySQL Workbench**, create a new schema named `projectfactory`.  
2. Right-click it → choose **Table Data Import Wizard**.  
3. Import the provided `Factory.csv` file.  
4. In the import settings, make sure to change the `LogTime` column type to **DATETIME** and use the format: %d/%m/%Y %H:%M:%S

---

### 5. Open the Project in IntelliJ IDEA
1. Create a folder in drive C named `JvOOP` and inside it create another folder named `_SourceDev.Java` (Or change it in code).
2. Place the project folder named `ProjectFactory` inside that directory.  
3. Open IntelliJ → **File → Open...** → select the `ProjectFactory` folder.  
4. Locate the `ConnectionToSql` class and Run the class.  
5. Run the `Login` screen and enter your **MySQL username and password**.  

If the connection is successful, you’ll see a confirmation message and the main menu with all available queries.

---

### 6. Run the Application
In IntelliJ, click: Run → Run 'ConnectionToSql.Main'

or execute via terminal: java -jar FactorySystem.jar

Then, use the Swing GUI to select one of the six queries and view the results.

---

## 💬 Notes

* The system automatically imports the `Factory.csv` data on first run.
* Make sure MySQL is running before launching the application.
* The sixth query demonstrates **parallel data computation** using Java threads.

---

## 👩‍💻 Authors

* **Sivan Lasri**
* **Elinor Srur**

Course: *Introduction to System Programming*.
Institution: **Kinneret Academic College**.

---
