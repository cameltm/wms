package com.camel.wms.service;

import com.bsuir.WarehouseManagementSystem.model.Order;
import com.bsuir.WarehouseManagementSystem.model.Product;
import com.bsuir.WarehouseManagementSystem.model.User;
import com.bsuir.WarehouseManagementSystem.repository.OrderRepository;
import com.bsuir.WarehouseManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public void save(Order order){
        orderRepository.save(order);
    }

    public void saveNewOrder(Order order, User user, Long selectProductId){
        Product product = productRepository.findById(selectProductId).orElseThrow();
        order.setProduct(product);
        order.setUser(user);
        order.setStatus("Обрабатывается");
        orderRepository.save(order);
    }

    public Order findById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow();
    }

    public List<Order> getUserOrders(Long userId){
        return orderRepository.getOrders(userId);
    }

    public List<Order> getAll(){
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllUnchecked(){
        return orderRepository.getAllUnchecked();
    }

    public Order getOrderById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow();
    }

    public void removeOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }
}
