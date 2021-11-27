/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kharon
 */
@Repository
@Transactional(readOnly = true)
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByCorreo(String correo);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario a " +
            "SET a.desbloqueado = TRUE WHERE a.correo = ?1")
    int desbloquearUsuario(String correo);
}
