package com.groupeisi.service;

import com.groupeisi.Entities.Declarant;

import java.util.List;

public interface DeclarantService {
    List<Declarant> getAllDeclarants();
    void saveDeclarant(Declarant declarant);
    Declarant getDeclarantById(long id);
    void deleteDeclarantById(long id);
}
