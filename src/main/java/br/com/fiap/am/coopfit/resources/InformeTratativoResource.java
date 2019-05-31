package br.com.fiap.am.coopfit.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.am.coopfit.domain.InformeTratativo;
import br.com.fiap.am.coopfit.services.InformeTratativoService;

@RestController
@RequestMapping(value="/tratativas")
public class InformeTratativoResource {

	
	@Autowired
	private InformeTratativoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<InformeTratativo> find(@PathVariable Long id) {
		InformeTratativo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody InformeTratativo obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody InformeTratativo obj){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<InformeTratativo>> findAll() {
		List<InformeTratativo> list = service.findAll();
		//List<InformeTratativoDTO> listDTO = list.stream().map(obj -> new InformeTratativoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}	
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<InformeTratativo>> findPage(
			@ RequestParam(value="page", defaultValue="0") Integer page, 
			@ RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@ RequestParam(value="orderBy", defaultValue="instante") String orderBy, 
			@ RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<InformeTratativo> list = service.findPage(page, linesPerPage, orderBy, direction);
		//Page<InformeTratativoDTO> listDTO = list.map(obj -> new InformeTratativoDTO(obj));
		return ResponseEntity.ok().body(list);
	}	
	
}
