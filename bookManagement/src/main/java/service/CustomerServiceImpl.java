package service;

import com.sun.javafx.scene.layout.region.Margins;
import model.Book;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private String jdbcURL = "jdbc:mysql://localhost:3306/myLibrary?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String SHOW_ALL_CUSTOMER = "SELECT borrower.*,book.BookName FROM mylibrary.books book INNER JOIN mylibrary.borrowers borrower ON book.IdBook = borrower.IdBook";
    private static final String INSERT_CUSTOMER = "CALL spLendBook(?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CUSTOMER = "CALL spGiveBack(?)";

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
    public List<Customer> showCustomers() {
        List<Customer> listCustomers = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SHOW_ALL_CUSTOMER);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("IdBorrower");
                String nameCustomer = rs.getString("BorrowerName");
                String address = rs.getString("Address");
                String phone = rs.getString("PhoneNumber");
                String mail = rs.getString("Email");
                String nameBook = rs.getString("BookName");
                int amount = rs.getInt("Amount");
                listCustomers.add(new Customer(id, nameCustomer,address,phone,mail,nameBook,amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCustomers;
    }

    @Override
    public void createCustomer(Customer customer) {
        System.out.println(INSERT_CUSTOMER);
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPhoneNumber());
            statement.setString(4, customer.getMail());
            statement.setString(5, customer.getIdBook());
            statement.setInt(6, customer.getAmount());
            System.out.println(statement);
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int idBorrow) {
        System.out.println(DELETE_CUSTOMER);
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);
            statement.setInt(1, idBorrow);
            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> searchCustomer(String symSearch) {
        List<Customer> customerList = showCustomers();
        List<Customer> listSearchCustomer = new ArrayList<>();
        symSearch = symSearch.toLowerCase();

        for (Customer c: customerList) {
            if (c.getName().toLowerCase().contains(symSearch)) {
                listSearchCustomer.add(c);
            }
        }

//        for (Customer c: customerList) {
//            if (c.getId() == s) {
//                listSearchCustomer.add(c);
//            }
//        }

//        switch (value){
//            case "Book":
//                for (Customer c: customerList) {
//                    if (c.getNameBook().toLowerCase().contains(symSearch)) {
//                        listSearchCustomer.add(c);
//                    }
//                }
//                break;
//            case "Customer":
//                for (Customer c: customerList) {
//                    if (c.getName().toLowerCase().contains(symSearch)) {
//                        listSearchCustomer.add(c);
//                    }
//                }
//                break;
//            default:
//                for (Customer c: customerList) {
//                    if (c.getPhoneNumber().toLowerCase().contains(symSearch)) {
//                        listSearchCustomer.add(c);
//                    }
//                }
//        }
        return listSearchCustomer;
    }
}
