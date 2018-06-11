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

import br.com.se.oxp.api.auditoria.dtos.OperadoraDTO;
import br.com.se.oxp.api.exception.ErrorDetails;
import br.com.se.oxp.api.exception.RecursoNotFoundException;
import br.com.se.oxp.api.model.Operadora;
import br.com.se.oxp.api.service.OperadoraService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juan Leiro
 *
 */
@RestController
@RequestMapping("/operadoras")
@Slf4j
@CrossOrigin("*")
public class OperadoraResource {
	
	//private final SimpleDateFormat dateFormt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private OperadoraService operadoraService;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Autowired
    private MessageSource messageSource;

	@GetMapping("/paginado")
	public ResponseEntity<Page<OperadoraDTO>> findAll(Pageable pageable) {
		Page<Operadora> operadoras = this.operadoraService.findAllPaginado((PageRequest) pageable);
		log.info("Buscando operadoras");
		
		Page<OperadoraDTO> operadorasDTO = operadoras.map(operadora -> this.convertToDTO(operadora));
		return ResponseEntity.ok(operadorasDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<OperadoraDTO>> findAll() {
		List<Operadora> operadoras = this.operadoraService.findAll();
		log.info("Buscando operadoras");
		
		List<OperadoraDTO> operadorasDTO = operadoras.stream().map(operadora -> this.convertToDTO(operadora)).collect(Collectors.toList());
		return ResponseEntity.ok(operadorasDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OperadoraDTO> findById(@PathVariable("id") Long id){
		log.info("Buscando um Usuário pelo id: {}" , id);
		Optional<Operadora> operadora = this.operadoraService.findById(id);
		
		if(!operadora.isPresent()) {
			log.info("Operadora não encontrado para o id: {}", id);
			throw new RecursoNotFoundException("id"+id);
		}
		OperadoraDTO operadoraDTO = this.convertToDTO(operadora.get());
		
		return ResponseEntity.ok(operadoraDTO);
	}
	
	@PostMapping
	public ResponseEntity<OperadoraDTO> save(@Valid @RequestBody OperadoraDTO operadoraDTO) {
		log.info("Adicionando Operadora: {}" , operadoraDTO.toString());
		Operadora operadora = operadoraService.save(this.convertToModel(operadoraDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(operadora.getId()).toUri();
		
		OperadoraDTO operadoraDTOSalvo = this.convertToDTO(operadora);
		
		return ResponseEntity.created(uri).body(operadoraDTOSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OperadoraDTO> update(@Valid @RequestBody OperadoraDTO operadoraDTO, @PathVariable Long id) {
		log.info("Atualizando Operadora: {}" , operadoraDTO.toString());
		Optional<Operadora> operadoraOptional = this.operadoraService.findById(id);
		
		if(!operadoraOptional.isPresent()) {
			log.info("Operadora não encontrado para o id: {}", id);
			throw new RecursoNotFoundException("id"+id);
		}
		
		operadoraDTO.setId(id);
		
		Operadora operadoraSalvo = operadoraService.save(this.convertToModel(operadoraDTO));
		
		OperadoraDTO operadoraDTOSalvo = this.convertToDTO(operadoraSalvo);
		
		return ResponseEntity.ok(operadoraDTOSalvo);
	}
	
	/**
	 *  Converte uma entidade Operadora para seu respectivo DTO.
	 *  @param Operadora
	 *  @return OperadoraDTO
	 */
	private OperadoraDTO convertToDTO(Operadora operadora) {
		OperadoraDTO operadoraDTO = modelMap.map(operadora, OperadoraDTO.class);
		if(operadoraDTO.getContatos() != null) operadoraDTO.contatos.forEach(c -> c.setOperadora(null));
		return operadoraDTO;
	}
	
	/**
	 *  Converte um DTO para sua entidade.
	 *  @param OperadoraDTO
	 *  @return Operadora
	 */
	private Operadora convertToModel(OperadoraDTO operadoraDTO) {
		Operadora operadora = modelMap.map(operadoraDTO, Operadora.class);
		return operadora;
	}
	
	@ExceptionHandler({ RecursoNotFoundException.class } )
    public ResponseEntity<Object> handleOperadoraInexistenteException(RecursoNotFoundException ex, WebRequest request) {
         String mensagemOperadora = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<ErrorDetails> erros = Arrays.asList(new ErrorDetails(LocalDateTime.now(), mensagemOperadora, mensagemDesenvolvedor,request.getDescription(false)));
        return ResponseEntity.badRequest().body(erros);
    }
}
