package br.com.aula.lojagames.web.controller;

import br.com.aula.lojagames.entity.Jogo;
import br.com.aula.lojagames.service.JogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("jogos")
public class JogoController {

    private final JogoService jogoService;

    @PostMapping
    public ResponseEntity<Jogo> create(@RequestBody Jogo jogo) {
        Jogo jogoCriado = jogoService.salvar(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoCriado);
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> getAll() {
        List<Jogo> jogos = jogoService.buscarTodos();
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> getById(@PathVariable Long id) {
        Jogo jogo = jogoService.buscarPorId(id);
        return ResponseEntity.ok(jogo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Jogo> updateInfo(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        Jogo jogo = jogoService.editarInformacoes(id, jogoAtualizado);
        return ResponseEntity.ok(jogo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            jogoService.excluirPorId(id);
            return ResponseEntity.ok("Jogo excluído com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
