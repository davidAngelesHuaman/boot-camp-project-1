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
public class Customer {

    @Id
    private Integer id;
    private String docType;
    private String docNumber;
    private String fullName;
    private String telephone;
    private String address;
    private String statusCustomer;
}
