package TechSolutions.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoRequest(
        @NotNull(message = "clienteId e obrigatorio") Long clienteId,
        @NotEmpty(message = "o pedido deve ter ao menos um item") List<ItemRequest> itens) {

    public record ItemRequest(
            @NotNull(message = "produtoId e obrigatorio") Long produtoId,
            @NotNull(message = "quantidade e obrigatoria") Integer quantidade) {
    }
}
