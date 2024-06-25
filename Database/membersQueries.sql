-- for the member list
DELIMITER $$

CREATE PROCEDURE retrieveListOfMembers()
BEGIN
    SELECT LPAD(ID, 4, '0') AS Formatted_ID, CONCAT(First_Name, " ", Father_Name, " ", Grandfather_Name) as FullName, Gender, Age, Religion, Member_Address, Phone_No,Occupation
    FROM MEMBER_TABLE;
END$$

DELIMITER ;


-- for adding a member 
DELIMITER $$

CREATE PROCEDURE addMember(
    IN FullName VARCHAR(100),
    IN Member_Address VARCHAR(30),
    IN Phone_No CHAR(10),
    IN Age INT,
    IN Gender VARCHAR(6),
    IN Occupation VARCHAR(20),
    IN Religion VARCHAR(15),
    IN Photo VARCHAR(255)
)
BEGIN
    DECLARE First_Name VARCHAR(20);
    DECLARE Father_Name VARCHAR(20);
    DECLARE Grandfather_Name VARCHAR(20);

    SET First_Name = TRIM(SUBSTRING_INDEX(FullName, ' ', 1));
    SET Father_Name = TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(FullName, ' ', -2), ' ', 1));
    SET Grandfather_Name = TRIM(SUBSTRING_INDEX(FullName, ' ', -1));

    INSERT INTO MEMBER_TABLE (First_Name, Father_Name, Grandfather_Name, Member_Address, Phone_No, Age, Gender, Occupation, Religion, Photo)
    VALUES (First_Name, Father_Name, Grandfather_Name, Member_Address, Phone_No, Age, Gender, Occupation, Religion, Photo);
END $$

DELIMITER ;

-- for retrieving individual member
DELIMITER $$

CREATE PROCEDURE retrieveMember(IN ID INT)
BEGIN
    SELECT LPAD(ID, 4, '0'), Gender, Age, Religion, Member_Address, Phone_No, Occupation, Photo, CONCAT(First_Name, " ", Father_Name, " ", Grandfather_Name) as FullName
FROM
    MEMBER_TABLE AS m
WHERE
    m.ID = ID;

END $$

DELIMITER ;

-- for updating member
DELIMITER $$

CREATE PROCEDURE updateMember(
    IN mID INT,
    IN newMember_Address VARCHAR(30),
    IN newPhone_No CHAR(10),
    IN newAge INT,
    IN newGender VARCHAR(6),
    IN newOccupation VARCHAR(20),
    IN newReligion VARCHAR(15)
)
BEGIN
    UPDATE MEMBER_TABLE
    SET
        Member_Address = newMember_Address,
        Phone_No = newPhone_No,
        Age = newAge,
        Gender = newGender,
        Occupation = newOccupation,
        Religion = newReligion
    WHERE
        ID = mID;
END $$

DELIMITER ;


-- for retrieving a member families

DELIMITER$$

CREATE PROCEDURE retrieveFamily(in ID INT)
BEGIN 
    SELECT CONCAT(F.First_Name, F.Father_Name, F.Grandfather_Name) AS F_FullName,
    F.Relationship AS Family_Relationship,
    F.Phone_No AS Family_Phone_No
FROM
    MEMBER_TABLE AS m

WHERE
    m.ID = ID;

END $$

DELIMITER ;

-- for inserting family member
DELIMITER $$

CREATE PROCEDURE addFamily(
    IN Member_ID INT,
    IN FullName VARCHAR(100),
    IN Relationship VARCHAR(20),
    IN Phone_No CHAR(10)
)
BEGIN
    DECLARE First_Name VARCHAR(20);
    DECLARE Father_Name VARCHAR(20);
    DECLARE Grandfather_Name VARCHAR(20);

    SET First_Name = TRIM(SUBSTRING_INDEX(FullName, ' ', 1));
    SET Father_Name = TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(FullName, ' ', -2), ' ', 1));
    SET Grandfather_Name = TRIM(SUBSTRING_INDEX(FullName, ' ', -1));

    INSERT INTO FAMILY (
        First_Name,
        Father_Name,
        Grandfather_Name,
        Relationship,
        Member_ID,
        Phone_No
    )
    VALUES (First_Name, Father_Name, Grandfather_Name, Relationship, Member_ID, Phone_No);
