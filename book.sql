SELECT * FROM mylibrary.books;

SELECT borrower.*,book.BookName FROM mylibrary.books book INNER JOIN mylibrary.borrowers borrower ON book.IdBook = borrower.IdBook;

INSERT INTO mylibrary.borrowers(BorrowerName, Address, PhoneNumber, Email, IdBook) VALUES('Harding Ballard', 'Blanditiis iure opti', '+1 (642) 451-4616', 'Accusantium enim in ', 'CL001')