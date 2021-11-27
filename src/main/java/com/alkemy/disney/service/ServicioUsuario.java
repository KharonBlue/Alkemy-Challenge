
package com.alkemy.disney.service;

import com.alkemy.disney.token.TokenService;
import com.alkemy.disney.entity.Usuario;
import com.alkemy.disney.repository.UsuarioRepositorio;
import com.alkemy.disney.token.Token;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kharon
 */
@Service
@AllArgsConstructor
public class ServicioUsuario implements UserDetailsService{
    
    private final String USUARIO_NO_ENCONTRADO_MSJ = "Usuario con este mail no encontrado";
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return usuarioRepositorio.findByCorreo(correo).orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USUARIO_NO_ENCONTRADO_MSJ, correo)));
    }

    @Transactional
    public String signUpUser(Usuario usuario) {
        boolean usuarioExistente = usuarioRepositorio
                .findByCorreo(usuario.getCorreo())
                .isPresent();

        if (usuarioExistente) {
            throw new IllegalStateException("Email ya usado para un token");
        }

        //Encripto la clave y la seteo al usuario
        String claveEncriptada = bCryptPasswordEncoder
                .encode(usuario.getClave());

        usuario.setClave(claveEncriptada);

        usuarioRepositorio.save(usuario);

        String token = UUID.randomUUID().toString();

        Token tk = new Token(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), usuario);
        

        tokenService.saveConfirmationToken(tk);
        return token;
    }

    @Transactional
    public int enableUsuario(String correo) {
        return usuarioRepositorio.desbloquearUsuario(correo);
    }

}
