package TechSolutions.demo.mapper;

import TechSolutions.demo.dto.ProdutoRequest;
import TechSolutions.demo.dto.ProdutoResponse;
import TechSolutions.demo.exception.BadRequestException;
import TechSolutions.demo.model.Categoria;
import TechSolutions.demo.model.Produto;
import TechSolutions.demo.model.ProdutoDigital;
import TechSolutions.demo.model.ProdutoFisico;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequest req) {
        String tipo = req.tipo() == null ? "" : req.tipo().trim().toUpperCase();
        Produto produto = switch (tipo) {
            case "FISICO" -> {
                ProdutoFisico f = new ProdutoFisico();
                f.setPeso(req.peso());
                f.setAlturaCm(req.alturaCm());
                f.setLarguraCm(req.larguraCm());
                yield f;
            }
            case "DIGITAL" -> {
                ProdutoDigital d = new ProdutoDigital();
                d.setTamanhoMb(req.tamanhoMb());
                d.setUrlDownload(req.urlDownload());
                yield d;
            }
            default -> throw new BadRequestException(
                    "tipo invalido: use FISICO ou DIGITAL");
        };
        produto.setNome(req.nome());
        produto.setDescricao(req.descricao());
        produto.setPreco(req.preco());
        produto.setEstoque(req.estoque());
        if (req.categoriaId() != null) {
            Categoria c = new Categoria();
            c.setId(req.categoriaId());
            produto.setCategoria(c);
        }
        return produto;
    }

    public ProdutoResponse toResponse(Produto p) {
        Long categoriaId = p.getCategoria() != null ? p.getCategoria().getId() : null;
        String categoriaNome = p.getCategoria() != null ? p.getCategoria().getNome() : null;

        Double peso = null, alturaCm = null, larguraCm = null, tamanhoMb = null;
        String urlDownload = null;
        if (p instanceof ProdutoFisico f) {
            peso = f.getPeso();
            alturaCm = f.getAlturaCm();
            larguraCm = f.getLarguraCm();
        } else if (p instanceof ProdutoDigital d) {
            tamanhoMb = d.getTamanhoMb();
            urlDownload = d.getUrlDownload();
        }

        return new ProdutoResponse(
                p.getId(), p.getTipo(), p.getNome(), p.getDescricao(),
                p.getPreco(), p.getEstoque(), p.calcularFrete(),
                categoriaId, categoriaNome,
                peso, alturaCm, larguraCm, tamanhoMb, urlDownload);
    }
}
