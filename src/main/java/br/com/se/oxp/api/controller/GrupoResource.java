package br.com.se.oxp.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.se.oxp.api.auditoria.dtos.GrupoDTO;
import br.com.se.oxp.api.model.Grupo;
import br.com.se.oxp.api.service.GrupoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juan Leiro
 *
 */
@RestController
@RequestMapping("/grupos")
@Slf4j
@CrossOrigin("*")
public class GrupoResource {
	

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private ModelMapper modelMap;
	

	@GetMapping
	public ResponseEntity<List<GrupoDTO>> findAll() {
		List<Grupo> grupos = this.grupoService.findAll();
		log.info("Buscando grupos");
		
		List<GrupoDTO> gruposDTO = grupos.stream().map(grupo -> this.convertToDTO(grupo)).collect(Collectors.toList());
		return ResponseEntity.ok(gruposDTO);
	}
	
	
	/**
	 *  Converte uma entidade Grupo para seu respectivo DTO.
	 *  @param Grupo
	 *  @return GrupoDTO
	 */
	private GrupoDTO convertToDTO(Grupo grupo) {
		GrupoDTO grupoDTO = modelMap.map(grupo, GrupoDTO.class);
		return grupoDTO;
	}
	
}
