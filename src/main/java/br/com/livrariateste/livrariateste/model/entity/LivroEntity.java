package br.com.livrariateste.livrariateste.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Table(name="livro")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String titulo;
    @Size(max = 500 )
    @NonNull
    private String resumo;
    @NonNull
    private String sumario;
    private Double preco;
    @NonNull
    @Size(min = 100)
    private String paginas;
    @NonNull
    private Long isbn;
    private LocalDateTime lancamento;

}
