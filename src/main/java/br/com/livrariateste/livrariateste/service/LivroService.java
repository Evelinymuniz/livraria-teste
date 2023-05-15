package br.com.livrariateste.livrariateste.service;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.entity.LivroEntity;
import br.com.livrariateste.livrariateste.model.mapper.LivroMapper;
import br.com.livrariateste.livrariateste.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroMapper mapper;

    public LivroDTO pegarPorId(Integer id) {

        Optional<LivroEntity> livroEntityOptional =
                repository.findById(id);

        if(livroEntityOptional.isPresent()) {
            LivroEntity livroEntity = livroEntityOptional.get();
            return mapper.update(livroEntity);
        }

        throw new EntityNotFoundException("livro não encontrada");
    }


    public LivroDTO criar(LivroDTO livroDTO){
        LivroEntity livro =  mapper.update(livroDTO);
        livro = repository.save(livro);
        return mapper.update(livro);
    }
    public LivroDTO editar(LivroDTO livroDTO, Integer id){

        if (repository.existsById(id)){
            LivroEntity livroEntity = mapper.update(livroDTO);
            livroEntity.setId(id);
            livroEntity = repository.save(livroEntity);

            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Livro não encontrada");
    }
    public void deletar(Integer id){
        Optional<LivroEntity> livroEntityOptional = repository.findById(id);

        if (livroEntityOptional.isPresent()){
            LivroEntity livroEntity = livroEntityOptional.get();
            repository.delete(livroEntity);
            return;
        }
        throw new EntityNotFoundException("Livro não encontrada");

    }
    public List<LivroDTO> listar(){
        List <LivroEntity> listaEntities = repository.findAll();
        return mapper.updateListDTO(listaEntities);

    }


}
