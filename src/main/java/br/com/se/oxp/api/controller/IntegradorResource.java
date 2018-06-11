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

import br.com.se.oxp.api.auditoria.dtos.IntegradorDTO;
import br.com.se.oxp.api.exception.ErrorDetails;
import br.com.se.oxp.api.exception.RecursoNotFoundException;
import br.com.se.oxp.api.model.Integrador;
import br.com.se.oxp.api.service.IntegradorService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juan Leiro
 *
 */
@RestController
@RequestMapping("/integradores")
@Slf4j
@CrossOrigin("*")
public class IntegradorResource {
	
	//private final SimpleDateFormat dateFormt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private IntegradorService integradorService;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Autowired
    private MessageSource messageSource;

	@GetMapping("/paginado")
	public ResponseEntity<Page<IntegradorDTO>> findAll(Pageable pageable) {
		Page<Integrador> integradors = this.integradorService.findAllPaginado((PageRequest) pageable);
		log.info("Buscando integradors");
		
		Page<IntegradorDTO> integradorsDTO = integradors.map(integrador -> this.convertToDTO(integrador));
		return ResponseEntity.ok(integradorsDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<IntegradorDTO>> findAll() {
		List<Integrador> integradors = this.integradorService.findAll();
		log.info("Buscando integradors");
		
		List<IntegradorDTO> integradorsDTO = integradors.stream().map(integrador -> this.convertToDTO(integrador)).collect(Collectors.toList());
		return ResponseEntity.ok(integradorsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IntegradorDTO> findById(@PathVariable("id") Long id){
		log.info("Buscando um Usuário pelo id: {}" , id);
		Optional<Integrador> integrador = this.integradorService.findById(id);
		
		if(!integrador.isPresent()) {
			log.info("Integrador não encontrado para o id: {}", id);
			throw new RecursoNotFoundException("id"+id);
		}
		IntegradorDTO integradorDTO = this.convertToDTO(integrador.get());
		
		return ResponseEntity.ok(integradorDTO);
	}
	
	@PostMapping
	public ResponseEntity<IntegradorDTO> save(@Valid @RequestBody IntegradorDTO integradorDTO) {
		log.info("Adicionando Integrador: {}" , integradorDTO.toString());
		Integrador integrador = integradorService.save(this.convertToModel(integradorDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(integrador.getId()).toUri();
		
		IntegradorDTO integradorDTOSalvo = this.convertToDTO(integrador);
		
		return ResponseEntity.created(uri).body(integradorDTOSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<IntegradorDTO> update(@Valid @RequestBody IntegradorDTO integradorDTO, @PathVariable Long id) {
		log.info("Atualizando Integrador: {}" , integradorDTO.toString());
		Optional<Integrador> integradorOptional = this.integradorService.findById(id);
		
		if(!integradorOptional.isPresent()) {
			log.info("Integrador não encontrado para o id: {}", id);
			throw new RecursoNotFoundException("id"+id);
		}
		
		integradorDTO.setId(id);
		
		Integrador integradorSalvo = integradorService.save(this.convertToModel(integradorDTO));
		
		IntegradorDTO integradorDTOSalvo = this.convertToDTO(integradorSalvo);
		
		return ResponseEntity.ok(integradorDTOSalvo);
	}
	
	/**
	 *  Converte uma entidade Integrador para seu respectivo DTO.
	 *  @param Integrador
	 *  @return IntegradorDTO
	 */
	private IntegradorDTO convertToDTO(Integrador integrador) {
		IntegradorDTO integradorDTO = modelMap.map(integrador, IntegradorDTO.class);
		integradorDTO.contatos.forEach(c -> c.setIntegrador(null));
		return integradorDTO;
	}
	
	/**
	 *  Converte um DTO para sua entidade.
	 *  @param IntegradorDTO
	 *  @return Integrador
	 */
	private Integrador convertToModel(IntegradorDTO integradorDTO) {
		Integrador integrador = modelMap.map(integradorDTO, Integrador.class);
		return integrador;
	}
	
	@ExceptionHandler({ RecursoNotFoundException.class } )
    public ResponseEntity<Object> handleIntegradorInexistenteException(RecursoNotFoundException ex, WebRequest request) {
         String mensagemIntegrador = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<ErrorDetails> erros = Arrays.asList(new ErrorDetails(LocalDateTime.now(), mensagemIntegrador, mensagemDesenvolvedor,request.getDescription(false)));
        return ResponseEntity.badRequest().body(erros);
    }
}
