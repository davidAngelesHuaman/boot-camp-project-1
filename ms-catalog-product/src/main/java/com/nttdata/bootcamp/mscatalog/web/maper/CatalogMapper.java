package com.nttdata.bootcamp.mscatalog.web.maper;

import com.nttdata.bootcamp.mscatalog.domain.Catalog;
import com.nttdata.bootcamp.mscatalog.web.model.CatalogModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CatalogMapper {

    Catalog modelToEntity (CatalogModel model);

    CatalogModel entityToModel (Catalog event);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Catalog entity, Catalog updateEntity);

}
