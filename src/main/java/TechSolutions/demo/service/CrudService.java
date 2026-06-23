package TechSolutions.demo.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {

    T criar(T entidade);

    List<T> listar();

    Page<T> listar(Pageable pageable);

    T buscarPorId(ID id);

    T atualizar(ID id, T entidade);

    void deletar(ID id);
}
