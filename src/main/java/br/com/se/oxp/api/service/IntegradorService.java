package br.com.se.oxp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.se.oxp.api.model.Integrador;
import br.com.se.oxp.api.repository.IntegradorRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class IntegradorService implements BaseService<Integrador> {
	
	@Autowired
	private IntegradorRepository integradorRepository;
	
	@Override
	public Page<Integrador> findAllPaginado(PageRequest pageRequest) {
		log.info("Buscando todos os integradors paginados");
		return this.integradorRepository.findAll(pageRequest);
	}
	
	@Override
	public List<Integrador> findAll() {
		log.info("Buscando uma lista de integradors");
		return this.integradorRepository.findAll();
	}

	@Override
	public Optional<Integrador> findById(Long id) {	
		log.info("Buscando um integrador pelo ID: {}",id);
		return this.integradorRepository.findById(id);
	}
	
	public Integrador findOne(Long id) {
		return this.integradorRepository.getOne(id);
	}

	@Override
	public Integrador save(Integrador integrador) {
		log.info("Persistindo o integrador: {}", integrador);
		return this.integradorRepository.save(integrador);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o integrador do ID: {}", id);
		this.integradorRepository.deleteById(id);
	}
	
}
