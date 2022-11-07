package com.nttdata.bootcamp.mstransaction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//se separa el activo del pasivo por la función que cada uno cumple
public class ActiveCustomerProduct {

    private String id;
    private String customerId;
    private String productId;
    private BigDecimal amountCredit; //monto
    private BigDecimal debtCredit; //deuda
    private BigDecimal limitCredit; //limite crediticio

}
