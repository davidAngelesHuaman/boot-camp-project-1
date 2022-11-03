package com.nttdata.bootcamp.mscatalog.domain;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Document(value = "catalogs")
//@RedisHash("catalogs")


public class Catalog {

    @Id
    private String id;

    @NotNull
    private String parameter;

    @NotNull
    private String value;

}
