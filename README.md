# IDIR
Local Idir managing application based on basic java and database SQL

Goals
1.	The program will try to achieve the ease access of the Idir system which is exercised locally in Ethiopia.
Traditionally the system and all the data are managed through multiple hand-written large record books. Which can lead to repeated data, multiple unnecessary record books which get full every time $ must be replaced with new ones continuously which also may cause loss of data. As well as it is not very efficient for accessing and retrieving any information.
Thus our program will try to overcome these problems. Basically what our program:-
•	Have an interactive GUI that can easily be understood and used by the wide society.
•	Have a database for the data of the members of the Idir and can easily retrieve, manipulate, access and add information in the database
2.	We will try to work on it this summer and finalize it by the end of this year or if takes long as far as meskel. 
And we will use Java for the application program with java swing for the GUI and SQl for the database (probably with MySQL) and also will work on through GitHub for version control and collaboration. 

End Users
The program is designed to be used by many Idirs from small to large based on their respective needs.
The end user are the ones who are at the writing desk of Idir houses who receive monthly payments from members and give compassionate (ማስተዛዘኛ) money for the affected, and for those who manage the Idir’s properties like tents, chairs, tables, etc.
Also the persons who audit, calculate incomes and spending (ሂሣብ ያዥ)
*(other roles in the Idir to be identified)

Plan – Steps 
Front end
i.	First we start off by building the basic skeletal structure of the application program by using java 
ii.	Identifying what the objects in the GUI of the program do and how the function (1st week)

Back end
iii.	Identifying the entities in the Idir system and build based on the relational database model and build everything from identifying entities to making ER-Diagrams to mapping to relational tables (2nd week)
iv.	After making the theoretical work of the database then build the database by using SQL in MySQL database management system.
(Which we have to learn from scratch btw) (3th-4th week)
v.	Connecting the java program with the database making the objects in the GUI function properly and effectively and finalizing. (5th week)
vi.	Testing (6th week)

Conceptual Design
The Idir is organized of many members and official selected from those members and the idir also have many properties like offices, tents, chairs, etc.  
This Idir has 5 entities
i.	MEMBER
An entity type MEMBER with attributes 
•	Full name – Composite (first name, father’s name, grandfather’s name), not null
•	Member ID – primary key, a number assigned for members in order of their joining of the idir starting from the first member as 1 and automatically increased as more members join
•	Address – composite (city, wereda, kebele, house No) 
•	Phone No – atomic the cell phone number of the member
•	Age - atomic
•	Occupation – atomic can be null
•	Religion – atomic

ii.	THE IDIR (the organization itself)
The organization itself has many attributes thus we take it as an entity itself.
•	Official name – the name of the Idir in establishment
Atomic and not null but unique from any other. Primary Key
•	Bank account No- an account no that the idir uses to store the money collected.
•	Office Address – the address where the idir basically resides and where the money is collected and many more
•	Store Address – the address where the properties of the idir are stored 
•	Chairman – the member ID of the person who serves as a chairman of the idir. The chairman is the person who oversees and represents the idir as whole
•	Vice Chairman - the member ID of the person who serves as a vice chairman. This person serves as supportive of the chairman and as placeholder in case of the absence of the chairman
•	Secretary - the member ID of the person who serves as secretary. The secretary is the one who prepares meeting titles and takes notes on these meetings and reports. 
•	Math personnel (ሂሣብ ሹም) - the member ID of the person who serves as math personnel. This person controls all income receipts and all money movements
•	Main money holder - the member ID of the person who serves as money holder. This person receives money from the math personnel and stores it in the Idir’s bank account.
•	Daily money collector - the member ID of the person who serves as money collector. This person collects money from members in monthly or other payments and gives the money to the money holder through the math personnel 
•	Property buyer - the member ID of the person who serves as property holder. This person buys things that the Idir needs
•	Shift supervisors – multivalued - the member IDs of the persons who serves as shift supervisors. These persons who supervise funerals and many arrangements in the funeral and other ceremonies
•	Auditors – multivalued - the member IDs of the persons who serves as auditors. These persons are the ones who audit the workings of the other officials and mainly the money income/outcome of the idir at least twice a year
•	Starting date - current officials starting date
All the attributes from chairman down are foreign keys from the MEMBER entity
iii.	PROPERTY	
•	Type – primary key the type of the property like chair, table, tent etc.
•	Number of items – the quantity of the item – Derived attribute

iv.	RECEIPT 
•	Receipt No – primary key - no generated from the 1st receipt as 1 and continuously increased
•	Date – the date of receipt issued
•	Payer ID – the member ID of the payer - foreign key
•	Reason for payment – the reason for payment in less than 3 words
•	Amount – the amount of money paid 

v.	DEPENDENT
The dependents of the member where the member gets compassion money in case one of the dependents decease. A weak entity
•	Full name – composite – the name of the dependent. Partial key
•	Relationship – the relationship the dependent have with the member (Mother, Father, Child or Siblings and include in-laws or spouse Mother, Father or Siblings)
•	Member ID – the member in which it depends on – foreign key
•	Address – the address of the dependent
•	Age – age of the dependent 
Relationships
There are relationships
i.	SERVES – a relationship between a MEMBER and IDIR where the member serves as some official.
This relationship has an attribute - Role Name – which specifies the role the member plays in the Idir in the SERVES relationship.
The relationship is created by importing the primary key of the MEMBER - Member ID – in to the IDIR entity
ii.	HAS_DEPENDENT – a relationship between MEMBER and DEPENDENT where the dependents are dependent on the member. 
The relationship is created by importing the primary key of the MEMBER - Member ID – in to the DEPENDENT entity
iii.	PAYES – a relationship between MEMBER and RECEIPT where the member pays the money issued in the receipt 
The relationship is created by importing the primary key of the MEMBER - Member ID – in to the RECEIPT entity.
Relationship Constraints
i.	Cardinality ratio – MEMBER:IDIR – 1:1
Participation constraint – MEMBER participation is partial while the IDIR is total participation
ii.	Cardinality ratio – MEMBER:DEPENDENT – 1:N
Participation constraint – both entities participation is total.
iii.	Cardinality ratio – MEMBER:RECEIPT – 1:N 
Participation constraint – both entities participation is total.
