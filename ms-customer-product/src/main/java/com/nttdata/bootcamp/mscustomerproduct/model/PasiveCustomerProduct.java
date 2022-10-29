package com.nttdata.bootcamp.mscustomerproduct.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//se separa el activo del pasivo por la función que cada uno cumple
public class PasiveCustomerProduct {

    private Integer id;
    private String customerId;
    private String productId;
    private Boolean has_maintenance; // tiene mantenimiento?
    private Double amountMaintenance; //monto mantenimiento
    private Integer amountOperation; //cantidad de operaciones
    private Date dateOperation; //fecha permitida para la operacion
}
