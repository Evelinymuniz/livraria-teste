package br.com.livrariateste.livrariateste.service;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.entity.LivroEntity;

import java.util.List;

public interface LivroServiceInterface {
    LivroEntity pegarPorId(Integer id);
    List<LivroEntity> findAll();
    LivroEntity create(LivroDTO obj);
    void delete(Integer id);
}
