package com.camel.wms.repository;

import com.bsuir.WarehouseManagementSystem.model.Box;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoxRepository extends CrudRepository<Box, Long> {
    List<Box> findAll();
    Box getBoxById(Long id);

    @Query(value = "SELECT * FROM Boxes JOIN products ON products.id = boxes.product_id",nativeQuery = true)
    List<Box> getAllBoxes();

    @Query(value = "SELECT position_id AS positionId, COUNT(position_id) AS boxesAmount FROM Boxes WHERE product_id = :product_id GROUP BY position_id", nativeQuery = true)
    List<BoxGetters> getBoxesAndPositionId(@Param("product_id") Long productId);

    @Query(value = "SELECT id AS boxId FROM Boxes WHERE product_id = :product_id AND fullness!=capacity", nativeQuery = true)
    List<BoxGetters> getUncompletedBoxesByProductId(@Param("product_id") Long productId);

    @Query(value = "SELECT SUM(capacity) - SUM(fullness) FROM Boxes WHERE product_id = :product_id AND fullness!=capacity",nativeQuery = true)
    Integer getUncompletedPlacesInBoxesAmount(@Param("product_id") Long productId);

    @Query(value="SELECT * FROM Boxes where product_id = :product_id",nativeQuery = true)
    List<Box> getBoxIdByProduct(@Param("product_id") Long product_id);

    @Query(value = "SELECT SUM(capacity) FROM Boxes",nativeQuery = true)
    Integer getTotalCapacity();

    @Query(value = "SELECT SUM(fullness) FROM Boxes",nativeQuery = true)
    Integer getTotalFullness();

    @Query(value = "SELECT capacity FROM Boxes GROUP BY capacity",nativeQuery = true)
    Integer getBoxCapacity();

//    @Query(value = "UPDATE Boxes SET capacity = :capacity",nativeQuery = true)
//    void updateBoxCapacity(@Param("capacity") Integer capacity);

}
