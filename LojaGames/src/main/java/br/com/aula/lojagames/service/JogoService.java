package br.com.aula.lojagames.service;

import br.com.aula.lojagames.entity.Jogo;
import br.com.aula.lojagames.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    @Transactional
    public Jogo salvar(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    @Transactional(readOnly = true)
    public Jogo buscarPorId(Long id) {
        return jogoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("## JOGO NÃO ENCONTRADO ##")
        );
    }

    @Transactional(readOnly = true)
    public List<Jogo> buscarTodos() {
        return jogoRepository.findAll();
    }

    @Transactional
    public Jogo editarInformacoes(Long id, Jogo jogoAtualizado) {
        Jogo jogo = buscarPorId(id);
        if (jogoAtualizado.getNome() != null) {
            jogo.setNome(jogoAtualizado.getNome());
        }

        if (jogoAtualizado.getGenero() != null) {
            jogo.setGenero(jogoAtualizado.getGenero());
        }

        if (jogoAtualizado.getAnoLancamento() != null) {
            jogo.setAnoLancamento(jogoAtualizado.getAnoLancamento());
        }
        return jogoRepository.save(jogo);
    }

    @Transactional
    public void excluirPorId(Long id) {
        Jogo jogo = buscarPorId(id);

        if (jogo == null) {
            throw new RuntimeException("Jogo com ID " + id + " não encontrado.");
        }

        jogoRepository.delete(jogo);
    }

}