END $$

DELIMITER ;


-- for retrieving officials   
DELIMITER $$

CREATE PROCEDURE retrieveOfficials()
BEGIN
    DECLARE position VARCHAR(50);

    SELECT 
        m.Photo, LPAD(m.ID, 4, '0') AS Official_ID, 
        CONCAT(m.First_Name, ' ', m.Father_Name, ' ', m.Grandfather_Name) AS FullName,
        CASE
            WHEN i.Chairman = m.ID THEN 'Chairman'
            WHEN i.Vice_Chairman = m.ID THEN 'Vice_Chairman'
            WHEN i.Secretary = m.ID THEN 'Secretary'
            WHEN i.Accountant = m.ID THEN 'Accountant'
            WHEN i.Money_Holder = m.ID THEN 'Money_Holder'
            WHEN i.Money_Collector = m.ID THEN 'Money_Collector'
            WHEN i.Property_Buyer = m.ID THEN 'Property_Buyer'
            WHEN i.Shift_Supervisor_1 = m.ID THEN 'Shift_Supervisor'
            WHEN i.Shift_Supervisor_2 = m.ID THEN 'Shift_Supervisor'
            WHEN i.Shift_Supervisor_3 = m.ID THEN 'Shift_Supervisor'
            WHEN i.Auditor_1 = m.ID THEN 'Auditor'
            WHEN i.Auditor_2 = m.ID THEN 'Auditor'
        END AS Position
    FROM 
        IDIR_TABLE AS i
    LEFT JOIN 
        MEMBER_TABLE AS m
    ON  
        m.ID IN (i.Chairman, i.Vice_Chairman, i.Secretary, i.Accountant, i.Money_Holder, i.Money_Collector,
                 i.Property_Buyer, i.Shift_Supervisor_1, i.Shift_Supervisor_2, i.Shift_Supervisor_3,
                 i.Auditor_1, i.Auditor_2);
END $$

DELIMITER ;


-- for updating officials
DELIMITER $$

CREATE PROCEDURE UpdateOfficials(
    IN u_Official_Name VARCHAR(50),
    IN newChairman INT,
    IN newVice_Chairman INT, 
    IN newSecretary INT,
    IN newAccountant INT,
    IN newMoney_Holder INT,
    IN newMoney_Collector INT,
    IN newProperty_Buyer INT, 
    IN newShift_Supervisor_1 INT,
    IN newShift_Supervisor_2 INT,
    IN newShift_Supervisor_3 INT,
    IN newAuditor_1 INT,
    IN newAuditor_2 INT
)
BEGIN 
    UPDATE IDIR_TABLE
    SET 
        Chairman = newChairman,
        Vice_Chairman = newVice_Chairman, 
        Secretary = newSecretary,
        Accountant = newAccountant,
        Money_Holder = newMoney_Holder,
        Money_Collector = newMoney_Collector,
        Property_Buyer = newProperty_Buyer, 
        Shift_Supervisor_1 = newShift_Supervisor_1,
        Shift_Supervisor_2 = newShift_Supervisor_2,
        Shift_Supervisor_3 = newShift_Supervisor_3,
        Auditor_1 = newAuditor_1,
        Auditor_2 = newAuditor_2
    WHERE Official_Name = u_Official_Name;
END $$

DELIMITER ;

-- for retrieving agenda list
select LPAD(Agenda_No, 4, '0') AS Agenda_No, Written_Date , Title,  CONCAT(m.First_Name, ' ', m.Father_Name, ' ', m.Grandfather_Name) AS Writer
from AGENDA AS a LEFT JOIN MEMBER_TABLE AS m
ON a.Writer = m.ID


-- for retrieving individual Agenda

DELIMITER $$

CREATE PROCEDURE retrievingIndividualAgenda(IN num INT)
BEGIN 
    select LPAD(Agenda_No, 4, '0') AS Agenda_No, Written_Date , Title,  CONCAT(m.First_Name, ' ', m.Father_Name, ' ', m.Grandfather_Name) AS Writer, Written_Text
    from AGENDA AS a LEFT JOIN MEMBER_TABLE AS m
    ON a.Writer = m.ID
    WHERE a.Agenda_No = Num;
END $$

DELIMITER ;
