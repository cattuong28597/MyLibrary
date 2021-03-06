CREATE DATABASE myLibrary;

USE myLibrary;

CREATE TABLE Books(
IdBook VARCHAR(50) PRIMARY KEY,
BookName VARCHAR(220),
Kind VARCHAR(50),
Author VARCHAR(50),
Quantity INT
);

CREATE TABLE Borrowers(
IdBorrower INT AUTO_INCREMENT PRIMARY KEY,
BorrowerName VARCHAR(220),
Address VARCHAR(220),
PhoneNumber VARCHAR(15),
Email VARCHAR(40),
IdBook VARCHAR(50),
Amount int,
FOREIGN KEY (IdBook) REFERENCES Books(IdBook)
);

 DROP TABLE Borrowers;
