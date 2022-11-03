package com.nttdata.bootcamp.mscatalog.web.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class CatalogModel {

    private String id;

    @NotBlank(message="Parameter cannot be null or empty")
    private String parameter;

    @NotBlank(message="Name cannot be null or empty")
    private String value;


}
