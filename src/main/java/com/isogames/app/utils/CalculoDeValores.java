package com.isogames.app.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculoDeValores {

    public BigDecimal calcularValorDaDevolucao(String valorTotal, String quantidadeComprada, String quantidadeDevolvida){

        BigDecimal valorTotalIplm = new BigDecimal(valorTotal);
        BigDecimal quantidadeCompradaIplm = new BigDecimal(quantidadeComprada);
        BigDecimal quantidadeDevolvidaIplm = new BigDecimal(quantidadeDevolvida);

        BigDecimal novoValorUnidade = valorTotalIplm.divide(quantidadeCompradaIplm);
        BigDecimal valorDevolvido = novoValorUnidade.multiply(quantidadeDevolvidaIplm);

        return valorDevolvido;
    }

}
