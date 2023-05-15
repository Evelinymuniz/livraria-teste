package br.com.livrariateste.livrariateste.service.impl;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.entity.LivroEntity;
import br.com.livrariateste.livrariateste.model.mapper.LivroMapper;
import br.com.livrariateste.livrariateste.repository.LivroRepository;
import br.com.livrariateste.livrariateste.service.LivroServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroServiceInterface {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroMapper mapper;


    @Override
    public LivroEntity pegarPorId(Integer id) {
        Optional<LivroEntity> obj = repository.findById(id);
        return obj.orElseThrow();
    }

    @Override
    public List<LivroEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public LivroEntity create(LivroDTO obj) {
        return repository.save(mapper.update(obj));
    }

    @Override
    public void delete(Integer id) {
        pegarPorId(id);
        repository.deleteById(id);

    }
}
