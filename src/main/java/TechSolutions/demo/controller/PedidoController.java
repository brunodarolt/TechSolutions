package TechSolutions.demo.controller;

import TechSolutions.demo.dto.AtualizarStatusRequest;
import TechSolutions.demo.dto.PedidoRequest;
import TechSolutions.demo.dto.PedidoResponse;
import TechSolutions.demo.mapper.PedidoMapper;
import TechSolutions.demo.model.Pedido;
import TechSolutions.demo.service.PedidoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;
    private final PedidoMapper mapper;

    public PedidoController(PedidoService service, PedidoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@Valid @RequestBody PedidoRequest req) {
        List<PedidoService.ItemEntrada> itens = req.itens().stream()
                .map(i -> new PedidoService.ItemEntrada(i.produtoId(), i.quantidade()))
                .toList();
        Pedido pedido = service.criar(req.clienteId(), itens);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(pedido));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable).map(mapper::toResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.buscarPorId(id)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> atualizarStatus(
            @PathVariable Long id, @Valid @RequestBody AtualizarStatusRequest req) {
        Pedido pedido = service.atualizarStatus(id, req.status());
        return ResponseEntity.ok(mapper.toResponse(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
