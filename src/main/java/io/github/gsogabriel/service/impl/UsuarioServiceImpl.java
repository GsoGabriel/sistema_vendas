package io.github.gsogabriel.service.impl;

import io.github.gsogabriel.domain.entity.Usuario;
import io.github.gsogabriel.domain.repository.UsuarioRepository;
import io.github.gsogabriel.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
        if(senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();

    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado na base."));

        String[] roles = user.isAdmin() ? new String[]{"USER", "ADMIN"} : new String[]{"USER"};

        return User.builder()
                .username(user.getLogin())
                .password(user.getSenha())
                .roles(roles)
                .build();


//        if (!s.equals("cicrano")){
//            throw new UsernameNotFoundException("Usuario nao encotrado na base.");
//        }
//        return User.builder()
//                .username("cicrano")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER", "ADMIN")
//                .build();
    }

}
