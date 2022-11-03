package com.nttdata.bootcamp.msproduct.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;
    private String nameProduct;
    private String typeProduct; //activo o pasivo con filtro "ProductType"
    private String statusProduct;
}
