package br.com.se.oxp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.se.oxp.api.model.Contato;
import br.com.se.oxp.api.repository.ContatoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ContatoService implements BaseService<Contato> {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Override
	public Page<Contato> findAllPaginado(PageRequest pageRequest) {
		log.info("Buscando todos os contatos paginados");
		return this.contatoRepository.findAll(pageRequest);
	}
	
	@Override
	public List<Contato> findAll() {
		log.info("Buscando uma lista de contatos");
		return this.contatoRepository.findAll();
	}

	@Override
	public Optional<Contato> findById(Long id) {	
		log.info("Buscando um contato pelo ID: {}",id);
		return this.contatoRepository.findById(id);
	}
	
	public Contato findOne(Long id) {
		return this.contatoRepository.getOne(id);
	}

	@Override
	public Contato save(Contato contato) {
		log.info("Persistindo o contato: {}", contato);
		return this.contatoRepository.save(contato);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o contato do ID: {}", id);
		this.contatoRepository.deleteById(id);
	}
	
}
