package com.thoughtworks.aceleradora.login.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNome(String nome);
    List<Usuario> findAll();
    Usuario findOneByEmail(String email);
    Usuario findOneById(Long Id);



}
