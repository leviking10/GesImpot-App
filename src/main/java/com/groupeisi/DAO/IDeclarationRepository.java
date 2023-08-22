package com.groupeisi.DAO;

import com.groupeisi.Entities.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeclarationRepository extends JpaRepository <Declaration, Long> {
}
