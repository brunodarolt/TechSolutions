package TechSolutions.demo.service;

import TechSolutions.demo.exception.BadRequestException;
import TechSolutions.demo.exception.ResourceNotFoundException;
import TechSolutions.demo.model.Cliente;
import TechSolutions.demo.model.ItemPedido;
import TechSolutions.demo.model.Pedido;
import TechSolutions.demo.model.Produto;
import TechSolutions.demo.model.StatusPedido;
import TechSolutions.demo.repository.ClienteRepository;
import TechSolutions.demo.repository.PedidoRepository;
import TechSolutions.demo.repository.ProdutoRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    public record ItemEntrada(Long produtoId, Integer quantidade) {
    }

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Page<Pedido> listar(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
    }

    @Transactional
    public Pedido criar(Long clienteId, List<ItemEntrada> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new BadRequestException("O pedido deve conter ao menos um item");
        }
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", clienteId));

        Pedido pedido = new Pedido(cliente);
        for (ItemEntrada entrada : itens) {
            if (entrada.quantidade() == null || entrada.quantidade() <= 0) {
                throw new BadRequestException("Quantidade invalida no item do pedido");
            }
            Produto produto = produtoRepository.findById(entrada.produtoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto", entrada.produtoId()));
            produto.baixarEstoque(entrada.quantidade());
            pedido.adicionarItem(new ItemPedido(produto, entrada.quantidade()));
        }
        pedido.recalcularTotal();
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        if (novoStatus == null) {
            throw new BadRequestException("Status do pedido e obrigatorio");
        }
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }
}
