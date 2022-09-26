package com.develpers3x2.thymeleaf.service;

import com.develpers3x2.thymeleaf.entidad.Usuario;
import com.develpers3x2.thymeleaf.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("UserDetailsService")
public class LoginService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null){
            throw new UsernameNotFoundException(username);
        }
        var roles = new ArrayList<GrantedAuthority>();

        roles.add(new SimpleGrantedAuthority(usuario.getRole().name()));

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
