package com.isogames.app.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GameResponseCompra {

    private Date horaDaCompra;
    private String nomeDoJogo;
    private float precoTotal;
    private float precoUnidade;
    private int quantidade;
    private String mensagem;
}
