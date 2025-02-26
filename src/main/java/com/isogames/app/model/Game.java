package com.isogames.app.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 75, name = "nome_do_jogo")
    private String nomeDoJogo;
    @Column(nullable = false, name = "data_de_lancamento")
    private Date dataDeLancamaento;
    @Column(nullable = false, length = 250)
    private String descricao;
    @Column(nullable = false, length = 50)
    private String desenvolvedora;
    @Column(nullable = false, length = 50)
    private String distribuidora;
    @Column(nullable = false)
    private float preco;
    @Column(nullable = false, length = 250)
    private List<String> plataforma;
    @Column(nullable = false, length = 50)
    private List<String> genero;
    @Column(nullable = false, length = 50, name = "classificacao_indicativa")
    private String classificacaoIndiacativa;
    @Column(nullable = false, length = 250)
    private List<String> idiomas;
    @Column(nullable = false, length = 250)
    private List<String> legendas;
    @Column(nullable = false, name = "quantidade_em_estoque")
    private  int quantidadeEmEstoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoJogo() {
        return nomeDoJogo;
    }

    public void setNomeDoJogo(String nomeDoJogo) {
        this.nomeDoJogo = nomeDoJogo;
    }

    public Date getDataDeLancamaento() {
        return dataDeLancamaento;
    }

    public void setDataDeLancamaento(Date dataDeLancamaento) {
        this.dataDeLancamaento = dataDeLancamaento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public List<String> getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(List<String> plataforma) {
        this.plataforma = plataforma;
    }

    public List<String> getGenero() {
        return genero;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public String getClassificacaoIndiacativa() {
        return classificacaoIndiacativa;
    }

    public void setClassificacaoIndiacativa(String classificacaoIndiacativa) {
        this.classificacaoIndiacativa = classificacaoIndiacativa;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<String> getLegendas() {
        return legendas;
    }

    public void setLegendas(List<String> legendas) {
        this.legendas = legendas;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
}
