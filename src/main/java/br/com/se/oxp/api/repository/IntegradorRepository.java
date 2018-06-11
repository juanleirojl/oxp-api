package br.com.se.oxp.api.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.se.oxp.api.model.Integrador;

public interface IntegradorRepository extends JpaRepository<Integrador, Long> {

	public abstract Optional<Integrador> findByNomeIgnoreCase(String nome);

	@Query("SELECT i FROM Integrador i LEFT JOIN FETCH i.contatos c ")
	public abstract Set<Integrador> findAllIntegradoresWithContatos();

	@Query("SELECT i FROM Integrador i LEFT JOIN FETCH i.contatos c  where i.id=:id ")
	public Integrador findIntegradorWithContatos(@Param("id") Long id);

	public abstract Optional<Integrador> findByCnpj(String cnpj);
}
