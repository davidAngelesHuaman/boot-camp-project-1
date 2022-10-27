package com.nttdata.bootcamp.mstransaction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Integer id;
    private Integer idCustomerProduct;
    private String typeTransaction;
    private LocalDateTime dateTransaction;
    private Double amountTransaction;
    private String description;
    private Boolean isProcessed;
}
