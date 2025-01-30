package com.isogames.app.model.response;
import java.util.Date;

public class GameResponseError {

    private int httpCode;
    private String mensagemDeErro;
    private Date horaDoErro;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMensagemDeErro() {
        return mensagemDeErro;
    }

    public void setMensagemDeErro(String mensagemDeErro) {
        this.mensagemDeErro = mensagemDeErro;
    }

    public Date getHoraDoErro() {
        return horaDoErro;
    }

    public void setHoraDoErro(Date horaDoErro) {
        this.horaDoErro = horaDoErro;
    }
}
