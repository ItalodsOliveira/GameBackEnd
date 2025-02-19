package com.isogames.app.utils;

import org.springframework.stereotype.Component;

@Component
public class AjustaPreco {

    public float aplicarDesconto(float preco, float percentual) {
        return preco - (preco * (percentual / 100));
    }

    public float aplicarAumento(float preco, float precentual) {
        return preco + (preco * (precentual / 100));
    }
}
