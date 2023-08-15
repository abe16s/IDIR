CREATE SCHEMA IDIR;
USE IDIR;

CREATE TABLE MEMBER_TABLE (
    Member_ID INT PRIMARY KEY,
    First_Name VARCHAR(15) NOT NULL,
    Father_Name VARCHAR(15) NOT NULL,
    Grandfather_Name VARCHAR(15) NOT NULL,
    Address VARCHAR(30) NOT NULL,
    Phone_No CHAR(10) UNIQUE,
    Age INT,
    Occupation VARCHAR(20),
    Religion VARCHAR(15),
    Photo VARCHAR(100) UNIQUE
);

CREATE TABLE IDIR_TABLE (
    Official_Name VARCHAR(50) PRIMARY KEY,
    Office_Address VARCHAR(30) NOT NULL,
    Store_Address VARCHAR(30),
    Bank_Account_No VARCHAR(15) NOT NULL,
    Chairman INT,
    Vice_Chairman INT,
    Secretary INT,
    Math_Personnel INT,
    Main_Money_Holder INT,
    Daily_Money_Collector INT,
    Property_Buyer INT,
    Shift_Supervisor_1 INT,
    Shift_Supervisor_2 INT,
    Shift_Supervisor_3 INT,
    Auditor_1 INT,
    Auditor_2 INT,
    Starting_Date DATE,
    Rules_And_Regulations VARCHAR(100),
    FOREIGN KEY (Chairman)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Vice_Chairman)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Secretary)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Math_Personnel)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Main_Money_Holder)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Daily_Money_Collector)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Property_Buyer)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Shift_Supervisor1)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Shift_Supervisor2)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Shift_Supervisor3)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Auditor1)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Auditor2)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE PROPERTY (
    Property_Type VARCHAR(15) PRIMARY KEY,
    Number_Of_Items INT,
    Number_Of_Items_In_Store INT,
    Individual_Price DECIMAL(6 , 2 )
);

CREATE TABLE RECEIPT (
    Receipt_No VARCHAR(6) PRIMARY KEY,
    Issued_Date DATE NOT NULL,
    Payer_ID INT NOT NULL,
    Money_Receiver INT NOT NULL,
    Reason_for_Payment VARCHAR(30),
    Amount DECIMAL(4 , 2 ) NOT NULL,
    FOREIGN KEY (Payer_ID)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (Money_Receiver)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE FAMILY (
    First_Name VARCHAR(15) NOT NULL,
    Father_Name VARCHAR(15) NOT NULL,
    Grandfather_Name VARCHAR(15) NOT NULL,
    Relationship VARCHAR(20),
    Member_ID INT,
    Phone_No CHAR(10) UNIQUE,
    FOREIGN KEY (Member_ID)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    UNIQUE (First_Name , Father_Name , Grandfather_Name)
);
  
CREATE TABLE AGENDA (
    Agenda_No INT PRIMARY KEY,
    Written_Date DATE,
    Title VARCHAR(20),
    Writer INT,
    Written_Text VARCHAR(100),
    FOREIGN KEY (Writer)
        REFERENCES MEMBER_TABLE (Member_ID)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE VIEW PAYMENT_HISTORY AS
    SELECT 
        M.Member_ID, R.Receipt_No, R.Reason_for_Payment
    FROM
        MEMBER_TABLE AS M,
        RECEIPT AS R
    WHERE
        M.Member_ID = R.Payer_ID;