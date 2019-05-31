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

import br.com.fiap.am.coopfit.domain.InformacaoSaude;
import br.com.fiap.am.coopfit.services.InformacaoSaudeService;

@RestController
@RequestMapping(value="/informacoes_saude")
public class InformacaoSaudeResource {

	
	@Autowired
	private InformacaoSaudeService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<InformacaoSaude> find(@PathVariable Long id) {
		InformacaoSaude obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody InformacaoSaude obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody InformacaoSaude obj){
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
	public ResponseEntity<List<InformacaoSaude>> findAll() {
		List<InformacaoSaude> list = service.findAll();
		//List<InformacaoSaudeDTO> listDTO = list.stream().map(obj -> new InformacaoSaudeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}	
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<InformacaoSaude>> findPage(
			@ RequestParam(value="page", defaultValue="0") Integer page, 
			@ RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@ RequestParam(value="orderBy", defaultValue="instante") String orderBy, 
			@ RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<InformacaoSaude> list = service.findPage(page, linesPerPage, orderBy, direction);
		//Page<InformacaoSaudeDTO> listDTO = list.map(obj -> new InformacaoSaudeDTO(obj));
		return ResponseEntity.ok().body(list);
	}	
	
}
