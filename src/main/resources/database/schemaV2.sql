CREATE SCHEMA IF NOT EXISTS RealEstateProgram;
USE RealEstateProgram;

-- Country Table
CREATE TABLE IF NOT EXISTS Country (
    country_id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL UNIQUE
);

-- PropertyType Table
CREATE TABLE IF NOT EXISTS PropertyType (
    property_type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(45) NOT NULL UNIQUE
);

-- Addresses Table
CREATE TABLE IF NOT EXISTS Addresses (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    postal_code VARCHAR(20) NOT NULL,
    country_id INT NOT NULL,
    FOREIGN KEY (country_id) REFERENCES Country(country_id) ON DELETE CASCADE
);

-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    address_id INT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    password VARCHAR(20) NOT NULL,
    subscription_tier ENUM('TIER1', 'TIER2', 'TIER3', 'NOTIER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (address_id) REFERENCES Addresses(address_id) ON DELETE CASCADE
);

-- Properties Table
CREATE TABLE IF NOT EXISTS Properties (
    property_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(255),
    description TEXT,
    property_type_id INT NOT NULL,
    price DECIMAL(15, 2),
    area_sq_ft DECIMAL(10, 2),
    bedrooms INT CHECK (bedrooms BETWEEN 1 AND 10),  -- just manually add 1-10 in dropdown and it will store here, increment of 1
    bathrooms DECIMAL(3, 1) CHECK (bathrooms BETWEEN 1.0 AND 10.0 AND bathrooms % 0.5 = 0),-- increment of .5
    address_id INT,
    status ENUM('available', 'pending', 'sold', 'rented') DEFAULT 'available',
    listed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (property_type_id) REFERENCES PropertyType(property_type_id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES Addresses(address_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PropertyImages (
    image_id INT PRIMARY KEY AUTO_INCREMENT,
    property_id INT,
    image_url VARCHAR(255),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (property_id) REFERENCES Properties(property_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS UserFinancialInfo (
    financial_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    card_holder_name VARCHAR(100) NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    card_type ENUM('Visa', 'MasterCard', 'Amex', 'Discover', 'Other') NOT NULL,
    expiration_date DATE NOT NULL,
    billing_address_id INT,
    cvv_code VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (billing_address_id) REFERENCES Addresses(address_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Reviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    property_id INT,
    rating INT DEFAULT 3 CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (property_id) REFERENCES Properties(property_id)
);