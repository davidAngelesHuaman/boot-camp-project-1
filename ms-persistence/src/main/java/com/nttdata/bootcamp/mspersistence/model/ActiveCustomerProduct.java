package com.nttdata.bootcamp.mspersistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//se separa el activo del pasivo por la función que cada uno cumple
public class ActiveCustomerProduct {
    @Id
    private Integer id;
    private String customerId;
    private String productId;
    private Double amountCredit; //monto
    private Double debtCredit; //deuda
    private Double limitCredit; //limite crediticio
}
