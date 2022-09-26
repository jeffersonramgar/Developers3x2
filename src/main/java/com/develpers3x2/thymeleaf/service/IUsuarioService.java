package com.develpers3x2.thymeleaf.service;

import com.develpers3x2.thymeleaf.entidad.Usuario;

import java.util.List;

public interface IUsuarioService {

    public Usuario findById(int id);
    public Usuario findByUsername(String username);
    public List<Usuario> findAll();
    public Usuario createUsuario(Usuario usuario);
    public Usuario updateUsuario(int id, Usuario usuario);
    public void deleteUsuario(int id);

}
