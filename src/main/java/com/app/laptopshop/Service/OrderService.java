package com.app.laptopshop.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.laptopshop.Entity.Customer;
import com.app.laptopshop.Entity.Laptop;
import com.app.laptopshop.Entity.Order;
import com.app.laptopshop.Repository.OrderRepo;
import com.app.laptopshop.Repository.CustomerRepo;
import com.app.laptopshop.Repository.LaptopRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private LaptopRepo laptopRepo;

    public Order createOrder(Order order) {

        // Fetch actual entities from DB using the IDs passed
        Long customerId = order.getCustomer().getId();
        Long laptopId = order.getLaptop().getId();

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        Laptop laptop = laptopRepo.findById(laptopId)
                .orElseThrow(() -> new RuntimeException("Laptop not found with ID: " + laptopId));

        order.setCustomer(customer);
        order.setLaptop(laptop);

        return orderRepo.save(order);
    }

    public Order getOrder(Long id) {  // Renamed to 'id'
        return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order updateOrder(Long id, Order updatedOrder) {  // Renamed to 'id'
        Order existing = getOrder(id);
        existing.setStatus(updatedOrder.getStatus());
        existing.setOrderDate(updatedOrder.getOrderDate());
        existing.setTotalAmount(updatedOrder.getTotalAmount());
        return orderRepo.save(existing);
    }

    public void deleteOrder(Long id) {  // Renamed to 'id'
        orderRepo.deleteById(id);
    }

    public Page<Order> getAllOrders(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return orderRepo.findAll(pageable);
    }
}
