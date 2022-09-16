package com.develpers3x2.thymeleaf.service;

import com.develpers3x2.thymeleaf.entidad.*;
import com.develpers3x2.thymeleaf.repositories.IEnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService implements IEnterpriseService {
    @Autowired
    private IEnterpriseRepository enterpriseRepository;
    @Override
    public Enterprise findById(int id) {
        Optional<Enterprise> empresa = enterpriseRepository.findById((long) id);

        return empresa.get();
    }

    @Override
    public List<Enterprise> findAll() {
        List<Enterprise> empresas =(List<Enterprise>) enterpriseRepository.findAll();

        return empresas;
    }




    @Override
    public Enterprise createEnterprise(Enterprise empresa) {
        return enterpriseRepository.save(empresa);
    }


    @Override
    public Enterprise updateEnterprise(int id, Enterprise empresa) {
        empresa.setId(id);
        return enterpriseRepository.save(empresa);
    }

    @Override
    public void deleteEnterprise(int id) {
        enterpriseRepository.deleteById((long) id);
    }


}
