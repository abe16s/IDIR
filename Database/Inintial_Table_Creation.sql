CREATE SCHEMA IDIR;
USE IDIR;

CREATE TABLE MEMBER_TABLE (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(60) NOT NULL,
    Member_Address VARCHAR(30) NOT NULL,
    Phone_No CHAR(10) UNIQUE,
    Age INT,
    Gender VARCHAR(6),
    Occupation VARCHAR(20),
    Religion VARCHAR(15),
    Photo VARCHAR(255) UNIQUE
);

CREATE TABLE IDIR_TABLE (
    Official_Name VARCHAR(50) PRIMARY KEY,
    Office_Address VARCHAR(30) NOT NULL,
    Store_Address VARCHAR(30),
    Bank_Acc_No VARCHAR(30) NOT NULL,
    Total_Amount DECIMAL(9 , 2 ) DEFAULT 0.0,
    Chairman INT,
    Vice_Chairman INT,
    Secretary INT,
    Accountant INT,
    Money_Holder INT,
    Money_Collector INT,
    Property_Buyer INT,
    Shift_Supervisor_1 INT,
    Shift_Supervisor_2 INT,
    Shift_Supervisor_3 INT,
    Auditor_1 INT,
    Auditor_2 INT,
    Starting_Date DATE,
    Rules_And_Regulations VARCHAR(255),
    FOREIGN KEY (Chairman)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Vice_Chairman)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Secretary)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Accountant)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Money_Holder)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Money_Collector)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Property_Buyer)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Shift_Supervisor_1)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Shift_Supervisor_2)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Shift_Supervisor_3)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Auditor_1)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Auditor_2)
        REFERENCES MEMBER_TABLE (ID)
);

CREATE TABLE PROPERTY (
    Property_Type VARCHAR(15) PRIMARY KEY,
    Number_Of_Items INT,
    Individual_Price DECIMAL(8 , 2 )
);

CREATE TABLE RECEIPT (
    Receipt_No INT AUTO_INCREMENT PRIMARY KEY,
    Issued_Date DATE NOT NULL,
    Issued_For INT NOT NULL,
    Issued_By INT NOT NULL,
    Reason_for_Payment VARCHAR(50) NOT NULL,
    Amount DECIMAL(8 , 2 ) NOT NULL,
    Money_Type VARCHAR(12),
    Deleted VARCHAR(100) DEFAULT 'NO',
    FOREIGN KEY (Issued_For)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Issued_By)
        REFERENCES MEMBER_TABLE (ID)
);

CREATE TABLE FAMILY (
    FullName VARCHAR(60) NOT NULL,
    Relationship VARCHAR(20) NOT NULL,
    Member_ID INT NOT NULL,
    Phone_No CHAR(10) UNIQUE,
    FOREIGN KEY (Member_ID)
        REFERENCES MEMBER_TABLE (ID),
    UNIQUE (Member_ID , First_Name , Father_Name , Grandfather_Name)
);
  
CREATE TABLE AGENDA (
    Agenda_No INT AUTO_INCREMENT PRIMARY KEY,
    Written_Date DATE,
    Title VARCHAR(100),
    Writer INT NOT NULL,
    Written_Text VARCHAR(255),
    FOREIGN KEY (Writer)
        REFERENCES MEMBER_TABLE (ID)
);

CREATE TABLE MONTHLY_PAYMENT_HISTORY (
    ID INT,
    yr INT,
    Jan INT DEFAULT 0,
    Feb INT DEFAULT 0,
    Mar INT DEFAULT 0,
    Apr INT DEFAULT 0,
    May INT DEFAULT 0,
    Jun INT DEFAULT 0,
    Jul INT DEFAULT 0,
    Aug INT DEFAULT 0,
    Sep INT DEFAULT 0,
    Oct INT DEFAULT 0,
    Nov INT DEFAULT 0,
    `Dec` INT DEFAULT 0,
    PRIMARY KEY (ID , yr)
);
        
DELIMITER $$

CREATE TRIGGER MEMBER_DELETE BEFORE DELETE ON MEMBER_TABLE FOR EACH ROW
BEGIN
  SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "You can't delete members"; 
END $$

DELIMITER ;

-- Trigger to add a new member that's just been added into the MONTHLY_PAYMENT_HISTORY
DELIMITER $$

CREATE TRIGGER addMemberToMonthlyHistory AFTER INSERT ON MEMBER_TABLE FOR EACH ROW
BEGIN
	DECLARE yearr INT;
    SELECT YEAR(CURDATE()) into yearr;
	INSERT INTO MONTHLY_PAYMENT_HISTORY (ID, yr)
	VALUES (New.ID, yearr);
END $$

DELIMITER ;

-- A function to add all the members in to MONTHLY_PAYMENT_HISTORY as New Year comes
DELIMITER $$

CREATE PROCEDURE populateMonthlyPaymentHistory(newYear INT) 
BEGIN
	DECLARE n INT DEFAULT 0;
	DECLARE i INT DEFAULT 0;
    DECLARE member_id INT;
	SELECT COUNT(*) FROM MEMBER_TABLE INTO n;
	SET i=0;
	WHILE i<n DO 
		SELECT (ID) FROM MEMBER_TABLE LIMIT i,1 INTO member_id;
		INSERT INTO MONTHLY_PAYMENT_HISTORY (ID, yr)
        VALUES (member_id, newYear);
		SET i = i + 1;
	END WHILE;
END $$

DELIMITER ;

-- The event that automatically populates the MONTHLY_PAYMENT_HISTORY as New Year comes
CREATE EVENT populateMonthlyPaymentHistoryAtNewYear
ON SCHEDULE EVERY 1 YEAR
STARTS CONCAT(YEAR(CURDATE()) + 1, '-01-01')
DO 
CALL populateMonthlyPaymentHistory(YEAR(CURDATE()));

-- Triggers for Increasing or Decreasing the Total Amount of idir
DELIMITER $$

CREATE TRIGGER addOrSubtractTotalAmount AFTER INSERT ON RECEIPT FOR EACH ROW
BEGIN
	IF New.Money_Type = "Income" THEN
		UPDATE IDIR_TABLE
		SET Total_Amount = Total_Amount + New.Amount;
	ELSE 
		UPDATE IDIR_TABLE
		SET Total_Amount = Total_Amount - New.Amount;
	END IF;
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER addOrSubtractTotalAmountAfterDelete AFTER UPDATE ON RECEIPT FOR EACH ROW
BEGIN
	IF New.Deleted <> "NO" THEN
		IF New.Money_Type = "Income" THEN
			UPDATE IDIR_TABLE
			SET Total_Amount = Total_Amount - New.Amount;
		ELSE 
			UPDATE IDIR_TABLE
			SET Total_Amount = Total_Amount + New.Amount;
		END IF;
	END IF;
END $$

DELIMITER ;
