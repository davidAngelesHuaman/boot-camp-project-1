package com.nttdata.bootcamp.mstransaction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private Integer id;
    private Integer idCustomerProduct;
    private String typeTransaction;
    private Date dateTransaction;
    private Double amountTransaction;
    private String description;
    private Boolean isProcessed;
}
