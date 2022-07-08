CREATE TABLE clients (
	id_client 		BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name 		VARCHAR ( 50 ) NOT NULL,
	last_name 		VARCHAR ( 50 ) NOT NULL,
	birthday		DATE  NOT NULL,
	gender 			CHARACTER ( 1 ) NOT NULL,
	home_phone		VARCHAR ( 20 ) NOT NULL,
	cell_phone 		VARCHAR ( 20 ) NOT NULL,
	address_home 	VARCHAR ( 255 ) NOT NULL,
	profession 		VARCHAR ( 50 ) NOT NULL,
	incomes			NUMERIC (20, 2) NOT NULL,
	created			TIMESTAMP  NOT NULL,
	last_modified	TIMESTAMP  NOT NULL
);