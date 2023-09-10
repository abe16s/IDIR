	-- Financial Page

-- retrieving the starting date of the year
SELECT 
    YEAR(Starting_Date)
FROM
    IDIR_TABLE;

-- retrieving the basic info of idir
SELECT 
    Official_Name, Bank_Acc_No, Total_Amount
FROM
    IDIR_TABLE;

-- retrieving the current month income and expendings
SELECT 
    Money_Type, SUM(Amount)
FROM
    RECEIPT
WHERE
    MONTH(Issued_Date) = MONTH(CURDATE())
        AND YEAR(Issued_Date) = YEAR(CURDATE())
        AND Deleted = 'NO'
GROUP BY Money_Type
ORDER BY Money_Type DESC;

-- retrieving the current month income and expendings
SELECT 
    Money_Type, SUM(Amount)
FROM
    RECEIPT
WHERE
    YEAR(Issued_Date) = YEAR(CURDATE())
        AND Deleted = 'NO'
GROUP BY Money_Type
ORDER BY Money_Type DESC;

-- retrieving properties
SELECT 
    Property_Type, Number_Of_Items, Individual_Price
FROM
    PROPERTY;

-- retrieving expenditures
SELECT 
    Issued_Date,
    LPAD(Receipt_No, 6, '0'),
    Amount,
    Reason_for_Payment
FROM
    Receipt
WHERE
    Money_Type = 'Expenditure'
        AND Deleted = 'NO'
ORDER BY Issued_Date DESC;

-- retrieving sum of income and expenditure based on year and month for the yearly report
SELECT 
    YEAR(Issued_Date),
    MONTH(Issued_Date),
    Money_Type,
    SUM(Amount)
FROM
    RECEIPT
WHERE
    Deleted = 'NO'
GROUP BY MONTH(Issued_Date) , YEAR(Issued_Date) , Money_Type;

	-- Payment History Page

-- retrieving the MONTHLY_PAYMENT_HISTORY table
SELECT 
    LPAD(ID, 4, '0'),
    Jan,
    Feb,
    Mar,
    Apr,
    May,
    Jun,
    Jul,
    Aug,
    Sep,
    Oct,
    Nov,
    `Dec`
FROM
    MONTHLY_PAYMENT_HISTORY
WHERE
    yr = yr;

-- retrieving receipt no for the MONTHLY_PAYMENT_HISTORY table in the java
SELECT 
    Receipt_No
FROM
    RECEIPT
WHERE
    Reason_for_Payment = 'Monthly Payment For month year'
        AND Issued_For = id
        AND Deleted = 'NO';

	-- Individual Receipt Page
	
-- retrieving receipt fi is Issued_for , fb is Issued_by 
SELECT 
    r.Issued_Date,
    LPAD(r.Receipt_No, 6, '0') AS Receipt_No,
    LPAD(r.Issued_For, 4, '0') AS Formatted_Issued_For,
    CONCAT(fi.First_Name,
            ' ',
            fi.Father_Name,
            ' ',
            fi.Grandfather_Name) AS Issuer_Name,
    r.Amount,
    r.Reason_for_Payment,
    r.Money_Type,
    LPAD(r.Issued_By, 4, '0') AS Formatted_Issued_By,
    CONCAT(fb.First_Name,
            ' ',
            fb.Father_Name,
            ' ',
            fb.Grandfather_Name) AS Signer_Name
FROM
    RECEIPT AS r
        JOIN
    MEMBER_TABLE AS fi ON r.Issued_For = fi.ID
        JOIN
    MEMBER_TABLE AS fb ON r.Issued_By = fb.ID
WHERE
    r.Receipt_No = rNo;

-- retrieving next available
SELECT 
    LPAD(MAX(Receipt_No) + 1, 6, '0') AS nextAvailableReceiptNo
FROM
    RECEIPT;

-- Inserting into receipt 
INSERT INTO RECEIPT (Issued_Date, Issued_For, Amount, Reason_For_Payment, Money_Type, Issued_By)
VALUES (curdate(), id, amt, "Reason", "Type", id);

-- ticking history table
UPDATE MONTHLY_PAYMENT_HISTORY 
SET 
    mon = amt
WHERE
    ID = id AND yr = yr;

-- increasing property's quantity and updating price
UPDATE PROPERTY 
SET 
    Number_Of_Items = Number_Of_Items + qty,
    individual_Price = prc
WHERE
    Property_Type = 'Type';

-- inserting into propert
INSERT INTO PROPERTY(Property_Type, Number_Of_Items, Individual_Price)
VALUES (ty, qty, prc);

-- for deleteing a receipt
UPDATE RECEIPT 
SET 
    Deleted = 'Date/Reason'
WHERE
    Receipt_No = rsn;

-- ticking back history table when receipt deleted
UPDATE MONTHLY_PAYMENT_HISTORY 
SET 
    mon = 0
WHERE
    ID = id AND yr = yr;

-- decreasing property's quantity when receipt deleted
UPDATE PROPERTY 
SET 
    Number_Of_Items = Number_Of_Items - qty
WHERE
    Property_Type = 'Type';
	
    -- Rules Page
-- retrieving the rules
SELECT 
    Rules_And_Regulations
FROM
    IDIR_TABLE;