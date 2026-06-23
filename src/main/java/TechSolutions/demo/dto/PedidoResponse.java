package TechSolutions.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponse(
        Long id,
        Long clienteId,
        String clienteNome,
        String status,
        BigDecimal valorTotal,
        List<ItemResponse> itens) {

    public record ItemResponse(
            Long produtoId,
            String produtoNome,
            Integer quantidade,
            BigDecimal precoUnitario,
            BigDecimal subtotal) {
    }
}
