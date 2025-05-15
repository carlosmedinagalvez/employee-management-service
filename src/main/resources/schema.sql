CREATE TABLE employee (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
	secondName VARCHAR(50),
	paternalLastName VARCHAR(50) NOT NULL,
    maternalLastName VARCHAR(50) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
	gender ENUM('Male', 'Female', 'Other') NOT NULL,
    dateOfBirth DATE NOT NULL,
    position VARCHAR(100) NOT NULL
);