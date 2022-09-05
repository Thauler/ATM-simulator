package com.trybe.acc.java.caixaeletronico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Transacao.
 */
public class Transacao {

  private double quantia;
  private String instante;
  private String descricao;
  private Conta conta;

  /**
   * Instantiates a new Transacao.
   *
   * @param quantia   the quantia
   * @param descricao the descricao
   */
  public Transacao(double quantia, String descricao) {
    this.quantia = quantia;
    this.descricao = descricao;
    this.instante = retornarInstante();
  }

  /**
   * Gets quantia.
   *
   * @return the quantia
   */
  public double getQuantia() {
    return quantia;
  }

  /**
   * Retornar instante string.
   *
   * @return the string
   */
  public String retornarInstante() {
    String formato = "dd/MM/yyyy HH:mm:ss";
    String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));
    return dateAndTime;
  }

  /**
   * Retornar resumo transacao string.
   *
   * @return the string
   */
  public String retornarResumoTransacao() {
      String statementFormat = "%s -------- %s: R$ %s ";

    if (this.descricao.toLowerCase().contains("recebid")) {
      statementFormat += "+";
    } else {
      statementFormat += "-";
    }

    return stringStatement(statementFormat);
  }

  private String stringStatement(String statementFormat) {
    return String
        .format(statementFormat, this.instante, this.descricao, this.quantia);
  }
}

