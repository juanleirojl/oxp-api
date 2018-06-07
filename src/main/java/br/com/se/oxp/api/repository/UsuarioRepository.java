package br.com.se.oxp.api.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.se.oxp.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	 @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.grupos grupo LEFT JOIN FETCH grupo.permissoes permissao")
	 Set<Usuario> findAllWithGruposPermissoes(); 
	 
	 //Page<Usuario> findAll(Pageable pageable); 
}
