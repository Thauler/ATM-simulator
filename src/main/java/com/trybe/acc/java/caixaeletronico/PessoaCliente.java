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
    System.out.println("Nova pessoa cliente "
        + this.nomeCompleto + " com CPF: " + this.cpf + " criada!");
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

  public String getCpf() {
    return cpf;
  }
}
