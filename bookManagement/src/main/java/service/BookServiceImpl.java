package service;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/myLibrary?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String SHOW_ALL_BOOK = "SELECT * FROM Books";
    private static final String SHOW_BOOK_BY_ID = "SELECT IdBook, BookName, Kind, Author, Quantity FROM Book WHERE IdBook = ?";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM Books WHERE IdBook = ?";

    public BookServiceImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Book> showAllBooks() {
       List<Book> listBook = new ArrayList<>();

       try {
           Connection connection = getConnection();
           PreparedStatement statement = connection.prepareStatement(SHOW_ALL_BOOK);
           System.out.println(statement);
           ResultSet rs = statement.executeQuery();

           while (rs.next()) {
               String id = rs.getString("IdBook");
               String name = rs.getString("BookName");
               String kind = rs.getString("Kind");
               String author = rs.getString("Author");
               int quantity = rs.getInt("Quantity");
               listBook.add(new Book(id, name, kind, author, quantity));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return listBook;
    }

    @Override
    public Book findById(String id) {
        Book book = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SHOW_BOOK_BY_ID);
            statement.setString(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("");
                String kind = rs.getString("");
                String author = rs.getString("");
                int quantity = rs.getInt("");
                book = new Book(id, name, kind, author, quantity);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void deleteBook(String id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_BY_ID);
            statement.setString(1, id);
            statement.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
