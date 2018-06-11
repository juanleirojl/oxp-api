package br.com.se.oxp.api.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.se.oxp.api.enums.Status;
import br.com.se.oxp.api.model.Operadora;

public interface OperadoraRepository extends JpaRepository<Operadora, Long> {

	public abstract Optional<Operadora> findByNomeIgnoreCase(String nome);

	public abstract Optional<Operadora> findByCnpj(String cnpj);

	//@Query("SELECT o FROM Operadora o LEFT JOIN FETCH o.contatos c LEFT JOIN FETCH o.parametros p  LEFT JOIN FETCH p.parametroParceiroOperadoraList ppo where o.id=:id ")
	//Operadora findOperadoraWithParams(@Param("id") Long id);

	//@Query("SELECT o FROM Operadora o LEFT JOIN FETCH o.contatos LEFT JOIN FETCH o.parametros p  LEFT JOIN FETCH p.parametroParceiroOperadoraList ppo ")
	//public abstract Set<Operadora> findAllOperadoraWithParams();

	public abstract Set<Operadora> findByStatus(Status status);
}
