SELECT * FROM mylibrary.borrowers;

delete from mylibrary.borrowers;

ALTER TABLE mylibrary.borrowers AUTO_INCREMENT = 1;

DELIMITER //
    CREATE PROCEDURE spLendBook
    (
	IN BorrowerName1 VARCHAR(220),
	IN Address1 VARCHAR(220),
	IN PhoneNumber1 VARCHAR(15),
	IN Email1 VARCHAR(40),
	IN IdBook1 VARCHAR(50),
	IN Amount1 int
    )
    BEGIN
		DECLARE rowCheck INT DEFAULT 0;
		SELECT COUNT(*) INTO rowCheck FROM mylibrary.borrowers WHERE BorrowerName = BorrowerName1 AND Address = Address1 AND PhoneNumber = PhoneNumber1 AND Email = Email1 AND 
        IdBook = IdBook1;
		IF rowCheck >= 1 THEN
			update mylibrary.borrowers set Amount = Amount + Amount1 where IdBook = IdBook1;
            update mylibrary.books set Quantity = Quantity - Amount1 where IdBook = IdBook1;
		ELSE
			INSERT INTO mylibrary.borrowers(BorrowerName, Address, PhoneNumber, Email, IdBook,Amount) VALUES(BorrowerName1, Address1, PhoneNumber1, Email1, IdBook1, Amount1);
            update mylibrary.books set Quantity = Quantity - Amount1 where IdBook = IdBook1;
		END IF;
    END//
    DELIMITER ;
    
  -- DROP procedure spLendBook;
    
    CALL spLendBook('Echo Levine 1', 'Qui dolore excepteur', '012321323','Nulla voluptates dol','CL001',1);
    
    
    DELIMITER //
    CREATE PROCEDURE spGiveBack
    (
	IN IdBorrower1 INT
    )
    BEGIN
        DECLARE amount1 INT DEFAULT 0;
        DECLARE IdBook1 VARCHAR(50);
        SELECT Amount INTO amount1 FROM mylibrary.borrowers WHERE IdBorrower = IdBorrower1;
        SELECT IdBook INTO IdBook1 FROM mylibrary.borrowers WHERE IdBorrower = IdBorrower1;
        update mylibrary.books set Quantity = Quantity + amount1 where IdBook = IdBook1;
		delete from mylibrary.borrowers where IdBorrower = IdBorrower1;
        ALTER TABLE mylibrary.borrowers AUTO_INCREMENT = 1;
    END//
    DELIMITER ;
    
     -- DROP procedure spGiveBack;
    
    call spGiveBack(1);
    
    SELECT Amount FROM mylibrary.borrowers WHERE IdBorrower = 1;
    SELECT IdBook FROM mylibrary.borrowers WHERE IdBorrower = 1;
    