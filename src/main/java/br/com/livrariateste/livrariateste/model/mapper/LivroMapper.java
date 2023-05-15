package br.com.livrariateste.livrariateste.model.mapper;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.entity.LivroEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {
    public LivroDTO update(LivroEntity livro){

        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setResumo(livro.getResumo());
        livroDTO.setSumario(livro.getSumario());
        livroDTO.setPreco(livro.getPreco());
        livroDTO.setPaginas(livro.getPaginas());
        livroDTO.setIsbn(livro.getIsbn());
        livroDTO.setLancamento(livro.getLancamento());
        return livroDTO;

    }
    public LivroEntity update(LivroDTO livro){

        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setId(livro.getId());
        livroEntity.setTitulo(livro.getTitulo());
        livroEntity.setResumo(livro.getResumo());
        livroEntity.setSumario(livro.getSumario());
        livroEntity.setPreco(livro.getPreco());
        livroEntity.setPaginas(livro.getPaginas());
        livroEntity.setIsbn(livro.getIsbn());
        livroEntity.setLancamento(livro.getLancamento());
        return livroEntity;

    }

    public List<LivroEntity> updateListEntity(List<LivroDTO> listaDTO){
        return listaDTO.stream().map(this::update).toList();
    }
    public List <LivroDTO> updateListDTO(List<LivroEntity> listaEntity){
        return listaEntity.stream().map(this::update).toList();
    }

}

