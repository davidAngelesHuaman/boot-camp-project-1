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
public class TransactionRequest {

    private String id;
    private BigDecimal amountTransaction;
    private String payType;
    private String description;
    private Integer status;

    private String originCustomer;
    private String destinationCustomer;

    private String message;

}
