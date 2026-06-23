package TechSolutions.demo.mapper;

import TechSolutions.demo.dto.ClienteRequest;
import TechSolutions.demo.dto.ClienteResponse;
import TechSolutions.demo.dto.EnderecoDto;
import TechSolutions.demo.model.Cliente;
import TechSolutions.demo.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest req) {
        Cliente cliente = new Cliente(req.nome(), req.email());
        cliente.setTelefone(req.telefone());
        cliente.setEndereco(toEndereco(req.endereco()));
        return cliente;
    }

    public ClienteResponse toResponse(Cliente c) {
        return new ClienteResponse(
                c.getId(), c.getNome(), c.getEmail(), c.getTelefone(),
                toEnderecoDto(c.getEndereco()));
    }

    private Endereco toEndereco(EnderecoDto dto) {
        if (dto == null) {
            return null;
        }
        Endereco e = new Endereco();
        e.setLogradouro(dto.logradouro());
        e.setNumero(dto.numero());
        e.setBairro(dto.bairro());
        e.setCidade(dto.cidade());
        e.setEstado(dto.estado());
        e.setCep(dto.cep());
        return e;
    }

    private EnderecoDto toEnderecoDto(Endereco e) {
        if (e == null) {
            return null;
        }
        return new EnderecoDto(e.getLogradouro(), e.getNumero(), e.getBairro(),
                e.getCidade(), e.getEstado(), e.getCep());
    }
}
