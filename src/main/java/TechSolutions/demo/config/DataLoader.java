package TechSolutions.demo.config;

import TechSolutions.demo.model.Categoria;
import TechSolutions.demo.model.Cliente;
import TechSolutions.demo.model.Endereco;
import TechSolutions.demo.model.ProdutoDigital;
import TechSolutions.demo.model.ProdutoFisico;
import TechSolutions.demo.repository.CategoriaRepository;
import TechSolutions.demo.repository.ClienteRepository;
import TechSolutions.demo.repository.ProdutoRepository;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoriaRepository categoriaRepo;
    private final ProdutoRepository produtoRepo;
    private final ClienteRepository clienteRepo;

    public DataLoader(CategoriaRepository categoriaRepo, ProdutoRepository produtoRepo,
                      ClienteRepository clienteRepo) {
        this.categoriaRepo = categoriaRepo;
        this.produtoRepo = produtoRepo;
        this.clienteRepo = clienteRepo;
    }

    @Override
    public void run(String... args) {
        if (categoriaRepo.count() > 0) {
            return;
        }

        Categoria eletronicos = categoriaRepo.save(
                new Categoria("Eletronicos", "Aparelhos e acessorios"));
        Categoria livros = categoriaRepo.save(
                new Categoria("Livros", "Fisicos e digitais"));

        ProdutoFisico mouse = new ProdutoFisico("Mouse Gamer", new BigDecimal("129.90"), 50, 0.2);
        mouse.setCategoria(eletronicos);
        mouse.setDescricao("Mouse optico 7200 DPI");
        produtoRepo.save(mouse);

        ProdutoFisico teclado = new ProdutoFisico("Teclado Mecanico", new BigDecimal("299.00"), 30, 0.9);
        teclado.setCategoria(eletronicos);
        produtoRepo.save(teclado);

        ProdutoDigital ebook = new ProdutoDigital(
                "E-book Java Moderno", new BigDecimal("49.90"), 999,
                "https://compreaqui.local/downloads/java.pdf");
        ebook.setCategoria(livros);
        ebook.setTamanhoMb(12.5);
        produtoRepo.save(ebook);

        Endereco endereco = new Endereco("Rua das Flores", "100", "Florianopolis", "SC", "88000-000");
        endereco.setBairro("Centro");
        Cliente cliente = new Cliente("Maria Souza", "maria@email.com", "(48) 99999-0000", endereco);
        clienteRepo.save(cliente);
    }
}
