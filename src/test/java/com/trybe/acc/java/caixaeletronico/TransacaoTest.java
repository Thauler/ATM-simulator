package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@DisplayName("Testes dos métodos da classe Transacao")
class TransacaoTest {


  @Test
  @DisplayName("21 - Testa o método construtor da classe Transacao.")
  void construtorTest() {
    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));

    Transacao transaction = new Transacao(100.00, "Depósito efetuado");

    assertEquals(dateAndTime, transaction.retornarInstante());
    assertEquals(100.00, transaction.getQuantia());
    assertTrue(transaction.retornarResumoTransacao().contains("Depósito efetuado"));
  }


  @Test
  @DisplayName("22 - Testa o método Getter do atributo quantia.")
  void getQuantiaTest() {
    Transacao transaction = new Transacao(100.00, "Depósito efetuado");

    assertEquals(100.00, transaction.getQuantia());
  }

  @Test
  @DisplayName("23 - Testa o método retornar resumo transacao.")
  void retornarResumoTransacaoTest() {
    Transacao transaction = new Transacao(100.01, "Depósito efetuado");

    String extrato = transaction.retornarInstante() + " -------- Depósito efetuado: R$ 100.01 -";

    assertEquals(extrato, transaction.retornarResumoTransacao());
  }

  @Test
  @DisplayName("24 - Testa o método instante está gerando o instante corretamente.")
  void retornarInstanteTest() {
    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));
    Transacao transaction = new Transacao(100.00, "Depósito efetuado");

    assertEquals(dateAndTime, transaction.retornarInstante());
  }

}
