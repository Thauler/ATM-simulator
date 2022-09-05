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
  public int retornarNumeroDeContas() {
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
}
