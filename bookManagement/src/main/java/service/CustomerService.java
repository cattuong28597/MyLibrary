package service;

import model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> showCustomers();

    void createCustomer(Customer customer);

    void deleteCustomer(int idBorrow);

    List<Customer> searchCustomer(String symSearch);
}
