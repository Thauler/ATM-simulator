package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * The type Banco.
 */
public class Banco {
  private ArrayList<PessoaCliente> pessoasClientes = new ArrayList<>();
  private ArrayList<Conta> contas = new ArrayList<>();

  /**
   * Gerar numero nova conta string.
   *
   * @return the string
   */
  public String gerarNumeroNovaConta() {
    Random randomize = new Random();

    ArrayList<String> numArr = new ArrayList<>();

    while (numArr.size() < 10) {
      numArr.add(Integer.toString(randomize.nextInt(10)));
    }

    String accountID = numArr
        .stream()
        .map(Object::toString)
        .collect(Collectors.joining(""));

    return accountID;
  }

  /**
   * Adicionar pessoa cliente.
   *
   * @param name     the name
   * @param cpf      the cpf
   * @param password the password
   *
   * @return the pessoa cliente
   */
  public PessoaCliente adicionarPessoaCliente(String name, String cpf, String password) {

    PessoaCliente newClient = new PessoaCliente(name, cpf, password);

    pessoasClientes.add(newClient);

    PessoaCliente lastPersonRegistred = pessoasClientes.get(pessoasClientes.lastIndexOf(newClient));

    return lastPersonRegistred;
  }

  /**
   * Pessoa cliente login pessoa cliente.
   *
   * @param cpf   the cpf
   * @param senha the senha
   *
   * @return the pessoa cliente
   */
  public PessoaCliente pessoaClienteLogin(String cpf, String senha) {

    PessoaCliente clienteLogin = null;

    for (PessoaCliente client : pessoasClientes) {
      if (! client.validarSenha(senha) || ! findCpf(cpf)) {
        return clienteLogin;
      }
      clienteLogin = client;
    }
    return clienteLogin;
  }

  private boolean findCpf(String cpfCheck) {
    return pessoasClientes.stream().anyMatch(client -> Objects.equals(client.getCpf(), cpfCheck));
  }

  /**
   * Depositar.
   *
   * @param clienteLogado the cliente logado
   * @param paraConta     the para conta
   * @param quantia       the quantia
   */
  public void depositar(PessoaCliente clienteLogado, int paraConta, double quantia) {
    String messageOfTransaction = "Depósito recebido";
    clienteLogado.adicionarTransacaoContaEspecifica(paraConta, quantia, messageOfTransaction);
  }

  /**
   * Transferir fundos.
   *
   * @param clienteLogado the cliente logado
   * @param daConta       the da conta
   * @param paraConta     the para conta
   * @param quantia       the quantia
   */
  public void transferirFundos(
      PessoaCliente clienteLogado, int daConta, int paraConta, double quantia) {

    String messageOfWithdraw = "Transferência efetuada";

    String messageOfTransaction = "Transferência recebida";

    clienteLogado.adicionarTransacaoContaEspecifica(daConta, quantia, messageOfWithdraw);

    clienteLogado.adicionarTransacaoContaEspecifica(paraConta, quantia, messageOfTransaction);

  }


}
