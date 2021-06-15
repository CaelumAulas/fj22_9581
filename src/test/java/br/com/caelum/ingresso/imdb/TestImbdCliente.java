package br.com.caelum.ingresso.imdb;

import br.com.caelum.ingresso.model.Filme;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;

public class TestImbdCliente {

    @Test
    public void deveRetornarDetalhesDoFilme() {

        ImdbCliente cliente = new ImdbCliente();
        Filme filme = new Filme("Venom", Duration.ofMinutes(60), "Acao", BigDecimal.TEN);
        Optional<DetalheFilme> detalheFilme = cliente.buscaDetalhesDoFilme(filme, DetalheFilme.class);

        Assert.assertTrue(detalheFilme.isPresent());
        Assert.assertEquals(detalheFilme.get().getTitulo(), filme.getNome());
        Assert.assertEquals("2018", detalheFilme.get().getAno());
    }

    @Test
    public void deveRetornarApenasCapaDoFilme() {

        ImdbCliente cliente = new ImdbCliente();
        Filme filme = new Filme("Venom", Duration.ofMinutes(60), "Acao", BigDecimal.TEN);
        Optional<ImagemCapa> detalheFilme = cliente.buscaDetalhesDoFilme(filme, ImagemCapa.class);

        Assert.assertTrue(detalheFilme.isPresent());
        Assert.assertNotNull(detalheFilme.get().getUrl());
    }

}
