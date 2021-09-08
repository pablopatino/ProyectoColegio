package com.proyectoColegio.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.repositorio.RepositorioRol;
import com.proyectoColegio.repositorio.RepositorioUsuario;

@Service
@Transactional
public class ServicesUsuarioImlp implements ServicesUsuario, UserDetailsService {
	
	@Autowired
	RepositorioUsuario repositorioUsuario;
	@Autowired
	RepositorioRol repositorioRol;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
	
//		usuario.getRoles().add(new Rol("ROL_USER"));
		
		return this.repositorioUsuario.save(usuario);
	}

	@Override
	public Rol guardarRol(Rol rol) {
		return this.repositorioRol.save(rol);
	}

	@Override
	public void agregarRol(String username, String nombreRol) {
		Usuario usuario = this.repositorioUsuario.findByUsername(username);
		Rol rol = this.repositorioRol.findByName(nombreRol);
		usuario.getRoles().add(rol);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repositorioUsuario.findByUsername(username);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		usuario.getRoles().stream().forEach(rol -> { authorities.add(new SimpleGrantedAuthority(rol.getNombre()));});
		return new User(usuario.getUsername(), usuario.getPassword(), authorities);
	}
	
	@Override
	public Usuario getUser(String username) {
		return this.repositorioUsuario.findByUsername(username);
	}

}
