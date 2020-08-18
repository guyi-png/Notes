CREATE DATABASE bookstore CHARACTER SET utf8;

USE bookstore;

CREATE TABLE `user` (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	user_name VARCHAR(30) UNIQUE NOT NULL,
	account_id INT NOT NULL
);

CREATE TABLE trades (
	trade_id INT PRIMARY KEY AUTO_INCREMENT,
	trade_time DATETIME,
	user_id INT
);

CREATE TABLE tradeitem(
	item_id INT PRIMARY KEY AUTO_INCREMENT,
	book_id INT,
	quantity INT,
	trade_id INT
);

CREATE TABLE `books`(
	book_id INT PRIMARY KEY AUTO_INCREMENT,
	author VARCHAR(50),
	title VARCHAR(50),
	price FLOAT(12,4),
	publishing_date DATE,
	sales_amount INT,
	store_number INT,
	remark VARCHAR(50)
);

CREATE TABLE account(
	account_id INT PRIMARY KEY,
	balance DECIMAL(15,4)
);


SELECT * FROM `books`;
SELECT * FROM trades;
SELECT * FROM tradeitem;
SELECT * FROM `user`;