package br.com.se.oxp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.se.oxp.api.model.Grupo;
import br.com.se.oxp.api.repository.GrupoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrupoService implements BaseService<Grupo> {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Override
	public Page<Grupo> findAllPaginado(PageRequest pageRequest) {
		log.info("Buscando todos os grupos paginados");
		return this.grupoRepository.findAll(pageRequest);
	}
	
	@Override
	public List<Grupo> findAll() {
		log.info("Buscando uma lista de grupos");
		return this.grupoRepository.findAll();
	}

	@Override
	public Optional<Grupo> findById(Long id) {	
		log.info("Buscando um grupo pelo ID: {}",id);
		return this.grupoRepository.findById(id);
	}
	
	public Grupo findOne(Long id) {
		return this.grupoRepository.getOne(id);
	}

	@Override
	public Grupo save(Grupo grupo) {
		log.info("Persistindo o grupo: {}", grupo);
		return this.grupoRepository.save(grupo);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o grupo do ID: {}", id);
		this.grupoRepository.deleteById(id);
	}
}
