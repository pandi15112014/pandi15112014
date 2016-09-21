

create table IF NOT EXISTS accounts (
  ID BIGINT NOT NULL PRIMARY KEY,
  NAME VARCHAR(100),
  BALANCE DECIMAL (8,2)
);

CREATE TABLE IF NOT EXISTS `payments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PAYEE` bigint(20) NOT NULL DEFAULT '0',
  `RECIPIENT` bigint(20) NOT NULL DEFAULT '0',
  `AMOUNT` decimal(8,2) DEFAULT '0.00',
  `PAY_DATE` date DEFAULT NULL,
  KEY `payment_id` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO ACCOUNTS (ID, NAME, BALANCE) VALUES (1, 'Airline1', 0);
INSERT INTO ACCOUNTS (ID, NAME, BALANCE) VALUES (2, 'Airline2', 0);
INSERT INTO ACCOUNTS (ID, NAME, BALANCE) VALUES (3, 'Customer 1', 0);
INSERT INTO ACCOUNTS (ID, NAME, BALANCE) VALUES (4, 'Customer 2', 0);
INSERT INTO ACCOUNTS (ID, NAME, BALANCE) VALUES (5, 'Customer 3', 0);