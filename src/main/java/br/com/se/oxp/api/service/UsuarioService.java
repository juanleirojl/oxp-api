package br.com.se.oxp.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.se.oxp.api.model.Usuario;
import br.com.se.oxp.api.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UsuarioService implements BaseService<Usuario> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Page<Usuario> findAllPaginado(PageRequest pageRequest) {
		log.info("Buscando todos os usuarios paginados");
		return this.usuarioRepository.findAll(pageRequest);
	}
	
	@Override
	public List<Usuario> findAll() {
		log.info("Buscando uma lista de usuarios");
		return this.usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {	
		log.info("Buscando um usuario pelo ID: {}",id);
		return this.usuarioRepository.findById(id);
	}
	
	public Usuario findOne(Long id) {
		return this.usuarioRepository.getOne(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		log.info("Persistindo o usuario: {}", usuario);
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o usuario do ID: {}", id);
		this.usuarioRepository.deleteById(id);
	}
	
	public Set<Usuario> findAllWithGruposPermissoes() {
		return this.usuarioRepository.findAllWithGruposPermissoes();
	}
}
