package TechSolutions.demo.repository;

import TechSolutions.demo.model.Produto;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

public final class ProdutoSpecs {

    private ProdutoSpecs() {
    }

    private static Specification<Produto> sempreVerdadeiro() {
        return (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Produto> nomeContem(String nome) {
        if (nome == null || nome.isBlank()) {
            return sempreVerdadeiro();
        }
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Produto> daCategoria(Long categoriaId) {
        if (categoriaId == null) {
            return sempreVerdadeiro();
        }
        return (root, query, cb) -> cb.equal(root.get("categoria").get("id"), categoriaId);
    }

    public static Specification<Produto> precoMaiorIgual(BigDecimal precoMin) {
        if (precoMin == null) {
            return sempreVerdadeiro();
        }
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("preco"), precoMin);
    }

    public static Specification<Produto> precoMenorIgual(BigDecimal precoMax) {
        if (precoMax == null) {
            return sempreVerdadeiro();
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("preco"), precoMax);
    }
}
