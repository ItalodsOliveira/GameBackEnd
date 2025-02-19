package com.isogames.app.model.response;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class GameResponseError {

    private int httpCode;
    private String mensagemDeErro;
    private Date horaDoErro;

}
