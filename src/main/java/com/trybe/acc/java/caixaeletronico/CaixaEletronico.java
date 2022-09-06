package com.trybe.acc.java.caixaeletronico;

import java.util.Scanner;

/**
 * The type Caixa eletronico.
 */
public class CaixaEletronico {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    Banco banco = new Banco();

    /* adiciona algumas pessoas clientes ao banco criando ja uma conta poupanca
     * e em seguida adiciona uma conta-corrente para essas pessoas
     */

    // pessoa cliente 1
    PessoaCliente pessoaCliente1 = banco.adicionarPessoaCliente(
        "Thauler", "123.456.789-10", "123456");

    Conta checkingAccount1 = new Conta("Corrente", pessoaCliente1, banco);
    Conta savingAccount1 = new Conta("Poupança", pessoaCliente1, banco);
    pessoaCliente1.adicionarConta(checkingAccount1);
    pessoaCliente1.adicionarConta(savingAccount1);

    // pessoa cliente 2
    PessoaCliente pessoaCliente2 = banco.adicionarPessoaCliente(
        "VHBS", "848.725.510-87", "654321");

    Conta checkingAccount2 = new Conta("Corrente", pessoaCliente2, banco);
    Conta savingAccount2 = new Conta("Poupança", pessoaCliente2, banco);
    pessoaCliente2.adicionarConta(checkingAccount2);
    pessoaCliente2.adicionarConta(savingAccount2);

    // pessoa cliente 3
    PessoaCliente pessoaCliente3 = banco.adicionarPessoaCliente(
        "Brunão", "433.892.200-11", "789012");

    Conta checkingAccount3 = new Conta("Corrente", pessoaCliente3, banco);
    Conta savingAccount3 = new Conta("Poupança", pessoaCliente3, banco);
    pessoaCliente3.adicionarConta(checkingAccount3);
    pessoaCliente3.adicionarConta(savingAccount3);

