package com.br.barberShop.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.barberShop.Model.Servico;
import com.br.barberShop.Repository.ServicoRepository;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServicoController {
	
	@Autowired
	private ServicoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Servico>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Servico>> getByTitle(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping 
	public ResponseEntity<Servico> post (@RequestBody Servico servico){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(servico));
	}

	@PutMapping
	public ResponseEntity<Servico> put(@Valid @RequestBody Servico servico){
		return repository.findById(servico.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(servico)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<Servico> servico = repository.findById(id);

		if(servico.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		repository.deleteById(id);
	}

}
