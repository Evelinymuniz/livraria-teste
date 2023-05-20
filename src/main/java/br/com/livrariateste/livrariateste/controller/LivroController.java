package br.com.livrariateste.livrariateste.controller;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.dto.MensagemDTO;
import br.com.livrariateste.livrariateste.service.impl.LivroServiceImpl;
import jakarta.persistence.EntityNotFoundException;
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
        private LivroServiceImpl livroService;


      /*  @GetMapping(value = "/{id}")
        public ResponseEntity<Object> findById(@PathVariable Integer id) {
            return ResponseEntity.ok().body((livroService.findById(id)));
        }*/

        @GetMapping
        public ResponseEntity<Object> findAll() {
            try {
                return ResponseEntity.ok(livroService.findAll());
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));
            }

        }

        @PostMapping
        public ResponseEntity<Object> create(@RequestBody LivroDTO livroDTO) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(livroService.create(livroDTO));
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));

            }

        }

       /* @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
            try {
                livroService.delete(id);
                return ResponseEntity.ok(new MensagemDTO("Livro com id " + id + " removido com sucesso!"));
            } catch (EntityNotFoundException ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemDTO(ex.getMessage()));
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO(ex.getMessage()));

            }
        }*/

    }

