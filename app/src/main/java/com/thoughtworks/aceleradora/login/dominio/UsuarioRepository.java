package com.thoughtworks.aceleradora.login.dominio;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String nome);
    List<Usuario> findAll();
    Optional<Usuario> findById(Long Id);
}
