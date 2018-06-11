package br.com.se.oxp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.se.oxp.api.model.Operadora;
import br.com.se.oxp.api.repository.OperadoraRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OperadoraService implements BaseService<Operadora> {
	
	@Autowired
	private OperadoraRepository operadoraRepository;
	
	@Override
	public Page<Operadora> findAllPaginado(PageRequest pageRequest) {
		log.info("Buscando todos os operadoras paginados");
		return this.operadoraRepository.findAll(pageRequest);
	}
	
	@Override
	public List<Operadora> findAll() {
		log.info("Buscando uma lista de operadoras");
		return this.operadoraRepository.findAll();
	}

	@Override
	public Optional<Operadora> findById(Long id) {	
		log.info("Buscando um operadora pelo ID: {}",id);
		return this.operadoraRepository.findById(id);
	}
	
	public Operadora findOne(Long id) {
		return this.operadoraRepository.getOne(id);
	}

	@Override
	public Operadora save(Operadora operadora) {
		log.info("Persistindo o operadora: {}", operadora);
		return this.operadoraRepository.save(operadora);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o operadora do ID: {}", id);
		this.operadoraRepository.deleteById(id);
	}
	
}
