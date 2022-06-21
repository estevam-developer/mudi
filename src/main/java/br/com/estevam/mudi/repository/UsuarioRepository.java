package br.com.estevam.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estevam.mudi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
