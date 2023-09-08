-- Triggers for Increasing or Decreasing the Total Amount of idir

DELIMITER $$

CREATE TRIGGER addOrSubtractTotalAmount AFTER INSERT ON RECEIPT FOR EACH ROW
BEGIN
	IF New.Type = "Income" THEN
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
		IF New.Type = "Income" THEN
			UPDATE IDIR_TABLE
			SET Total_Amount = Total_Amount - New.Amount;
		ELSE 
			UPDATE IDIR_TABLE
			SET Total_Amount = Total_Amount + New.Amount;
		END IF;
	END IF;
END $$

DELIMITER ;

-- retrieving the basic info of idir
SELECT Official_Name, Bank_Acc_No, Total_Amount
From IDIR_TABLE;

-- retrieving the current month income and expendings
SELECT Type, Sum(Amount)
FROM RECEIPT
WHERE MONTH(Issued_Date) = MONTH(curdate()) AND Deleted = "NO"
GROUP BY Type
ORDER BY Type DESC;

-- retrieving the current year income and expendings
SELECT Type, Sum(Amount)
FROM RECEIPT
WHERE YEAR(Issued_Date) = YEAR(curdate()) AND Deleted = "NO"
GROUP BY Type
ORDER BY Type DESC;

-- retrieving properties
SELECT Property_Type, Number_Of_Items, Individual_Price
FROM PROPERTY;

-- retrieving expenditures
SELECT Issued_Date, LPAD(Receipt_No, 6, '0'), Amount, Reason_for_Payment
FROM Receipt
WHERE Type = "Expenditure" AND Deleted = "NO"
ORDER BY Issued_Date DESC;

-- retrieving sum of income and expenditure based on year and month for the yearly report
Select YEAR(Issued_Date), MONTH(Issued_Date), Type, SUM(Amount)
From RECEIPT
Where Deleted = "NO"
GROUP BY MONTH(Issued_Date), YEAR(Issued_Date), Type
ORDER BY YEAR(Issued_Date) DESC, MONTH(Issued_Date), Type DESC;

-- retrieving the MONTHLY_PAYMENT_HISTORY table
SELECT ID, Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, `Dec`
FROM MONTHLY_PAYMENT_HISTORY
WHERE Year = yr;

-- retrieving receipt no for the MONTHLY_PAYMENT_HISTORY table in the java
SELECT Receipt_No
FROM RECEIPT
WHERE Reason_for_Payment = "Monthly Payment For month year" AND Issued_For = id AND Deleted = "NO";

-- Inserting into receipt 
INSERT INTO RECEIPT (Issued_Date, Issued_For, Amount, Reason_For_Payment, Type, Issued_By)
VALUES (curdate(), id, amt, "Reason", "Type", id);

-- retrieving receipt As f is Issued_for , Issued_by is by
SELECT Issued_Date, LPAD(Issued_For, 4, '0'), CONCAT(f.First_Name, " ", f.Father_Name, " ", f.Grandfather_Name) AS Issuer_Name, Amount, Reason_For_Payment, Type, LPAD(Issued_By, 4, '0'), CONCAT(b.First_Name, " ", b.Father_Name, " ", b.Grandfather_Name) As Signer_Name
FROM RECEIPT, MEMBER_TABLE As f, MEMBER_TABLE As b
WHERE Receipt_No = rn AND f.ID = Issued_For AND b.ID = Issued_BY;

-- SELECT
--     r.Issued_Date,
--     LPAD(r.Issued_For, 4, '0') AS Formatted_Issued_For,
--     CONCAT(fi.First_Name, ' ', fi.Father_Name, ' ', fi.Grandfather_Name) AS Issuer_Name,
--     r.Amount,
--     r.Reason_for_Payment,
--     r.Type,
--     LPAD(r.Issued_By, 4, '0') AS Formatted_Issued_By,
--     CONCAT(fb.First_Name, ' ', fb.Father_Name, ' ', fb.Grandfather_Name) AS Signer_Name
-- FROM
--     RECEIPT AS r
-- JOIN
--     MEMBER_TABLE AS fi ON r.Issued_For = fi.ID
-- JOIN
--     MEMBER_TABLE AS fb ON r.Issued_By = fb.ID
-- WHERE
--     r.Receipt_No = rn;


-- for deleteing a receipt
UPDATE RECEIPT
SET Deleted = "Date/Reason"
WHERE Receipt_No = rn;

-- retrieving the rules
SELECT Rules_And_Regulations
FROM IDIR_TABLE;
