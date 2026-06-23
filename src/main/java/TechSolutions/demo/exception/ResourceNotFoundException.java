package TechSolutions.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ResourceNotFoundException(String recurso, Long id) {
        super(recurso + " nao encontrado(a) para o id " + id);
    }
}
