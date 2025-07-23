# Sistema de Caixa em Java

Este é um sistema simples de caixa desenvolvido em Java, com interface gráfica (GUI) usando Swing, que permite:

- Cadastrar produtos com nome, código de barras e preço  
- Registrar vendas de produtos pelo código de barras  
- Cancelar vendas  
- Mostrar produtos vendidos na venda atual  
- Calcular o total da venda  
- Receber o valor pago pelo cliente e calcular o troco  
- Visualizar o saldo total acumulado no caixa  

---

## Funcionalidades

- **Cadastro de produtos:** Permite adicionar produtos ao catálogo com código, nome e preço.  
- **Registro de vendas:** Adiciona produtos à venda atual pelo código de barras.  
- **Cancelamento de vendas:** Remove produtos da venda atual pelo código.  
- **Finalização da compra:** Calcula o valor total, recebe o pagamento do cliente, calcula e exibe o troco, e limpa a venda atual.  
- **Saldo do caixa:** Mostra o saldo acumulado das vendas realizadas.  
- **Interface gráfica:** Utiliza Swing para uma experiência visual simples e funcional.  

---

## Estrutura do projeto

- `Produto.java` - Classe que representa um produto com atributos nome, código de barras e preço.  
- `Caixa.java` - Classe responsável por registrar vendas, calcular totais, manter saldo e gerenciar produtos vendidos.  
- `SistemaDeCaixaGUI.java` - Interface gráfica com formulários para cadastro, venda e finalização.  
- `MainGUI.java` - Classe principal que configura o tema visual e inicia a interface gráfica.  

---

## Como executar

1. Clone ou faça download do projeto.  
2. Compile todas as classes Java:

```bash
java
