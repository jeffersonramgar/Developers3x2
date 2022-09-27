package com.develpers3x2.thymeleaf.service;

import com.develpers3x2.thymeleaf.entidad.Profile;

import java.util.List;

public interface IProfileService {
    public Profile findById(int id);

    public List<Profile> findAll();
    public Profile createProfile(Profile perfil);
    public Profile updateProfile(Profile perfil);
    public void deleteProfile(int id);
}
