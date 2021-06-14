package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MeiaEntrada implements Desconto {

    @Override
    public BigDecimal calculaDesconto(BigDecimal preco) {
        BigDecimal porcentagemDeDesconto = new BigDecimal("0.5");
        BigDecimal valorASerDescontado = preco.multiply(porcentagemDeDesconto);
        return preco.subtract(valorASerDescontado).setScale(2, RoundingMode.HALF_UP);
    }
}
