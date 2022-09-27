package com.develpers3x2.thymeleaf.service;

import com.develpers3x2.thymeleaf.entidad.Enterprise;

import java.util.List;

public interface IEnterpriseService {
    public Enterprise findById(int id);

    public List<Enterprise> findAll();
    public Enterprise createEnterprise(Enterprise empresa);
    public Enterprise updateEnterprise(int id, Enterprise empresa);
    public void deleteEnterprise(int id);
}
