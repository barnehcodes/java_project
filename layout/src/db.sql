CREATE DATABASE supmti;

USE supmti;

CREATE TABLE user (
	username VARCHAR(22) PRIMARY KEY,
	password VARCHAR(33) NOT NULL);
	
INSERT INTO user values ('admin','admin');
INSERT INTO user values ('supmti','supmti123');