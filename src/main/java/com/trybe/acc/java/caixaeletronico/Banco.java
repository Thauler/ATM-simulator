package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Banco {

  private ArrayList<PessoaCliente> pessoaClientes = new ArrayList<>();
  private ArrayList<Conta> contas = new ArrayList<>();


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

}
