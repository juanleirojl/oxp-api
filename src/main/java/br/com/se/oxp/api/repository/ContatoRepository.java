package br.com.se.oxp.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.se.oxp.api.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	@Query("SELECT c FROM Contato c LEFT JOIN FETCH c.operadora o LEFT JOIN FETCH c.integrador ")
	public abstract List<Contato> findContatosWithIntegradorOperadora();
	
	@Query("SELECT c FROM Contato c LEFT JOIN FETCH c.operadora o LEFT JOIN FETCH c.integrador where c.id=:id ")
	public Contato findContatoWithIntegradorOperadora(@Param("id") Long id);
	
	public abstract Optional<Contato> findByEmailIgnoreCase(String email);
	
	@Query("SELECT c FROM Contato c INNER JOIN FETCH c.operadora o ")
	public Set<Contato> findContatosOperadora();
}
