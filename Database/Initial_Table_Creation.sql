CREATE SCHEMA IDIR;
USE IDIR;

CREATE TABLE MEMBER_TABLE (
    Member_ID CHAR(3) PRIMARY KEY,
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
    Chairman CHAR(3),
    Vice_Chairman CHAR(3),
    Secretary CHAR(3),
    Math_Personnel CHAR(3),
    Main_Money_Holder CHAR(3),
    Daily_Money_Collector CHAR(3),
    Property_Buyer CHAR(3),
    Shift_Supervisor1 CHAR(3),
    Shift_Supervisor2 CHAR(3),
    Shift_Supervisor3 CHAR(3),
    Auditor1 CHAR(3),
    Auditor2 CHAR(3),
    Starting_Date DATE,
    Rules_And_Regulations VARCHAR(100),
    FOREIGN KEY (Chairman)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Vice_Chairman)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Secretary)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Math_Personnel)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Main_Money_Holder)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Daily_Money_Collector)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Property_Buyer)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Shift_Supervisor1)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Shift_Supervisor2)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Shift_Supervisor3)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Auditor1)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Auditor2)
        REFERENCES MEMBER_TABLE (Member_ID)
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
    Payer_ID CHAR(3) NOT NULL,
    Money_Receiver CHAR(3) NOT NULL,
    Reason_for_Payment VARCHAR(30),
    Amount DECIMAL(4 , 2 ) NOT NULL,
    FOREIGN KEY (Payer_ID)
        REFERENCES MEMBER_TABLE (Member_ID),
    FOREIGN KEY (Money_Receiver)
        REFERENCES MEMBER_TABLE (Member_ID)
);

CREATE TABLE FAMILY (
    First_Name VARCHAR(15) NOT NULL,
    Father_Name VARCHAR(15) NOT NULL,
    Grandfather_Name VARCHAR(15) NOT NULL,
    Relationship VARCHAR(20),
    Member_ID CHAR(3),
    Phone_No CHAR(10) UNIQUE,
    FOREIGN KEY (Member_ID)
        REFERENCES MEMBER_TABLE (Member_ID),
    UNIQUE (First_Name , Father_Name , Grandfather_Name)
);
  
CREATE TABLE AGENDA (
    Agenda_No INT PRIMARY KEY,
    Written_Date DATE,
    Title VARCHAR(20),
    Writer CHAR(3),
    Written_Text VARCHAR(100),
    FOREIGN KEY (Writer)
        REFERENCES MEMBER_TABLE (Member_ID)
);

CREATE VIEW PAYMENT_HISTORY AS
    SELECT 
        M.Member_ID, R.Receipt_No, R.Reason_for_Payment
    FROM
        MEMBER_TABLE AS M,
        RECEIPT AS R
    WHERE
        M.Member_ID = R.Payer_ID;