package br.com.se.oxp.api.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.se.oxp.api.auditoria.dtos.UsuarioDTO;
import br.com.se.oxp.api.exception.ErrorDetails;
import br.com.se.oxp.api.exception.RecursoNotFoundException;
import br.com.se.oxp.api.exception.UsuarioInexistenteException;
import br.com.se.oxp.api.model.Usuario;
import br.com.se.oxp.api.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juan Leiro
 *
 */
@RestController
@RequestMapping("/usuarios")
@Slf4j
@CrossOrigin("*")
public class UsuarioController {
	
	//private final SimpleDateFormat dateFormt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Autowired
    private MessageSource messageSource;

	@GetMapping("/paginado")
	public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
		Page<Usuario> usuarios = this.usuarioService.findAllPaginado((PageRequest) pageable);
		log.info("Buscando usuarios");
		
		Page<UsuarioDTO> usuariosDTO = usuarios.map(usuario -> this.convertToDTO(usuario));
		return ResponseEntity.ok(usuariosDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> usuarios = this.usuarioService.findAll();
		log.info("Buscando usuarios");
		
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> this.convertToDTO(usuario)).collect(Collectors.toList());
		return ResponseEntity.ok(usuariosDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id){
		log.info("Buscando um Usuário pelo id: {}" , id);
		Optional<Usuario> usuario = this.usuarioService.findById(id);
		
		if(!usuario.isPresent()) {
			log.info("Usuario não encontrado para o id: {}", id);
			throw new RecursoNotFoundException("id"+id);
		}
		UsuarioDTO usuarioDTO = this.convertToDTO(usuario.get());
		
		return ResponseEntity.ok(usuarioDTO);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		log.info("Adicionando Usuario: {}" , usuarioDTO.toString());
		Usuario usuario = usuarioService.save(this.convertToModel(usuarioDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(usuario.getId()).toUri();
		
		UsuarioDTO usuarioDTOSalvo = this.convertToDTO(usuario);
		
		return ResponseEntity.created(uri).body(usuarioDTOSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
		log.info("Atualizando Usuario: {}" , usuarioDTO.toString());
		Optional<Usuario> usuarioOptional = this.usuarioService.findById(id);
		
		if(!usuarioOptional.isPresent()) {
			log.info("Usuario não encontrado para o id: {}", id);
			throw new UsuarioInexistenteException("id"+id);
		}
		
		usuarioDTO.setId(id);
		
		Usuario usuarioSalvo = usuarioService.save(this.convertToModel(usuarioDTO));
		
		UsuarioDTO usuarioDTOSalvo = this.convertToDTO(usuarioSalvo);
		
		return ResponseEntity.ok(usuarioDTOSalvo);
	}
	
	/**
	 *  Converte uma entidade Usuario para seu respectivo DTO.
	 *  @param Usuario
	 *  @return UsuarioDTO
	 */
	private UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = modelMap.map(usuario, UsuarioDTO.class);
		usuarioDTO.setNomeUltimoUsuario(usuario.getUltimoUsuario().getNome());
		return usuarioDTO;
	}
	
	/**
	 *  Converte um DTO para sua entidade.
	 *  @param UsuarioDTO
	 *  @return Usuario
	 */
	private Usuario convertToModel(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMap.map(usuarioDTO, Usuario.class);
		return usuario;
	}
	
	@ExceptionHandler({ UsuarioInexistenteException.class } )
    public ResponseEntity<Object> handleUsuarioInexistenteException(UsuarioInexistenteException ex, WebRequest request) {
         String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<ErrorDetails> erros = Arrays.asList(new ErrorDetails(LocalDateTime.now(), mensagemUsuario, mensagemDesenvolvedor,request.getDescription(false)));
        return ResponseEntity.badRequest().body(erros);
    }
}
