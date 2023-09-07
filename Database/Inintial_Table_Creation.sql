CREATE SCHEMA IDIR;
USE IDIR;

CREATE TABLE MEMBER_TABLE (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    First_Name VARCHAR(20) NOT NULL,
    Father_Name VARCHAR(20) NOT NULL,
    Grandfather_Name VARCHAR(20) NOT NULL,
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
    Total_Amount INT,
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
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Vice_Chairman)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Secretary)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Accountant)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Money_Holder)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Money_Collector)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Property_Buyer)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Shift_Supervisor_1)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Shift_Supervisor_2)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Shift_Supervisor_3)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Auditor_1)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Auditor_2)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE
);

CREATE TABLE PROPERTY (
    Property_Type VARCHAR(15) PRIMARY KEY,
    Number_Of_Items INT,
    Individual_Price DECIMAL(6 , 2 )
);

CREATE TABLE RECEIPT (
    Receipt_No INT AUTO_INCREMENT PRIMARY KEY,
    Issued_Date DATE NOT NULL,
    Issued_For INT NOT NULL,
    Issued_By INT NOT NULL,
    Reason_for_Payment VARCHAR(50) NOT NULL,
    Amount DECIMAL(4 , 2 ) NOT NULL,
    Type VARCHAR(12),
    Deleted VARCHAR(100) DEFAULT 'NO',
    FOREIGN KEY (Issued_For)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    FOREIGN KEY (Issued_By)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE
);

CREATE TABLE FAMILY (
    First_Name VARCHAR(20) NOT NULL,
    Father_Name VARCHAR(20) NOT NULL,
    Grandfather_Name VARCHAR(20) NOT NULL,
    Relationship VARCHAR(20) NOT NULL,
    Member_ID INT NOT NULL,
    Phone_No CHAR(10) UNIQUE,
    FOREIGN KEY (Member_ID)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE,
    UNIQUE (First_Name , Father_Name , Grandfather_Name)
);
  
CREATE TABLE AGENDA (
    Agenda_No INT AUTO_INCREMENT PRIMARY KEY,
    Written_Date DATE,
    Title VARCHAR(20),
    Writer INT NOT NULL,
    Written_Text VARCHAR(255),
    FOREIGN KEY (Writer)
        REFERENCES MEMBER_TABLE (ID)
        ON UPDATE CASCADE
);

CREATE TABLE MONTHLY_PAYMENT_HISTORY (
    ID INT,
    Year CHAR(4),
    Jan INT,
    Feb INT,
    Mar INT,
    Apr INT,
    May INT,
    Jun INT,
    Jul INT,
    Aug INT,
    Sep INT,
    Oct INT,
    Nov INT,
    `Dec` INT,
    FOREIGN KEY (ID)
        REFERENCES MEMBER_TABLE (ID),
    FOREIGN KEY (Jan)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Feb)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Mar)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Apr)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (May)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Jun)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Jul)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Aug)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Sep)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Oct)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (Nov)
        REFERENCES RECEIPT (Receipt_No),
    FOREIGN KEY (`Dec`)
        REFERENCES RECEIPT (Receipt_No),
    PRIMARY KEY (ID , Year)
);
        
DELIMITER $$

CREATE TRIGGER MEMBER_DELETE BEFORE DELETE ON MEMBER_TABLE FOR EACH ROW
BEGIN
  SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "You can't delete members"; 
END $$

DELIMITER ;
