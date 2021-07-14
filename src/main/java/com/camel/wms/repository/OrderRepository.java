package com.camel.wms.repository;

import com.camel.wms.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT * FROM Orders WHERE user_id = :user_id", nativeQuery = true)
    List<Order> getOrders(@Param("user_id") Long user_id);

    @Query(value = "SELECT * FROM Orders WHERE status='Обрабатывается'", nativeQuery = true)
    List<Order> getAllUnchecked();
}
