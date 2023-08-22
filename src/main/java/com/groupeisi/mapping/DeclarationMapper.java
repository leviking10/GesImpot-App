package com.groupeisi.mapping;


import com.groupeisi.DTO.Declarationdto;
import com.groupeisi.Entities.Declaration;
import org.mapstruct.Mapper;

@Mapper
public interface DeclarationMapper {
    Declarationdto toDeclaration(Declaration declaration);
    Declaration fromtoDeclaration(Declarationdto declarationdto);
}
