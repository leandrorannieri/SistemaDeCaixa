import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class SistemaDeCaixaGUI extends JFrame {

    private Map<String, Produto> catalogo;
    private Caixa caixa;

    private JTextField nomeField, codigoField, precoField;
    private JTextField codigoVendaField;
    private JTextArea produtosVendidosArea;
    private JTextField valorRecebidoField;
    private JLabel saldoLabel, totalVendaLabel, trocoLabel;

    public SistemaDeCaixaGUI(Map<String, Produto> catalogo, Caixa caixa) {
        this.catalogo = catalogo;
        this.caixa = caixa;

        setTitle("Sistema de Caixa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel);

        // Cadastro de produto
        JPanel cadastroPanel = new JPanel(new GridLayout(4, 2, 5,5));
        cadastroPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Produto"));

        cadastroPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        cadastroPanel.add(nomeField);

        cadastroPanel.add(new JLabel("Código de barras:"));
        codigoField = new JTextField();
        cadastroPanel.add(codigoField);

        cadastroPanel.add(new JLabel("Preço (use ponto):"));
        precoField = new JTextField();
        cadastroPanel.add(precoField);

        JButton btnCadastrar = new JButton("Cadastrar Produto");
        cadastroPanel.add(btnCadastrar);
        cadastroPanel.add(new JLabel(""));

        btnCadastrar.addActionListener(e -> cadastrarProduto());

        // Registro de venda
        JPanel vendaPanel = new JPanel(new GridLayout(3, 2, 5,5));
        vendaPanel.setBorder(BorderFactory.createTitledBorder("Registrar Venda"));

        vendaPanel.add(new JLabel("Código do produto:"));
        codigoVendaField = new JTextField();
        vendaPanel.add(codigoVendaField);

        JButton btnRegistrarVenda = new JButton("Registrar Venda");
        vendaPanel.add(btnRegistrarVenda);
        vendaPanel.add(new JLabel(""));

        btnRegistrarVenda.addActionListener(e -> registrarVenda());

        // Produtos vendidos
        JPanel vendidosPanel = new JPanel(new BorderLayout());
        vendidosPanel.setBorder(BorderFactory.createTitledBorder("Produtos Vendidos"));

        produtosVendidosArea = new JTextArea(10, 40);
        produtosVendidosArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(produtosVendidosArea);
        vendidosPanel.add(scroll, BorderLayout.CENTER);

        // Finalizar compra
        JPanel finalizarPanel = new JPanel(new GridLayout(4,2,5,5));
        finalizarPanel.setBorder(BorderFactory.createTitledBorder("Finalizar Compra"));

        finalizarPanel.add(new JLabel("Total da Venda:"));
        totalVendaLabel = new JLabel("R$ 0.00");
        finalizarPanel.add(totalVendaLabel);

        finalizarPanel.add(new JLabel("Valor Recebido:"));
        valorRecebidoField = new JTextField();
        finalizarPanel.add(valorRecebidoField);

        finalizarPanel.add(new JLabel("Troco:"));
        trocoLabel = new JLabel("R$ 0.00");
        finalizarPanel.add(trocoLabel);

        JButton btnFinalizar = new JButton("Finalizar Compra");
        finalizarPanel.add(btnFinalizar);
        finalizarPanel.add(new JLabel(""));

        btnFinalizar.addActionListener(e -> finalizarCompra());

        // Saldo do caixa
        JPanel saldoPanel = new JPanel();
        saldoPanel.setBorder(BorderFactory.createTitledBorder("Saldo do Caixa"));
        saldoLabel = new JLabel("R$ 0.00");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        saldoPanel.add(saldoLabel);

        // Layout principal
        JPanel topo = new JPanel(new GridLayout(1,2,10,10));
        topo.add(cadastroPanel);
        topo.add(vendaPanel);

        panel.add(topo, BorderLayout.NORTH);
        panel.add(vendidosPanel, BorderLayout.CENTER);

        JPanel base = new JPanel(new BorderLayout());
        base.add(finalizarPanel, BorderLayout.CENTER);
        base.add(saldoPanel, BorderLayout.SOUTH);

        panel.add(base, BorderLayout.SOUTH);
    }

    private void cadastrarProduto() {
        String nome = nomeField.getText().trim();
        String codigo = codigoField.getText().trim();
        String precoStr = precoField.getText().trim();

        if (nome.isEmpty() || codigo.isEmpty() || precoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos do produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido! Use ponto para decimais.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Produto p = new Produto(nome, codigo, preco);
        catalogo.put(codigo, p);
        JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

        nomeField.setText("");
        codigoField.setText("");
        precoField.setText("");
    }

    private void registrarVenda() {
        String codigo = codigoVendaField.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o código do produto para venda.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Produto p = catalogo.get(codigo);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado no catálogo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        caixa.registrarVenda(p);
        atualizarProdutosVendidos();
        atualizarTotalVenda();
        atualizarSaldo();

        codigoVendaField.setText("");
    }

    private void atualizarProdutosVendidos() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : caixa.getProdutosVendidos()) {
            sb.append(p).append("\n");
        }
        produtosVendidosArea.setText(sb.toString());
    }

    private void atualizarTotalVenda() {
        double total = caixa.calcularTotalVenda();
        totalVendaLabel.setText(String.format("R$ %.2f", total));
    }

    private void atualizarSaldo() {
        saldoLabel.setText(String.format("R$ %.2f", caixa.getSaldo()));
    }

    private void finalizarCompra() {
        String valorRecebidoStr = valorRecebidoField.getText().trim();

        if (valorRecebidoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o valor recebido do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valorRecebido;
        try {
            valorRecebido = Double.parseDouble(valorRecebidoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor recebido inválido! Use ponto para decimais.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double total = caixa.calcularTotalVenda();

        if (valorRecebido < total) {
            JOptionPane.showMessageDialog(this, String.format("Valor insuficiente. Faltam R$ %.2f", total - valorRecebido), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double troco = valorRecebido - total;
        trocoLabel.setText(String.format("R$ %.2f", troco));
        caixa.finalizarCompra(valorRecebido);

        atualizarProdutosVendidos();
        atualizarTotalVenda();
        atualizarSaldo();

        valorRecebidoField.setText("");
        JOptionPane.showMessageDialog(this, String.format("Compra finalizada! Troco: R$ %.2f", troco));
    }

}
