import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class SistemaDeCaixa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Produto> catalogo = new HashMap<>();
        Caixa caixa = new Caixa();

        while (true) {
            System.out.println("\n=== SISTEMA DE CAIXA ===");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Registrar venda");
            System.out.println("3. Cancelar venda");
            System.out.println("4. Mostrar produtos vendidos");
            System.out.println("5. Finalizar compra");
            System.out.println("6. Ver saldo do caixa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();

                    System.out.print("Código de barras: ");
                    String codigo = scanner.nextLine();

                    double preco = 0;
                    while (true) {
                        System.out.print("Preço (use ponto para decimais): ");
                        String precoStr = scanner.nextLine();

                        try {
                            preco = Double.parseDouble(precoStr);
                            break; // saiu do loop se conseguiu converter
                        } catch (NumberFormatException e) {
                            System.out.println("Preço inválido! Digite um número válido usando ponto decimal.");
                        }
                    }

                    Produto novoProduto = new Produto(nome, codigo, preco);
                    catalogo.put(codigo, novoProduto);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Informe o código de barras do produto: ");
                    String codigoVenda = scanner.nextLine();
                    Produto produtoVenda = catalogo.get(codigoVenda);
                    if (produtoVenda != null) {
                        caixa.registrarVenda(produtoVenda);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Informe o código de barras do produto para cancelar: ");
                    String codigoCancelamento = scanner.nextLine();
                    Produto produtoCancelado = catalogo.get(codigoCancelamento);
                    if (produtoCancelado != null) {
                        caixa.cancelarVenda(produtoCancelado);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 4:
                    caixa.mostrarProdutosVendidos();
                    break;

                case 5:
                    double total = caixa.calcularTotalVenda();
                    System.out.println("Total da compra: R$" + total);
                    System.out.print("Valor recebido do cliente: ");
                    double valorRecebido = scanner.nextDouble();
                    scanner.nextLine(); // Limpa o buffer após double
                    caixa.finalizarCompra(valorRecebido);
                    break;

                case 6:
                    System.out.println("Saldo atual no caixa: R$" + caixa.getSaldo());
                    break;

                case 0:
                    System.out.println("Encerrando sistema de caixa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");



            }
        }
    }
}
