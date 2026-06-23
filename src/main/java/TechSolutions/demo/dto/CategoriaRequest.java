package TechSolutions.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequest(
        @NotBlank(message = "nome e obrigatorio") String nome,
        String descricao) {
}
