package com.trybe.acc.java.caixaeletronico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    fail("Não implementado");

  }

  @Test
  @DisplayName ("14 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    fail("Não implementado");

  }


  @Test
  @DisplayName ("15 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    fail("Não implementado");

  }

  @Test
  @DisplayName ("16 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    fail("Não implementado");

  }

  @Test
  @DisplayName ("17 - Testa o método adiciona transacao de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    fail("Não implementado");

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
    fail("Não implementado");


  }

  @Test
  @DisplayName ("20 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    PessoaCliente client = new PessoaCliente("Thauler", "123.456.789-10", "123456");

    assertEquals("123.456.789-10", client.getCpf());

  }

}
