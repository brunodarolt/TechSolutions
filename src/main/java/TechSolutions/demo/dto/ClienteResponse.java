package TechSolutions.demo.dto;

public record ClienteResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        EnderecoDto endereco) {
}
