package TechSolutions.demo.service;

import TechSolutions.demo.exception.BadRequestException;
import TechSolutions.demo.exception.ResourceNotFoundException;
import TechSolutions.demo.model.Categoria;
import TechSolutions.demo.model.Produto;
import TechSolutions.demo.model.ProdutoDigital;
import TechSolutions.demo.model.ProdutoFisico;
import TechSolutions.demo.repository.CategoriaRepository;
import TechSolutions.demo.repository.ProdutoRepository;
import TechSolutions.demo.repository.ProdutoSpecs;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends AbstractCrudService<Produto> {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository) {
        super(produtoRepository, "Produto");
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Produto criar(Produto produto) {
        vincularCategoria(produto);
        return super.criar(produto);
    }

    public Page<Produto> buscar(String nome, Long categoriaId,
                                BigDecimal precoMin, BigDecimal precoMax,
                                Pageable pageable) {
        Specification<Produto> spec = Specification
                .allOf(ProdutoSpecs.nomeContem(nome),
                        ProdutoSpecs.daCategoria(categoriaId),
                        ProdutoSpecs.precoMaiorIgual(precoMin),
                        ProdutoSpecs.precoMenorIgual(precoMax));
        return produtoRepository.findAll(spec, pageable);
    }

    @Override
    protected void copiarDados(Produto destino, Produto origem) {
        destino.setNome(origem.getNome());
        destino.setDescricao(origem.getDescricao());
        destino.setPreco(origem.getPreco());
        destino.setEstoque(origem.getEstoque());
        if (origem.getCategoria() != null) {
            destino.setCategoria(buscarCategoria(origem.getCategoria().getId()));
        }
        if (destino instanceof ProdutoFisico df && origem instanceof ProdutoFisico of) {
            df.setPeso(of.getPeso());
            df.setAlturaCm(of.getAlturaCm());
            df.setLarguraCm(of.getLarguraCm());
        } else if (destino instanceof ProdutoDigital dd && origem instanceof ProdutoDigital od) {
            dd.setTamanhoMb(od.getTamanhoMb());
            dd.setUrlDownload(od.getUrlDownload());
        }
    }

    private void vincularCategoria(Produto produto) {
        if (produto.getCategoria() != null && produto.getCategoria().getId() != null) {
            produto.setCategoria(buscarCategoria(produto.getCategoria().getId()));
        }
    }

    private Categoria buscarCategoria(Long id) {
        if (id == null) {
            throw new BadRequestException("Categoria do produto e obrigatoria");
        }
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
    }
}
