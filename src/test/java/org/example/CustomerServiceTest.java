package org.example;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomerWithUniqueEmail() {
        Customer customer = new Customer("Duke", "C001", "duke@java.org");
        when(customerRepository.isEmailUnique("duke@java.org")).thenReturn(Optional.of(customer));
        customerService.addCustomer(customer);
        assertEquals(1, customerService.getAllCustomers().size());
        assertEquals("Duke", customerService.getAllCustomers().get(0).getName());
    }

    @Test
    void testAddCustomerWithDuplicateEmailThrowsException() {
        Customer customer1 = new Customer("Duke", "C001", "duke@java.org");
        Customer customer2 = new Customer("Mike", "C002", "duke@java.org");
        when(customerRepository.isEmailUnique("duke@java.org")).thenReturn(Optional.of(customer1));
        customerService.addCustomer(customer1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.addCustomer(customer2);
        });

        assertEquals("Email must be unique", exception.getMessage());
    }

    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer("Duke", "C001", "duke@java.org");
        Customer customer2 = new Customer("Mike", "C002", "mike@java.org");

        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        assertEquals(2, customerService.getAllCustomers().size());
    }
}