package br.com.aula.lojagames.repository;

import br.com.aula.lojagames.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

}