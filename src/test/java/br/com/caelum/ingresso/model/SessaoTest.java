package br.com.caelum.ingresso.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class SessaoTest {

    @Test
    public void oPrecoDaSessaoDeveSer35Reais() {
        Sala sala = new Sala("IMAX", new BigDecimal("15"));
        Filme filme = new Filme("Vingadores", Duration.ofMinutes(150), "Ação", new BigDecimal("20"));
        Sessao sessao = new Sessao(sala, filme, LocalTime.parse("19:00"));
        Assert.assertEquals(new BigDecimal("35.00"), sessao.getPreco());
    }
}
