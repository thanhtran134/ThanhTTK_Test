package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();

        Customer customer1 = new Customer("Duke", "C001", "duke2@java.org");
        Customer customer2 = new Customer("Mike", "C002", "mike@java.org");
        Customer customer3 = new Customer("John", "C003", "duke@java.org");

        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        try {
            customerService.addCustomer(customer3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        for (Customer customer : customerService.getAllCustomers()) {
            System.out.println(customer);
        }
    }
}
