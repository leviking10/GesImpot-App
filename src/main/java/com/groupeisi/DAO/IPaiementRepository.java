package com.groupeisi.DAO;

import com.groupeisi.Entities.Declaration;
import com.groupeisi.Entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByDeclaration(Declaration declaration);
    List<Paiement> findByDeclarationId(Long declarationId);
}
