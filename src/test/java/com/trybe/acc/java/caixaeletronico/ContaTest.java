package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste da classe Conta")
class ContaTest {

  @Test
  @DisplayName("6 - Testa o construtor da classe conta.")
  void construtorTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();

    Conta account = new Conta("Corrente", client, bank);

    assertEquals("Corrente", account.getTipoConta());
    assertEquals(client, account.getPessoaCliente());
    assertEquals(10, bank.gerarNumeroNovaConta().length(), account.getIdConta().length());
  }

  @Test
  @DisplayName("7 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");
    Banco bank = new Banco();
    Conta account = new Conta("Corrente", client, bank);

    account.adicionarTransacao(100.00, "Depósito recebido");
    account.adicionarTransacao(100.01, "Depósito recebido");

    assertEquals(200.01, account.retornarSaldo());

    account.adicionarTransacao(101, "Saque feito");
    account.adicionarTransacao(100, "Transferência feita");

    assertEquals(-0.9900000000000091, account.retornarSaldo());
  }

  @Test
  @DisplayName("8 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    fail("Não implementado");

  }

  @Test
  @DisplayName("9 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    fail("Não implementado");

  }

  @Test
  @DisplayName("10 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    fail("Não implementado");

  }

  @Test
  @DisplayName("11 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    fail("Não implementado");
  }

}
