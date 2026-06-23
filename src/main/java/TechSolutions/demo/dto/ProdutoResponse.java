package TechSolutions.demo.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String tipo,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer estoque,
        BigDecimal frete,
        Long categoriaId,
        String categoriaNome,
        Double peso,
        Double alturaCm,
        Double larguraCm,
        Double tamanhoMb,
        String urlDownload) {
}
