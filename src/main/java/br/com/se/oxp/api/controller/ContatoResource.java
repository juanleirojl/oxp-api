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

import br.com.se.oxp.api.auditoria.dtos.ContatoDTO;
import br.com.se.oxp.api.exception.ErrorDetails;
import br.com.se.oxp.api.exception.RecursoNotFoundException;
import br.com.se.oxp.api.model.Contato;
import br.com.se.oxp.api.service.ContatoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juan Leiro
 *
 */
@RestController
@RequestMapping("/contatos")
@Slf4j
@CrossOrigin("*")
public class ContatoResource {
	
	//private final SimpleDateFormat dateFormt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Autowired
    private MessageSource messageSource;

	@GetMapping("/paginado")
	public ResponseEntity<Page<ContatoDTO>> findAll(Pageable pageable) {
		Page<Contato> contatos = this.contatoService.findAllPaginado((PageRequest) pageable);
		log.info("Buscando contatos");
		
		Page<ContatoDTO> contatosDTO = contatos.map(contato -> this.convertToDTO(contato));
		return ResponseEntity.ok(contatosDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ContatoDTO>> findAll() {
		List<Contato> contatos = this.contatoService.findAll();
		log.info("Buscando contatos");
		
		List<ContatoDTO> contatosDTO = contatos.stream().map(contato -> this.convertToDTO(contato)).collect(Collectors.toList());
		return ResponseEntity.ok(contatosDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContatoDTO> findById(@PathVariable("id") Long id){
		log.info("Buscando um Usuário pelo id: {}" , id);
		Optional<Contato> contato = this.contatoService.findById(id);
		
		if(!contato.isPresent()) {
			log.info("Contato não encontrado para o id: {}", id);
			throw new RecursoNotFoundException("id"+id);
		}
		ContatoDTO contatoDTO = this.convertToDTO(contato.get());
		
		return ResponseEntity.ok(contatoDTO);
	}
	
	@PostMapping
	public ResponseEntity<ContatoDTO> save(@Valid @RequestBody ContatoDTO contatoDTO) {
		log.info("Adicionando Contato: {}" , contatoDTO.toString());
		Contato contato = contatoService.save(this.convertToModel(contatoDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(contato.getId()).toUri();
		
		ContatoDTO contatoDTOSalvo = this.convertToDTO(contato);
		
		return ResponseEntity.created(uri).body(contatoDTOSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ContatoDTO> update(@Valid @RequestBody ContatoDTO contatoDTO, @PathVariable Long id) {
		log.info("Atualizando Contato: {}" , contatoDTO.toString());
		Optional<Contato> contatoOptional = this.contatoService.findById(id);
		
		if(!contatoOptional.isPresent()) {
			log.info("Contato não encontrado para o id: {}", id);
			throw new RecursoNotFoundException("id"+id);
		}
		
		contatoDTO.setId(id);
		
		Contato contatoSalvo = contatoService.save(this.convertToModel(contatoDTO));
		
		ContatoDTO contatoDTOSalvo = this.convertToDTO(contatoSalvo);
		
		return ResponseEntity.ok(contatoDTOSalvo);
	}
	
	/**
	 *  Converte uma entidade Contato para seu respectivo DTO.
	 *  @param Contato
	 *  @return ContatoDTO
	 */
	private ContatoDTO convertToDTO(Contato contato) {
		ContatoDTO contatoDTO = modelMap.map(contato, ContatoDTO.class);
		
		if(contatoDTO.getOperadora() != null) contatoDTO.getOperadora().setContatos(null);
		if(contatoDTO.getIntegrador() != null) contatoDTO.getIntegrador().setContatos(null);
		return contatoDTO;
	}
	
	/**
	 *  Converte um DTO para sua entidade.
	 *  @param ContatoDTO
	 *  @return Contato
	 */
	private Contato convertToModel(ContatoDTO contatoDTO) {
		Contato contato = modelMap.map(contatoDTO, Contato.class);
		return contato;
	}
	
	@ExceptionHandler({ RecursoNotFoundException.class } )
    public ResponseEntity<Object> handleContatoInexistenteException(RecursoNotFoundException ex, WebRequest request) {
         String mensagemContato = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<ErrorDetails> erros = Arrays.asList(new ErrorDetails(LocalDateTime.now(), mensagemContato, mensagemDesenvolvedor,request.getDescription(false)));
        return ResponseEntity.badRequest().body(erros);
    }
}
