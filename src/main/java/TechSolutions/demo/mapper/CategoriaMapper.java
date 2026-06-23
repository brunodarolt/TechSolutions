package TechSolutions.demo.mapper;

import TechSolutions.demo.dto.CategoriaRequest;
import TechSolutions.demo.dto.CategoriaResponse;
import TechSolutions.demo.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest req) {
        return new Categoria(req.nome(), req.descricao());
    }

    public CategoriaResponse toResponse(Categoria c) {
        return new CategoriaResponse(c.getId(), c.getNome(), c.getDescricao());
    }
}
