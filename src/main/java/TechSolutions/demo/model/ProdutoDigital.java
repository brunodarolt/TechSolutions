package TechSolutions.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DIGITAL")
public class ProdutoDigital extends Produto {

    private Double tamanhoMb;
    private String urlDownload;

    public ProdutoDigital() {
        super();
    }

    public ProdutoDigital(String nome, BigDecimal preco, Integer estoque, String urlDownload) {
        super(nome, preco, estoque);
        this.urlDownload = urlDownload;
    }

    @Override
    public BigDecimal calcularFrete() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getTipo() {
        return "DIGITAL";
    }

    public Double getTamanhoMb() {
        return tamanhoMb;
    }

    public void setTamanhoMb(Double tamanhoMb) {
        this.tamanhoMb = tamanhoMb;
    }

    public String getUrlDownload() {
        return urlDownload;
    }

    public void setUrlDownload(String urlDownload) {
        this.urlDownload = urlDownload;
    }
}
