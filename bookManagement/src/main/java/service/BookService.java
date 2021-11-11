package service;

import model.Book;

import java.util.List;

public interface BookService {

    public List<Book> showAllBooks();

    public Book findById(String id);

    public void deleteBook(String id);
}
