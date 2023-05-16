package br.com.livrariateste.livrariateste.service.impl;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.entity.LivroEntity;
import br.com.livrariateste.livrariateste.model.mapper.LivroMapper;
import br.com.livrariateste.livrariateste.repository.LivroRepository;
import br.com.livrariateste.livrariateste.service.exceptionsService.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class LivroServiceImplTest {
    private static final Integer ID      = 1;
    private static final Integer INDEX   = 0;
    private static final String TITULO  = "A Guerra dos Tronos : As Crônicas de Gelo e Fogo, volume 1";
    private static final String RESUMO     = "A guerra dos tronos é o primeiro livro da série best-seller internacional As Crônicas de Gelo e Fogo, que deu origem à adaptação de sucesso da HBO, Game of Thrones. O verão pode durar décadas. O inverno, toda uma vida. E a guerra dos tronos começou. Como Guardião do Norte, lorde Eddard Stark não fica feliz quando o rei Robert o proclama a nova Mão do Rei";
    private static final String SUMARIO    = "As Crônicas de Gelo e Fogo";
    private static final String PAGINAS = "100";
    private static final Long IBSN = 8556510787L;
    private static final Double PRECO = 69.90;
    private static final LocalDateTime LANCAMENTO = LocalDateTime.now();
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";


    @InjectMocks
    private LivroServiceImpl service;

    @Mock
    private LivroRepository repository;

    @Mock
    private LivroMapper mapper;

    private LivroEntity livro;
    private LivroDTO livroDTO;
    private Optional<LivroEntity> optionalLivro;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }



    @Test
    void whenFindByIdReturnBook() {
        when(repository.findById(anyInt())).thenReturn(optionalLivro);

        LivroEntity response = service.findById(ID);

        assertNotNull(response);

        assertNotNull(response);
        assertEquals(LivroEntity.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(RESUMO, response.getResumo());
        assertEquals(SUMARIO, response.getSumario());
        assertEquals(PAGINAS, response.getPaginas());
        assertEquals(IBSN, response.getIsbn());
        assertEquals(PRECO, response.getPreco());
        assertEquals(LANCAMENTO, response.getLancamento());


    }

    @Test
    void whenFindByIdReturnObjectNotFoundException(){

        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }

    }

    @Test
    void FindAllReturnAnListOfBooks() {

        when(repository.findAll()).thenReturn(List.of(livro));
        List<LivroEntity> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(LivroEntity.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(TITULO, response.get(INDEX).getTitulo());
        assertEquals(RESUMO, response.get(INDEX).getResumo());
        assertEquals(SUMARIO, response.get(INDEX).getSumario());
        assertEquals(PAGINAS, response.get(INDEX).getPaginas());
        assertEquals(IBSN, response.get(INDEX).getIsbn());
        assertEquals(PRECO, response.get(INDEX).getPreco());
        assertEquals(LANCAMENTO, response.get(INDEX).getLancamento());


    }

    @Test
    void createSucess() {
        when(repository.save(any())).thenReturn(livro);

        LivroEntity response = service.create(livroDTO);

        assertNotNull(response);
        assertEquals(LivroEntity.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(RESUMO, response.getResumo());
        assertEquals(SUMARIO, response.getSumario());
        assertEquals(PAGINAS, response.getPaginas());
        assertEquals(IBSN, response.getIsbn());
        assertEquals(PRECO, response.getPreco());
        assertEquals(LANCAMENTO, response.getLancamento());

    }

    @Test
    void deleteSucess() {

        when(repository.findById(anyInt())).thenReturn(optionalLivro);

        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);

    }
    @Test
    void deleteReturnObjectNotFoundException() {

        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }


    private void startUser() {
        livro = new LivroEntity(ID, TITULO, RESUMO, SUMARIO, PRECO, PAGINAS, IBSN, LANCAMENTO);
        livroDTO = new LivroDTO(ID, TITULO, RESUMO, SUMARIO, PRECO, PAGINAS, IBSN, LANCAMENTO);
        optionalLivro = Optional.of(new LivroEntity(ID, TITULO, RESUMO, SUMARIO, PRECO, PAGINAS, IBSN, LANCAMENTO));
    }
}