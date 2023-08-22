package com.groupeisi.service;

import com.groupeisi.Entities.Declaration;
import java.util.List;

public interface DeclarationService {
    List<Declaration> getAllDeclarations();
    Declaration saveDeclaration(Declaration declaration);
    Declaration getDeclarationById(Long id);
    void deleteDeclaration(Long id);
    void validateAndSaveDeclaration(Declaration declaration);
}
