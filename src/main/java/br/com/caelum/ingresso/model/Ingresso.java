package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.desconto.Desconto;

import java.math.BigDecimal;

public class Ingresso {

    private Sessao sessao;
    private BigDecimal preco;

    public Ingresso(Sessao sessao, Desconto desconto) {
        this.sessao = sessao;
        this.preco = desconto.calculaDesconto(sessao.getPreco());
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getValorOriginal() {
        return this.sessao.getPreco();
    }
}
