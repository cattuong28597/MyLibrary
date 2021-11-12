package service;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookServiceImpl implements BookService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/myLibrary?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String SHOW_ALL_BOOK = "SELECT * FROM Books";
    private static final String SHOW_BOOK_BY_ID = "SELECT IdBook, BookName, Kind, Author, Quantity FROM Books WHERE IdBook = ?";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM Books WHERE IdBook = ?";
    private static final String INSERT_BOOK_SQL =  "INSERT INTO Books(IdBook, BookName, Kind, Author, Quantity) " +
            "VALUES(?, ?, ?, ?, ?)";

    public BookServiceImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                String name = rs.getString("BookName");
                String kind = rs.getString("Kind");
                String author = rs.getString("Author");
                int quantity = rs.getInt("Quantity");
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

    @Override
    public void createBook(Book book) {
        System.out.println(INSERT_BOOK_SQL);
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_SQL);
            statement.setString(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getKind());
            statement.setString(4, book.getAuthor());
            statement.setInt(5, book.getQuantity());
            System.out.println(statement);
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCheckId(String id) {
        boolean checkId = false;
        List<Book> books = showAllBooks();
        for (Book b: books) {
            if (b.getId().equals(id)) {
                checkId = true;
                break;
            }
        }
        return checkId;
    }

    @Override
    public boolean isFormatId(String id) {
        String REGEXT_ID = "^[A-Z]{2}[0-9]{3}$";
        Pattern pattern = Pattern.compile(REGEXT_ID);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
}
