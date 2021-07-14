package com.camel.wms.model;


import javax.persistence.*;

@Entity
@Table(name = "Map")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "rows_amount")
    private Integer rowsAmount;
    @Column(name = "columns_amount")
    private Integer columnsAmount;

    public Map() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRowsAmount() {
        return rowsAmount;
    }

    public void setRowsAmount(Integer rowsAmount) {
        this.rowsAmount = rowsAmount;
    }

    public Integer getColumnsAmount() {
        return columnsAmount;
    }

    public void setColumnsAmount(Integer columnsAmount) {
        this.columnsAmount = columnsAmount;
    }
}
