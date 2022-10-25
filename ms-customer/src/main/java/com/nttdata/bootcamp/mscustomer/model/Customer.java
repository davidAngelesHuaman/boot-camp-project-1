package com.nttdata.bootcamp.mscustomer.model;

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
public class Customer {

    //Los clientes pueden ser persona natural o juridica
    // se necesita tipo de cliente
    //tipo de doc: dni o ruc
    //nombre o raz social
    @Id
    private Integer id;
    private String docType;
    private String docNumber;
    private String fullName;
    private String telephone;
    private String address;
    private String statusCustomer;
}
