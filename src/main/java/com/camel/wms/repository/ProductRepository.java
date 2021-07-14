package com.camel.wms.repository;

import com.bsuir.WarehouseManagementSystem.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    Product getByDescription(String description);

    @Query(value = "SELECT SUM(fullness) as quantity,product_id as productId FROM Boxes JOIN products ON products.id = boxes.product_id GROUP BY product_id",nativeQuery = true)
    List<ProductGetters> getAllProducts();

    @Query(value = "SELECT SUM(fullness) FROM Boxes where product_id = :product_id",nativeQuery = true)
    Integer getProductsQuantity(@Param(value = "product_id") Long product_id);
}
