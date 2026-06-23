package TechSolutions.demo.dto;

import TechSolutions.demo.model.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusRequest(
        @NotNull(message = "status e obrigatorio") StatusPedido status) {
}
