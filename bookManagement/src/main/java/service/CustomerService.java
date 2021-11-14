package service;

import model.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> showCustomers();

    public void createCustomer(Customer customer);

    public void deleteCustomer(int idBorrow);

    public List<Customer> searchCustomer(String value, String symSearch);
}
