package TechSolutions.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "tipo e obrigatorio (FISICO ou DIGITAL)") String tipo,
        @NotBlank(message = "nome e obrigatorio") String nome,
        String descricao,
        @NotNull(message = "preco e obrigatorio")
        @Positive(message = "preco deve ser maior que zero") BigDecimal preco,
        @NotNull(message = "estoque e obrigatorio")
        @PositiveOrZero(message = "estoque nao pode ser negativo") Integer estoque,
        @NotNull(message = "categoriaId e obrigatorio") Long categoriaId,
        Double peso,
        Double alturaCm,
        Double larguraCm,
        Double tamanhoMb,
        String urlDownload) {
}
