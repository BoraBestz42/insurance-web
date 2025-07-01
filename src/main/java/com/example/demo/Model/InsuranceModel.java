package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products_insurance", schema = "insurance")
public class InsuranceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    public InsuranceModel(){
    }
    public InsuranceModel(Long id, String name, String typeName, Integer price,String description, String imageName) {
        this.id = id;
        this.name = name;
        this.typeName = typeName;
        this.price = price;
        this.description = description;
        this.imageName = imageName;
    }
}
