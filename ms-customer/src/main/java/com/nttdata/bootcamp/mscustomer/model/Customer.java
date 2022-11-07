package com.nttdata.bootcamp.mscustomer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    private String docType;
    private String docNumber;
    private String fullName;
    private String telephone;
    private String address;
    private String statusCustomer;
}
