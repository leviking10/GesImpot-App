package com.groupeisi.service;

import com.groupeisi.DAO.IDeclarantRepository;
import com.groupeisi.Entities.Declarant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeclarantServiceImpl implements DeclarantService{
    @Autowired
    private IDeclarantRepository declarantRepository;
    @Override
    public List<Declarant> getAllDeclarants() {
        return declarantRepository.findAll();
    }

    @Override
    public void saveDeclarant(Declarant declarant) {
        this.declarantRepository.save(declarant);
    }

    @Override
    public Declarant getDeclarantById(long id) {
        Optional<Declarant> optional =declarantRepository.findById(id);
        Declarant declarant=null;
        if(optional.isPresent()){
            declarant=optional.get();
        }else{
            throw new RuntimeException("Declaration introuvable pour l'id ::"+id);
        }
        return declarant;
    }

   @Override
    public void deleteDeclarantById(long id) {
        this.declarantRepository.deleteById(id);
    }
}
