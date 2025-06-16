package com.app.laptopshop.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.laptopshop.Entity.Customer;
import com.app.laptopshop.Repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer getCustomer(Long id) {  // Renamed to 'id'
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {  // Renamed to 'id'
        Customer existing = getCustomer(id);
        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setAddress(updatedCustomer.getAddress());
        existing.setPhone(updatedCustomer.getPhone());
        return customerRepo.save(existing);
    }

    public void deleteCustomer(long id) {  // Renamed to 'id'
        customerRepo.deleteById(id);
    }
}
