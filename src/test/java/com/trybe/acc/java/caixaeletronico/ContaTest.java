package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName ("Teste da classe Conta")
class ContaTest {

  @Test
  @DisplayName ("6 - Testa o construtor da classe conta.")
  void construtorTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();

    Conta account = new Conta("Corrente", client, bank);

    assertEquals(client, account.getPessoaCliente());
    assertEquals(10, bank.gerarNumeroNovaConta().length(), account.getIdConta().length());
  }

  @Test
  @DisplayName ("7 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    account.adicionarTransacao(100.00, "Depósito recebido");
    account.adicionarTransacao(100.01, "Depósito recebido");

    assertEquals(200.01, account.retornarSaldo());

    account.adicionarTransacao(101, "Saque feito");
    account.adicionarTransacao(100, "Transferência feita");

    assertEquals(- 0.9900000000000091, account.retornarSaldo());
  }

  @Test
  @DisplayName ("8 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    account.adicionarTransacao(100, "Depósito recebido");

    assertEquals(account.getIdConta() + " : R$100.0 : Corrente"
        , account.retornarResumoConta());

  }

  @Test
  @DisplayName ("9 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));

    OutputStream os = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(os);
    System.setOut(ps);


    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

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
  @DisplayName ("10 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    assertEquals(10, account.getIdConta().length());

  }

  @Test
  @DisplayName ("11 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    assertEquals(PessoaCliente.class, account.getPessoaCliente().getClass());
  }

}
