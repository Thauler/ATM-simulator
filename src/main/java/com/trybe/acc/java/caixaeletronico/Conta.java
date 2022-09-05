package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

/**
 * The type Conta.
 */
public class Conta {
  private String tipoConta;
  private String idConta;
  private PessoaCliente pessoaCliente;
  private ArrayList<Transacao> transacoes = new ArrayList<>();

  /**
   * Instantiates a new Conta.
   *
   * @param tipoConta     the tipo conta
   * @param pessoaCliente the pessoa cliente
   * @param banco         the banco
   */
  public Conta(String tipoConta, PessoaCliente pessoaCliente, Banco banco) {
    this.tipoConta = tipoConta;
    this.pessoaCliente = pessoaCliente;
    this.idConta = banco.gerarNumeroNovaConta();
  }

  /**
   * Gets id conta.
   *
   * @return the id conta
   */
  public String getIdConta() {
    return idConta;
  }

  /**
   * Adicionar transacao.
   *
   * @param quantity    the quantity
   * @param description the description
   */
  public void adicionarTransacao(double quantity, String description) {
    Transacao transaction = new Transacao(quantity, description);
    this.transacoes.add(transaction);
  }

  /**
   * Retornar saldo double.
   *
   * @return the double
   */
  public double retornarSaldo() {
    double balance = 0;
    for (Transacao transaction : this.transacoes) {
      if (transaction.retornarResumoTransacao().endsWith("-")) {
        balance -= transaction.getQuantia();
      } else {
        balance += transaction.getQuantia();
      }
    }
    return balance;
  }
}
