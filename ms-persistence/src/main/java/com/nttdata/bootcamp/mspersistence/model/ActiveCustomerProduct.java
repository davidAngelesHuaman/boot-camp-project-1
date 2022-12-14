package com.nttdata.bootcamp.mspersistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//se separa el activo del pasivo por la función que cada uno cumple
public class ActiveCustomerProduct {

    @Id
    private String id;
    private String customerId;
    private String productId;
    private BigDecimal amountCredit; //monto
    private BigDecimal debtCredit; //deuda
    private BigDecimal limitCredit; //limite crediticio
}
