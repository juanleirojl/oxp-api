package br.com.se.oxp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseService<T> {
	
	/**
	 * Retorna uma lista pagina de <T>
	 * 
	 * @param id
	 * @param pageRequest
	 * @return Page<T>
	 */
	@Transactional(readOnly = true)
	Page<T> findAllPaginado(PageRequest pageRequest);
	
	
	/**
	 *  Retorna uma lista de <T>
	 * @return Set<T>
	 */
	@Transactional(readOnly = true)
	List<T> findAll();
	
	
	/**
	 *  Retorna um objeto <T> por ID
	 * @param id
	 * @return Optional<T>
	 */
	@Transactional(readOnly = true)
	Optional<T> findById(Long id);
	
	/**
	 * Persiste um objeto T 
	 * @param t
	 * @return T
	 */
	T save(T t);
	
	/**
	 * Remove um objeto
	 * @param id
	 */
	void remover(Long id);
	
	
}
