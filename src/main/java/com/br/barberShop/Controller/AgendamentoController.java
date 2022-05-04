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

import com.br.barberShop.Model.Agendamento;
import com.br.barberShop.Repository.AgendamentoRepository;

@RestController
@RequestMapping("/agendamento")
@CrossOrigin("*")
public class AgendamentoController {

	@Autowired
	private AgendamentoRepository repositoty;
	
	@GetMapping
	public ResponseEntity<List<Agendamento>> GetAll(){
		return ResponseEntity.ok(repositoty.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Agendamento> getById(@PathVariable long id){
		return repositoty.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Agendamento>> getByTitle(@PathVariable String titulo){
		return ResponseEntity.ok(repositoty.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<Agendamento> post(@Valid @RequestBody Agendamento agendamento){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repositoty.save(agendamento));
	}

	@PutMapping
	public ResponseEntity<Agendamento> put(@Valid @RequestBody Agendamento agendamento){
		return repositoty.findById(agendamento.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repositoty.save(agendamento)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<Agendamento> post = repositoty.findById(id);

		if(post.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		repositoty.deleteById(id);
	}

	
}
