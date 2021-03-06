package com.br.barberShop.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.barberShop.Model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico,Long> {
	public List<Servico> findAllByDescricaoContainingIgnoreCase (String descricao);

}
