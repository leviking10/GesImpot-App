package com.groupeisi.DAO;

import com.groupeisi.Entities.Declarant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeclarantRepository extends JpaRepository<Declarant, Long> {

}

