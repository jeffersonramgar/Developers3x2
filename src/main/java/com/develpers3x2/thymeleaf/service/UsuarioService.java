package com.develpers3x2.thymeleaf.service;

import com.develpers3x2.thymeleaf.entidad.Profile;
import com.develpers3x2.thymeleaf.entidad.Usuario;
import com.develpers3x2.thymeleaf.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private ProfileService profileService;

    @Override
    public Usuario findById(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById((long) id);
        return usuario.get();
    }

    @Override
    public Usuario findByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

        return usuarios;
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        Profile perfil = usuario.getProfile();
        perfil.setCreatedAt(new Date());
        perfil = profileService.createProfile(perfil);
        usuario.setProfile(perfil);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(int id, Usuario usuario) {
        Profile newPerfil = profileService.updateProfile( usuario.getProfile());
        usuario.setProfile(newPerfil);
        usuario.setIdUser(id);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(int id) {
        usuarioRepository.deleteById((long) id);
    }
}
