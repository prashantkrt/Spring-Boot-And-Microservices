CREATE TABLE IF NOT EXISTS Employees (
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            department VARCHAR(255),
                            email VARCHAR(255),
                            phone VARCHAR(255),
                            dateOfJoining DATE
);


INSERT INTO Employees (name, department, email, phone, dateOfJoining) VALUES
                                                                           ('John Doe', 'IT', 'john.doe@example.com', '1234567890', '2023-05-12'),
                                                                           ('Jane Smith', 'HR', 'jane.smith@example.com', '0987654321', '2023-08-25')