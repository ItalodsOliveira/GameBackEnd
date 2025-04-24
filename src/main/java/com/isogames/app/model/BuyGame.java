package com.isogames.app.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "compraefetivada")
public class BuyGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "codigo_do_jogo")
    private Long codigoDoJogo;
    @Column(nullable = false, length = 75, name = "nome_do_jogo")
    private String nomeDoJogo;
    @Column(nullable = false, name = "preco_unidade")
    private BigDecimal precoUnidade;
    @Column(nullable = false, name = "preco_total")
    private BigDecimal precoTotal;
    @Column(nullable = false, name = "data_da_compra")
    private Date dataDaCompra;
    @Column(nullable = true, name = "data_da_devolucao")
    private Date dataDaDevolucao;
    @Column(nullable = false, name = "quantidade_comprada")
    private  int quantidadeComprada;
    @Column(nullable = true, name = "quantidade_devolvida")
    private  Long quantidadeDevolvida;
    @Column(nullable = true, name = "valor_da_devolucao")
    private  BigDecimal valorDaDevolucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoDoJogo() {
        return codigoDoJogo;
    }

    public void setCodigoDoJogo(Long codigoDoJogo) {
        this.codigoDoJogo = codigoDoJogo;
    }

    public String getNomeDoJogo() {
        return nomeDoJogo;
    }

    public void setNomeDoJogo(String nomeDoJogo) {
        this.nomeDoJogo = nomeDoJogo;
    }

    public BigDecimal getPrecoUnidade() {
        return precoUnidade;
    }

    public void setPrecoUnidade(BigDecimal precoUnidade) {
        this.precoUnidade = precoUnidade;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Date getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(Date dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public Date getDataDaDevolucao() {
        return dataDaDevolucao;
    }

    public void setDataDaDevolucao(Date dataDaDevolucao) {
        this.dataDaDevolucao = dataDaDevolucao;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public Long getQuantidadeDevolvida() {
        return quantidadeDevolvida;
    }

    public void setQuantidadeDevolvida(Long quantidadeDevolvida) {
        this.quantidadeDevolvida = quantidadeDevolvida;
    }

    public BigDecimal getValorDaDevolucao() {
        return valorDaDevolucao;
    }

    public void setValorDaDevolucao(BigDecimal valorDaDevolucao) {
        this.valorDaDevolucao = valorDaDevolucao;
    }
}


