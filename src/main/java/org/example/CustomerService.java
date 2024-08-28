package org.example;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        if (isEmailUnique(customer.getEmail())) {
            customers.add(customer);
        } else {
            throw new IllegalArgumentException("Email must be unique");
        }
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    private boolean isEmailUnique(String email) {
        return customers.stream().noneMatch(c -> c.getEmail().equals(email));
    }

}
