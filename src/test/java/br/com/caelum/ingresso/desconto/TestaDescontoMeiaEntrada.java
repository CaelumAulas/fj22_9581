package br.com.caelum.ingresso.desconto;

import br.com.caelum.ingresso.model.desconto.MeiaEntrada;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TestaDescontoMeiaEntrada {

    @Test
    public void oValorDoIngressoDeveSer15Reais() {
        MeiaEntrada desconto = new MeiaEntrada();
        BigDecimal valorOriginal = new BigDecimal("30");
        Assert.assertEquals(new BigDecimal("15.00"), desconto.calculaDesconto(valorOriginal));
    }

    @Test
    public void oValorDoIngressoDeveSer15e30Reais() {
        MeiaEntrada desconto = new MeiaEntrada();
        BigDecimal valorOriginal = new BigDecimal("30.35");
        Assert.assertEquals(new BigDecimal("15.18"), desconto.calculaDesconto(valorOriginal));
    }
}
