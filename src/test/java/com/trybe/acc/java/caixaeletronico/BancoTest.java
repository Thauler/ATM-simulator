package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Testes para a classe Banco")
class BancoTest {

  @Test
  @DisplayName("1 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    Banco bank = new Banco();
    bank.gerarNumeroNovaConta();
    assertEquals(10, "8514540006".length(), bank.gerarNumeroNovaConta().length());
  }

  @Test
  @DisplayName("2 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    Banco bank = new Banco();
    assertEquals(PessoaCliente.class,
        bank.adicionarPessoaCliente("Thauler", "123.456.789-10", "123456").getClass());
  }

  @Test
  @DisplayName("3 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
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
  @DisplayName("4 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    fail("Não implementado");

  }

  @Test
  @DisplayName("5 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    fail("Não implementado");

  }

}
