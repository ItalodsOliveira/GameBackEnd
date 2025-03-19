package com.isogames.app.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class GameResponseCompra {

    private Date horaDaCompra;
    private String nomeDoJogo;
    private BigDecimal precoTotal;
    private BigDecimal precoUnidade;
    private int quantidade;
    private String mensagem;
}
