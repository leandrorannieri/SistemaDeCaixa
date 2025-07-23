import java.util.ArrayList;
import java.util.List;

class Produto {
    private String nome;
    private String codigoBarras;
    private double preco;

    public Produto(String nome, String codigoBarras, double preco) {
        this.nome = nome;
        this.codigoBarras = codigoBarras;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " (Código: " + codigoBarras + ") - R$" + preco;
    }
}