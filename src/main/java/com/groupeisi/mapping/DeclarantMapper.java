package com.groupeisi.mapping;


import com.groupeisi.DTO.Declarantdto;
import com.groupeisi.Entities.Declarant;
import org.mapstruct.Mapper;

@Mapper
public interface DeclarantMapper {
    Declarantdto toDeclarant(Declarant declarant);
    Declarant fromtoDeclarant(Declarantdto declarantdto);
}

