package br.com.livrariateste.livrariateste.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
    private Integer id;
    @NotBlank(message = "PREENCHA O TITULO")
    private String titulo;
    @Size(max = 500 )
    @NotBlank(message = "RESUMO NAO PODE FICAR EM BRANCO")
    private String resumo;
    @NotBlank(message = "SUMARIO NAO PODE FICAR EM BRANCO")
    private String sumario;
    private Double preco;
    @NotBlank(message = "INFORME A QUANTIDADE DE PAGINAS")
    @Size(min = 100)
    private String paginas;
    @NotBlank(message = "ISBN NAO PODE FICAR EM BRANCO")
    private Long isbn;
    private LocalDateTime lancamento;

}
