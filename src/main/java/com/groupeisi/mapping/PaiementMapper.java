package com.groupeisi.mapping;

import com.groupeisi.DTO.Paiementdto;
import com.groupeisi.Entities.Paiement;
import org.mapstruct.Mapper;

@Mapper
public interface PaiementMapper {
    Paiementdto toPaiement(Paiement paiement);
    Paiement fromPaiement(Paiementdto paiementdto);
}
