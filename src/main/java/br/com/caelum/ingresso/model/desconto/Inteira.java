package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class Inteira implements Desconto {
    @Override
    public BigDecimal calculaDesconto(BigDecimal preco) {
        return preco;
    }
}
