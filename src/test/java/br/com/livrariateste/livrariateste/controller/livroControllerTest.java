package br.com.livrariateste.livrariateste.controller;

import br.com.livrariateste.livrariateste.model.dto.LivroDTO;
import br.com.livrariateste.livrariateste.model.entity.LivroEntity;
import br.com.livrariateste.livrariateste.model.mapper.LivroMapper;
import br.com.livrariateste.livrariateste.service.impl.LivroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.h2.store.fs.FilePath.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.cs.Surrogate.is;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LivroControllerTest {
    private static final Integer ID     = 1;
    private static final Integer INDEX  = 0;
    private static final String TITULO  = "A Guerra dos Tronos : As Crônicas de Gelo e Fogo, volume 1";
    private static final String RESUMO  = "A guerra dos tronos é o primeiro livro da série best-seller internacional As Crônicas de Gelo e Fogo, que deu origem à adaptação de sucesso da HBO, Game of Thrones. O verão pode durar décadas. O inverno, toda uma vida. E a guerra dos tronos começou. Como Guardião do Norte, lorde Eddard Stark não fica feliz quando o rei Robert o proclama a nova Mão do Rei";
    private static final String SUMARIO = "As Crônicas de Gelo e Fogo";
    private static final String PAGINAS = "100";
    private static final Long ISBN      = 8556510787L;
    private static final Double PRECO   = 69.90;
    private static final LocalDateTime LANCAMENTO = LocalDateTime.now();


    private LivroEntity livro = new LivroEntity();
    private LivroDTO livroDTO = new LivroDTO();


    @InjectMocks
    private LivroController controller;
    @Mock
    private LivroServiceImpl service;
    @Mock
    private LivroMapper mapper;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startLivro();
    }

    @Test
    public void testFindById() throws Exception {
        livroDTO.setId(ID);
        livroDTO.setTitulo(TITULO);

        // Salvar o livro no serviço
        service.create(livroDTO);


        mockMvc.perform(get("/livros/{id}", ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titulo", is(Integer.parseInt(TITULO))));
    }

    @Test
    void whenFindByIdThenReturnSuccess(){
        when(service.findById(anyInt())).thenReturn(livro);
        when(mapper.update(livroDTO));

        ResponseEntity<Object> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getBody().getClass());

    }

    @Test
    void whenFindAllThenReturnAListOfLivroDTO() {
        when(service.findAll()).thenReturn(List.of(livro));
        when(mapper.update(livroDTO));

        ResponseEntity<Object> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
       // assertEquals(LivroDTO.class, response.getBody().g);


    }

    @Test
    @DisplayName("Test save with success")
    void testSaveWithSuccess() {
        when(service.create(any())).thenReturn(livro);

        ResponseEntity<Object> response = controller.create(livroDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }


    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<Object> response = controller.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyInt());
    }

    private void startLivro() {
        livro = new LivroEntity(ID, TITULO, RESUMO, SUMARIO, PRECO, PAGINAS, ISBN, LANCAMENTO);
        livroDTO = new LivroDTO(ID, TITULO, RESUMO, SUMARIO, PRECO, PAGINAS, ISBN, LANCAMENTO);
    }
}