package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DisplayName ("Testes para a classe Banco")
class BancoTest {

  @Test
  @DisplayName ("1 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    Banco bank = new Banco();
    bank.gerarNumeroNovaConta();
    assertEquals(10, "8514540006".length(), bank.gerarNumeroNovaConta().length());
  }

  @Test
  @DisplayName ("2 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    Banco bank = new Banco();
    assertEquals(PessoaCliente.class,
        bank.adicionarPessoaCliente("Thauler", "123.456.789-10", "123456").getClass());
  }

  @Test
  @DisplayName ("3 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
  void pessoaClienteLoginTest() {

    Banco bank = new Banco();

    PessoaCliente mockCliente = bank
        .adicionarPessoaCliente("Thauler", "123.456.789-10", "123456");

    assertEquals(
        mockCliente
        , bank.pessoaClienteLogin("123.456.789-10", "123456"));
    assertNull(bank.pessoaClienteLogin("123.456.789-10", "1234569"));
    assertNull(bank.pessoaClienteLogin("123.456.789-19", "123456"));

  }

  @Test
  @DisplayName ("4 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    String messageOfDeposit = "Depósito recebido";
    String messageOfTransaction = "Transferência recebida";
    String messageOfWithdraw = "Transferência efetuada";

    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));

    OutputStream os = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(os);
    System.setOut(ps);

    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta checkingAccount = new Conta("Corrente", client, bank);
    Conta savingAccount = new Conta("Poupança", client, bank);

    client.adicionarConta(checkingAccount);
    client.adicionarConta(savingAccount);

    bank.depositar(client, 0, 100.00);
    bank.depositar(client, 1, 100.00);

    bank.transferirFundos(client, 0, 1, 100.00);

    bank.mostrarExtrato(client, 0);

    assertEquals("Nova pessoa cliente Thauler com CPF: 123.456.789-10 criada!\n" +
            dateAndTime + " -------- " + messageOfDeposit + ": R$ 100.0 +\n" +
        dateAndTime + " -------- " + messageOfWithdraw + ": R$ 100.0 -"

            + System.getProperty("line.separator"),
        os.toString());

    OutputStream outStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outStream);
    System.setOut(printStream);

    bank.mostrarExtrato(client, 1);

    assertEquals(dateAndTime + " -------- " + messageOfDeposit + ": R$ 100.0 +\n" +
        dateAndTime + " -------- " + messageOfTransaction + ": R$ 100.0 +"
            + System.getProperty("line.separator"),
        outStream.toString());
  }

  @Test
  @DisplayName ("5 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    String messageOfDeposit = "Depósito recebido";
    String messageOfWithdraw = "Saque efetuado";
    int accountIndex = 0;
    double quantity = 100.00;

    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));

    OutputStream os = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(os);
    System.setOut(ps);

    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta checkingAccount = new Conta("Corrente", client, bank);

    client.adicionarConta(checkingAccount);

    bank.depositar(client, accountIndex, quantity);

    bank.sacar(client, accountIndex, quantity);

    bank.mostrarExtrato(client, 0);

    assertEquals("Nova pessoa cliente Thauler com CPF: 123.456.789-10 criada!\n" +
            dateAndTime + " -------- " + messageOfDeposit + ": R$ 100.0 +\n" +
            dateAndTime + " -------- " + messageOfWithdraw + ": R$ 100.0 -"

            + System.getProperty("line.separator"),
        os.toString());
  }

}
