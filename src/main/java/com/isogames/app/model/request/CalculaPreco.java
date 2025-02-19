package com.isogames.app.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculaPreco {

    String classificacaoIndicativa;
    float percentual;
    boolean desconto;
}
