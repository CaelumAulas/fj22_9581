package br.com.caelum.ingresso.desconto;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.desconto.Inteira;
import br.com.caelum.ingresso.model.desconto.MeiaEntrada;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class TestaValoresDeIngresso {

    @Test
    public void oValorDoIngressoNaoDeveSerAlterado() {
        Sala sala = new Sala("3D", new BigDecimal("12"));
        Filme filme = new Filme("Venom", Duration.ofMinutes(120),
                "acao", new BigDecimal("30"));
        Sessao sessaoDasOnze = new Sessao(sala, filme, LocalTime.parse("11:00"));

        Ingresso ingresso = new Ingresso(sessaoDasOnze, new Inteira());
        Assert.assertEquals(new BigDecimal("42.00"), ingresso.getPreco());
    }

    @Test
    public void oValorDoIngressoDeveSerMetadeDoValor() {
        Sala sala = new Sala("3D", new BigDecimal("12"));
        Filme filme = new Filme("Venom", Duration.ofMinutes(120),
                "acao", new BigDecimal("30"));
        Sessao sessaoDasOnze = new Sessao(sala, filme, LocalTime.parse("11:00"));

        Ingresso ingresso = new Ingresso(sessaoDasOnze, new MeiaEntrada());
        Assert.assertEquals(new BigDecimal("21.00"), ingresso.getPreco());
    }
}
