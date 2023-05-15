package br.com.livrariateste.livrariateste.controller;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.dto.MensagemDTO;
import br.com.livrariateste.livrariateste.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.Data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/livros")
    @Data
    @Slf4j
    public class LivroController {
        @Autowired
        private LivroService livroService;

        @GetMapping
        public ResponseEntity<Object> listar() {
            try {
                return ResponseEntity.ok(livroService.listar());
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));
            }

        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> pegarUm(@PathVariable("id") Integer id) {
            try {
                return ResponseEntity.ok(livroService.pegarPorId(id));
            } catch (EntityNotFoundException ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));

            }

        }

        @PostMapping
        public ResponseEntity<Object> criar(@RequestBody LivroDTO livroDTO) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criar(livroDTO));
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));

            }

        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> editar(@RequestBody @Valid LivroDTO livroDTO, @PathVariable("id") Integer id) {
            try {
                return ResponseEntity.ok(livroService.editar(livroDTO, id));
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deletar(@PathVariable("id") Integer id) {
            try {
                livroService.deletar(id);
                return ResponseEntity.ok(new MensagemDTO("Livro com id " + id + " removido com sucesso!"));
            } catch (EntityNotFoundException ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemDTO(ex.getMessage()));
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));

            }
        }

       /* @GetMapping("/filter")
        public ResponseEntity<Object> pegarPorNomeIbsn(
                @RequestParam(name = "titulo", defaultValue = "") String titulo,
                @RequestParam(name = "ibsn", defaultValue = "") Long ibsn) {
            try {
                return ResponseEntity.ok(livroService.filtrar(titulo, ibsn));

            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new MensagemDTO(ex.getMessage()));
            }

        }*/

    }

