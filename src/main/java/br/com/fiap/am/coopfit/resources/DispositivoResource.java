package br.com.fiap.am.coopfit.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.am.coopfit.domain.Dispositivo;
import br.com.fiap.am.coopfit.services.DispositivoService;

@RestController
@RequestMapping(value="/dispositivos")
public class DispositivoResource {

	
	@Autowired
	private DispositivoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Dispositivo> find(@PathVariable Long id) {
		Dispositivo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Dispositivo obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Dispositivo obj){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Dispositivo>> findAll() {
		List<Dispositivo> list = service.findAll();
		//List<DispositivoDTO> listDTO = list.stream().map(obj -> new DispositivoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}	
	
//	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
//	@RequestMapping(value="/page", method=RequestMethod.GET)
//	public ResponseEntity<Page<DispositivoDTO>> findPage(
//			@ RequestParam(value="page", defaultValue="0") Integer page, 
//			@ RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
//			@ RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
//			@ RequestParam(value="direction", defaultValue="ASC") String direction) {
//		Page<Dispositivo> list = service.findPage(page, linesPerPage, orderBy, direction);
//		Page<DispositivoDTO> listDTO = list.map(obj -> new DispositivoDTO(obj));
//		return ResponseEntity.ok().body(listDTO);
//	}	
	
}
