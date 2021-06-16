package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.desconto.Desconto;
import br.com.caelum.ingresso.model.desconto.Inteira;
import br.com.caelum.ingresso.model.desconto.MeiaEntrada;

import java.math.BigDecimal;

public enum TipoDeIngresso {
    ESTUDANTE(new MeiaEntrada()),
    INTEIRA(new Inteira());

    private Desconto desconto;

    TipoDeIngresso(Desconto desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return desconto.getDescricao();
    }

    public BigDecimal aplicaDesconto(BigDecimal preco) {
        return this.desconto.calculaDesconto(preco);
    }
}
