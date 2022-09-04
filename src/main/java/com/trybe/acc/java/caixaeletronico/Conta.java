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


}
