package service;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    public List<Book> showAllBooks();

    public Book findById(String id);

    public void deleteBook(String id);

    public void createBook(Book book);

    public boolean isCheckId(String id);

    public boolean isFormatId(String id);

    public List<Book> searchBook(String value, String symSearch) throws SQLException;

    void edit(Book book);
}
