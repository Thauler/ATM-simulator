package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName ("Teste dos métodos da classe PessoaCliente")
class PessoaClienteTest {

  @Test
  @DisplayName ("12 - Testa o construtor da classe Pessoa Cliente.")
  void construtorTest() {
    OutputStream os = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(os);
    System.setOut(ps);

    new PessoaCliente("Thauler", "123.456.789-10", "123456");

    assertEquals("Nova pessoa cliente Thauler com CPF: 123.456.789-10 criada!"
            + System.getProperty("line.separator"),
        os.toString());
  }

  @Test
  @DisplayName ("13 - Testa o método adicionar conta e o método retornar número de contas.")
  void adicionarContaTestRetornaNumeroDeContasTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");

    Banco bank = new Banco();

    Conta account = new Conta("Corrente", client, bank);

    client.adicionarConta(account);

    assertEquals(1, client.retornarNumeroDeContas());
  }

  @Test
  @DisplayName ("14 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    int accountIndex = 0;

    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();

    Conta account = new Conta("Corrente", client, bank);

    client.adicionarConta(account);

    assertEquals(00.00, client.retornarSaldoContaEspecifica(accountIndex));

  }

  @Test
  @DisplayName ("15 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    int accountIndex = 0;

    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();

    Conta account = new Conta("Corrente", client, bank);

    client.adicionarConta(account);

    assertEquals(account.getIdConta(), client.retornarIdContaEspecifica(accountIndex));
  }

  @Test
  @DisplayName ("16 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    int accountIndex = 0;

    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));

    OutputStream os = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(os);
    System.setOut(ps);


    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    client.adicionarConta(account);

    client.retornarExtratoContaEspecifica(accountIndex);

    account.adicionarTransacao(100, "Depósito recebido");
    account.adicionarTransacao(100, "Depósito recebido");
    account.adicionarTransacao(100, "Depósito recebido");

    account.retornarExtrato();

    assertEquals("Nova pessoa cliente Thauler com CPF: 123.456.789-10 criada!\n" +
            dateAndTime + " -------- Depósito recebido: R$ 100.0 +\n" +
            dateAndTime + " -------- Depósito recebido: R$ 100.0 +\n" +
            dateAndTime + " -------- Depósito recebido: R$ 100.0 +"
            + System.getProperty("line.separator"),
        os.toString());

  }

  @Test
  @DisplayName ("17 - Testa o método adiciona transacao de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    int accountIndex = 0;
    double quantity = 100.0;
    String description = "Depósito recebido";
    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));

    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Transacao newTransaction = new Transacao(quantity, description);
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    client.adicionarConta(account);

    account.adicionarTransacao(100, "Depósito recebido");

    client.adicionarTransacaoContaEspecifica(accountIndex, quantity, description);

    assertEquals(dateAndTime + " -------- Depósito recebido: R$ 100.0 +",
        newTransaction.retornarResumoTransacao());
  }

  @Test
  @DisplayName ("18 - Testa o método validar senha.")
  void validarSenhaTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");

    assertTrue(client.validarSenha("123456"));
    assertFalse(client.validarSenha("12345623"));
  }

  @Test
  @DisplayName ("19 - Testa o método retornar resumo contas.")
  void retornarResumoContasTest() {
    OutputStream os = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(os);
    System.setOut(ps);

    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    client.adicionarConta(account);

    account.adicionarTransacao(100, "Depósito recebido");

    client.retornarResumoContas();

    assertEquals("Nova pessoa cliente Thauler com CPF: 123.456.789-10 criada!\n" +
            account.getIdConta() + " : R$100.0 : Corrente"
            + System.getProperty("line.separator"),
        os.toString());

  }

  @Test
  @DisplayName ("20 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");

    assertEquals("123.456.789-10", client.getCpf());

  }

}