    // laco infinito
    while (true) {

      System.out.println("\n\nBem-vindo ao Banco da Trybe\n\n");
      System.out.print("Entre com seu CPF: ");
      String pessoaClienteCpf = sc.nextLine();
      System.out.print("Entre com sua senha: ");
      String senha = sc.nextLine();

      PessoaCliente pessoaClienteAutenticada = banco.pessoaClienteLogin(pessoaClienteCpf, senha);

      if (pessoaClienteAutenticada == null) {
        System.out.println("Combinação de CPF e senha incorretos. Tente novamente");

      } else {

        int op;

        // menu para manipulacao das contas da pessoa cliente
        do {

          // mostra o resumo das contas da pessoa cliente
          pessoaClienteAutenticada.retornarResumoContas();

          System.out.println("O que você gostaria de fazer?");
          System.out.println("  1) Mostrar Extrato");
          System.out.println("  2) Sacar");
          System.out.println("  3) Depositar");
          System.out.println("  4) Transferir");
          System.out.println("  5) Sair");
          System.out.println();
          System.out.print("Entre com sua opção: ");

          op = sc.nextInt();

          if (op < 1 || op > 5) {
            System.out.println("Opção inválida, escolha uma opção válida.");
          }

          // processando a escolha
          if (op == 1) {

            int conta;

            // pega o indice da conta para imprimir o extrato
            do {
              System.out.printf("Entre com o número (1-%d) para a conta\nque "
                  + "o extrato será impresso: ", pessoaClienteAutenticada.retornaNumeroDeContas());
              conta = sc.nextInt() - 1;
              if (conta < 0 || conta >= pessoaClienteAutenticada.retornaNumeroDeContas()) {
                System.out.println("Número inválido, tente novamente.");
              } else {
                break;
              }
            } while (true);

            banco.mostrarExtrato(pessoaClienteAutenticada, conta);


          } else if (op == 2) {

            int deConta;
            double quantia;
            double saldoConta;

            // pega o indice da conta para saque
            do {
              System.out.printf("Entre o número (1-%d) para selecionar a conta para "
                  + "o saque: ", pessoaClienteAutenticada.retornaNumeroDeContas());
              deConta = sc.nextInt() - 1;
              if (deConta < 0 || deConta >= pessoaClienteAutenticada.retornaNumeroDeContas()) {
                System.out.println("Índice de conta inválido, tente novamente.");
              } else {
                break;
              }
            } while (true);

            // retorna o saldo da conta selecionada para ver se tem fundos suficientes
            saldoConta = pessoaClienteAutenticada.retornarSaldoContaEspecifica(deConta);

            // pega a quantia para o saque
            do {
              System.out.printf("Entre com a quantia a ser sacada (máximo R$%.02f): R$ ",
                  saldoConta);
              quantia = sc.nextDouble();
              if (quantia < 0) {
                System.out.println("quantia deve ser maior que zero.");
              } else if (quantia > saldoConta) {
                System.out.printf("quantia não pode ser maior que o saldo "
                    + "de R$ %.02f.\n", saldoConta);
              } else {
                break;
              }
            } while (true);

            banco.sacar(pessoaClienteAutenticada, deConta, quantia);

          } else if (op == 3) {

            int paraConta;
            double quantia;

            // pega o indice da conta para deposito
            do {
              System.out.printf("Entre com o número (1-%d) para selecionar a conta para "
                  + "depósito: ", pessoaClienteAutenticada.retornaNumeroDeContas());
              paraConta = sc.nextInt() - 1;

              if (paraConta < 0 || paraConta >= pessoaClienteAutenticada.retornaNumeroDeContas()) {
                System.out.println("Índice de conta inválido, tente novamente.");

              } else {
                break;
              }
            } while (true);

            // pega quantia para depositar
            do {
              System.out.print("Entre com a quantia de depósito: R$ ");
              quantia = sc.nextDouble();

              if (quantia < 0) {
                System.out.println("quantia deve ser maior que zero.");

              } else {
                break;
              }
            } while (true);

            // realiza o deposito
            banco.depositar(pessoaClienteAutenticada, paraConta, quantia);

          } else if (op == 4) {

            int daConta;
            int paraConta;
            double quantia;
            double saldoConta;

            // pega o indice de uma conta retirar o valor da transferencia
            do {
              System.out.printf("Entre o número (1-%d) para "
                  + "retirar o valor para transferência: ",
                  pessoaClienteAutenticada.retornaNumeroDeContas());
              daConta = sc.nextInt() - 1;
              if (daConta < 0 || daConta >= pessoaClienteAutenticada.retornaNumeroDeContas()) {
                System.out.println("Índice de conta inválido, tente novamente.");
              } else {
                break;
              }
            } while (true);

            // retorna o saldo da conta selecionada para ver se tem fundos suficientes
            saldoConta = pessoaClienteAutenticada.retornarSaldoContaEspecifica(daConta);

            // pega o indice da conta que vai receber o valor da transferencia
            do {
              System.out.printf("Entre o número (1-%d) para "
                  + "selecionar a conta que receberá a transferência: ",
                  pessoaClienteAutenticada.retornaNumeroDeContas());
              paraConta = sc.nextInt() - 1;
              if (paraConta < 0 || paraConta >= pessoaClienteAutenticada.retornaNumeroDeContas()) {
                System.out.println("Índice de conta inválido, tente novamente.");
              } else {
                break;
              }
            } while (true);

            // pega o valor para transferir
            do {
              /* pega a quantia para ser transferida de uma conta da pessoa cliente para outra
               * levando em consideracao o saldo da conta que cedera o dinheiro
               */
              System.out.printf("Entre com a quantia para ser transferida (máximo R$%.02f): R$ ",
                  saldoConta);
              quantia = sc.nextDouble();

              if (quantia < 0) {
                System.out.println("quantia deve ser maior que zero.");

              } else if (quantia > saldoConta) {
                System.out.print("quantia não pode ser maior que o valor do saldo "
                    + "de R$.02f.\n");
              } else {
                break;
              }
            } while (true);

            // envia as informacoes para o banco realizar a trasnferencia
            banco.transferirFundos(pessoaClienteAutenticada, daConta, paraConta, quantia);

          } else if (op == 5) {
            System.out.println("Logout realizado com sucesso!");
            break;
          }

        } while (true);
      } // fim else
    } // fim loop infinito
  }
}
