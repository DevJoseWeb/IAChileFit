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

import br.com.fiap.am.coopfit.domain.Questionario;
import br.com.fiap.am.coopfit.services.QuestionarioService;

@RestController
@RequestMapping(value="/questionarios")
public class QuestionarioResource {

	
	@Autowired
	private QuestionarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Questionario> find(@PathVariable Long id) {
		Questionario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Questionario obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Questionario obj){
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
	public ResponseEntity<List<Questionario>> findAll() {
		List<Questionario> list = service.findAll();
		//List<QuestionarioDTO> listDTO = list.stream().map(obj -> new QuestionarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}	
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Questionario>> findPage(
			@ RequestParam(value="page", defaultValue="0") Integer page, 
			@ RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@ RequestParam(value="orderBy", defaultValue="instante") String orderBy, 
			@ RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Questionario> list = service.findPage(page, linesPerPage, orderBy, direction);
		//Page<QuestionarioDTO> listDTO = list.map(obj -> new QuestionarioDTO(obj));
		return ResponseEntity.ok().body(list);
	}	
	
}
