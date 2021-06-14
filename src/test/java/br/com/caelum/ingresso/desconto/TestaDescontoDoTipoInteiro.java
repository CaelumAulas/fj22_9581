package br.com.caelum.ingresso.desconto;

import br.com.caelum.ingresso.model.desconto.Inteira;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TestaDescontoDoTipoInteiro {

    @Test
    public void naoDeveAplicarNenhumTipoDeDesconto() {
        BigDecimal valorOriginal = new BigDecimal("10");
        Assert.assertEquals(valorOriginal, new Inteira().calculaDesconto(valorOriginal));
    }
}
