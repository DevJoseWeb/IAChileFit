package br.com.fiap.am.coopfit.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.fiap.am.coopfit.domain.DispositivoSensor;
import br.com.fiap.am.coopfit.dto.DispositivoSensorDTO;
import br.com.fiap.am.coopfit.resource.utils.URL;
import br.com.fiap.am.coopfit.services.DispositivoSensorService;

@RestController
@RequestMapping(value = "/sensores")
public class DispositivoSensorResource {

	@Autowired
	private DispositivoSensorService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DispositivoSensor> find(@PathVariable Long id) {
		DispositivoSensor obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody DispositivoSensor obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody DispositivoSensor obj) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DispositivoSensorDTO>> findAll() {
		List<DispositivoSensor> list = service.findAll();
		List<DispositivoSensorDTO> listDTO = list.stream().map(obj -> new DispositivoSensorDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<DispositivoSensorDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<DispositivoSensor> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<DispositivoSensorDTO> listDTO = list.map(obj -> new DispositivoSensorDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/maximo", method = RequestMethod.GET)
	public ResponseEntity<Double> findPage(
			@RequestParam(value = "id", defaultValue = "0") Long id,
			@RequestParam(value = "tipo", defaultValue = "Sono") String tipo) {
		Long idDecoded = (id);
		String tipoDecoded = URL.decodeParam(tipo);
		double value = service.maxValue(idDecoded, tipoDecoded);
		//Page<DispositivoSensor> list = service.findPage(page, linesPerPage, orderBy, direction);
		//Page<DispositivoSensorDTO> listDTO = list.map(obj -> new DispositivoSensorDTO(obj));
		return ResponseEntity.ok().body(value);
	}

}
