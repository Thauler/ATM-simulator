package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Pessoa cliente.
 */
public class PessoaCliente {
  private String nomeCompleto;
  private String cpf;
  private String senha;
  private ArrayList<Conta> contas = new ArrayList<>();

  /**
   * Instantiates a new Pessoa cliente.
   *
   * @param nomeCompleto the nome completo
   * @param cpf          the cpf
   * @param senha        the senha
   */
  public PessoaCliente(String nomeCompleto, String cpf, String senha) {
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.senha = senha;
    System.out.printf("Nova pessoa cliente %s com CPF: %s criada!%n", nomeCompleto, cpf);
  }

  /**
   * Validar senha boolean.
   *
   * @param senha the senha
   *
   * @return the boolean
   */
  public boolean validarSenha(String senha) {
    return Objects.equals(senha, this.senha);
  }

  /**
   * Gets cpf.
   *
   * @return the cpf
   */
  public String getCpf() {
    return cpf;
  }

  /**
   * Adicionar conta.
   *
   * @param account the account
   */
  public void adicionarConta(Conta account) {
    this.contas.add(account);
  }

  /**
   * Retornar numero de contas int.
   *
   * @return the int
   */
  public int retornaNumeroDeContas() {
    return contas.size();
  }

  /**
   * Retornar saldo conta especifica double.
   *
   * @param accountIndex the account index
   *
   * @return the double
   */
  public double retornarSaldoContaEspecifica(int accountIndex) {
    Conta specificAccount = this.contas.get(accountIndex);
    return specificAccount.retornarSaldo();
  }

  /**
   * Retornar id conta especifica string.
   *
   * @param accountIndex the account index
   *
   * @return the string
   */
  public String retornarIdContaEspecifica(int accountIndex) {
    Conta specificAccount = this.contas.get(accountIndex);
    return specificAccount.getIdConta();
  }

  /**
   * Retornar extrato conta especifica.
   *
   * @param accountIndex the account index
   */
  public void retornarExtratoContaEspecifica(int accountIndex) {
    Conta specificAccount = this.contas.get(accountIndex);
    specificAccount.retornarExtrato();
  }

  /**
   * Adicionar transacao conta especifica.
   *
   * @param accountIndex the account index
   * @param quantity     the quantity
   * @param description  the description
   */
  public void adicionarTransacaoContaEspecifica(
      int accountIndex, double quantity, String description) {
    Conta specificAccount = this.contas.get(accountIndex);
    specificAccount.adicionarTransacao(quantity, description);
  }

  /**
   * Retornar resumo contas.
   */
  public void retornarResumoContas() {
    for (Conta accounts: this.contas) {
      System.out.println(accounts.retornarResumoConta());
    }
  }
}
