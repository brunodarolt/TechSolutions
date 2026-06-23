package TechSolutions.demo.dto;

public record EnderecoDto(
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep) {
}
