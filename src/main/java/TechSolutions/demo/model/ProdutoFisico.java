package TechSolutions.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("FISICO")
public class ProdutoFisico extends Produto {

    private Double peso;
    private Double alturaCm;
    private Double larguraCm;

    public ProdutoFisico() {
        super();
    }

    public ProdutoFisico(String nome, BigDecimal preco, Integer estoque, Double peso) {
        super(nome, preco, estoque);
        this.peso = peso;
    }

    @Override
    public BigDecimal calcularFrete() {
        double pesoSeguro = peso == null ? 0.0 : peso;
        BigDecimal frete = BigDecimal.valueOf(pesoSeguro * 8.0);
        BigDecimal minimo = BigDecimal.valueOf(10.0);
        return frete.compareTo(minimo) < 0 ? minimo : frete;
    }

    @Override
    public String getTipo() {
        return "FISICO";
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(Double alturaCm) {
        this.alturaCm = alturaCm;
    }

    public Double getLarguraCm() {
        return larguraCm;
    }

    public void setLarguraCm(Double larguraCm) {
        this.larguraCm = larguraCm;
    }
}
