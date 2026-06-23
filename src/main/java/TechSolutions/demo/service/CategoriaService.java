package TechSolutions.demo.service;

import TechSolutions.demo.exception.BadRequestException;
import TechSolutions.demo.model.Categoria;
import TechSolutions.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends AbstractCrudService<Categoria> {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        super(categoriaRepository, "Categoria");
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria criar(Categoria categoria) {
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new BadRequestException(
                    "Ja existe uma categoria com o nome '" + categoria.getNome() + "'");
        }
        return super.criar(categoria);
    }

    @Override
    protected void copiarDados(Categoria destino, Categoria origem) {
        destino.setNome(origem.getNome());
        destino.setDescricao(origem.getDescricao());
    }
}
