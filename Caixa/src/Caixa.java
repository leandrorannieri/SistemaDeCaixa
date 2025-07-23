import java.util.ArrayList;
import java.util.List;

public class Caixa {

    private List<Produto> produtosVendidos = new ArrayList<>();
    private double saldo = 0;

    // Registra venda (adiciona produto na lista e atualiza saldo)
    public void registrarVenda(Produto produto) {
        produtosVendidos.add(produto);
        saldo += produto.getPreco();
    }

    // Cancela venda (remove produto da lista e atualiza saldo)
    public void cancelarVenda(Produto produto) {
        if (produtosVendidos.remove(produto)) {
            saldo -= produto.getPreco();
        }
    }

    // Calcula total da venda atual (soma preços dos produtos vendidos)
    public double calcularTotalVenda() {
        double total = 0;
        for (Produto p : produtosVendidos) {
            total += p.getPreco();
        }
        return total;
    }

    // Retorna o saldo atual do caixa
    public double getSaldo() {
        return saldo;
    }

    // Retorna a lista de produtos vendidos
    public List<Produto> getProdutosVendidos() {
        return produtosVendidos;
    }

    // Finaliza a compra: limpa os produtos vendidos (reseta a venda atual)
    public void finalizarCompra(double valorRecebido) {
        produtosVendidos.clear();
        // O saldo permanece, pois é acumulado
    }

    // Exibe os produtos vendidos no console
    public void mostrarProdutosVendidos() {
        if (produtosVendidos.isEmpty()) {
            System.out.println("Nenhum produto vendido ainda.");
            return;
        }
        System.out.println("Produtos vendidos:");
        for (Produto p : produtosVendidos) {
            System.out.println(p);
        }
    }
}
