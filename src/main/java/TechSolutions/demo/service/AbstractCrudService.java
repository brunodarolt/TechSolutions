package TechSolutions.demo.service;

import TechSolutions.demo.exception.ResourceNotFoundException;
import TechSolutions.demo.model.BaseEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService<T extends BaseEntity>
        implements CrudService<T, Long> {

    private final JpaRepository<T, Long> repository;
    private final String nomeRecurso;

    protected AbstractCrudService(JpaRepository<T, Long> repository, String nomeRecurso) {
        this.repository = repository;
        this.nomeRecurso = nomeRecurso;
    }

    @Override
    public T criar(T entidade) {
        return repository.save(entidade);
    }

    @Override
    public List<T> listar() {
        return repository.findAll();
    }

    @Override
    public Page<T> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(nomeRecurso, id));
    }

    @Override
    public T atualizar(Long id, T dados) {
        T existente = buscarPorId(id);
        copiarDados(existente, dados);
        return repository.save(existente);
    }

    @Override
    public void deletar(Long id) {
        T existente = buscarPorId(id);
        repository.delete(existente);
    }

    protected abstract void copiarDados(T destino, T origem);

    protected JpaRepository<T, Long> getRepository() {
        return repository;
    }
}
