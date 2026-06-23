package TechSolutions.demo.mapper;

import TechSolutions.demo.dto.PedidoResponse;
import TechSolutions.demo.model.ItemPedido;
import TechSolutions.demo.model.Pedido;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoResponse toResponse(Pedido p) {
        List<PedidoResponse.ItemResponse> itens = p.getItens().stream()
                .map(this::toItemResponse)
                .toList();
        return new PedidoResponse(
                p.getId(),
                p.getCliente().getId(),
                p.getCliente().getNome(),
                p.getStatus().name(),
                p.getValorTotal(),
                itens);
    }

    private PedidoResponse.ItemResponse toItemResponse(ItemPedido item) {
        return new PedidoResponse.ItemResponse(
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal());
    }
}
