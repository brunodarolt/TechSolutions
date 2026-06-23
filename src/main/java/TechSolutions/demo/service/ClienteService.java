package TechSolutions.demo.service;

import TechSolutions.demo.exception.BadRequestException;
import TechSolutions.demo.model.Cliente;
import TechSolutions.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends AbstractCrudService<Cliente> {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        super(clienteRepository, "Cliente");
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new BadRequestException(
                    "Ja existe um cliente com o e-mail '" + cliente.getEmail() + "'");
        }
        return super.criar(cliente);
    }

    @Override
    protected void copiarDados(Cliente destino, Cliente origem) {
        destino.setNome(origem.getNome());
        destino.setTelefone(origem.getTelefone());
        destino.setEndereco(origem.getEndereco());
    }
}
