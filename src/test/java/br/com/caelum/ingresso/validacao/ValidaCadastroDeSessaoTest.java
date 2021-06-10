package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class ValidaCadastroDeSessaoTest {

    @Test
    public void naoDeveCadastrarSessaoPoisDaConflitoDeHorario() {
        ValidaCadastroDeSessao validaCadastroDeSessao = new ValidaCadastroDeSessao();

        Sala sala = new Sala("3D");
        Filme filme = new Filme("Venom", Duration.ofMinutes(120), "acao");
        Sessao sessaoDasOnze = new Sessao(sala, filme, LocalTime.parse("11:00"));
        Sessao sessaoDasOito = new Sessao(sala, filme, LocalTime.parse("08:00"));

        List<Sessao> sessoesJaExistente = Arrays.asList(sessaoDasOnze, sessaoDasOito);
        Sessao sessaoMeioDia = new Sessao(sala, filme, LocalTime.parse("12:00"));
        boolean possoCadastra = validaCadastroDeSessao.possoCadastraEssaSessao(sessoesJaExistente, sessaoMeioDia);

        Assert.assertFalse(possoCadastra);
    }

    @Test
    public void deveCadastraoSessao() {
        ValidaCadastroDeSessao validaCadastroDeSessao = new ValidaCadastroDeSessao();

        Sala sala = new Sala("3D");
        Filme filme = new Filme("Venom", Duration.ofMinutes(120), "acao");
        Sessao sessaoDasOnze = new Sessao(sala, filme, LocalTime.parse("11:00"));
        Sessao sessaoDasOito = new Sessao(sala, filme, LocalTime.parse("08:00"));

        List<Sessao> sessoesJaExistente = Arrays.asList(sessaoDasOnze, sessaoDasOito);
        Sessao sessaoMeioDia = new Sessao(sala, filme, LocalTime.parse("14:00"));
        boolean possoCadastra = validaCadastroDeSessao.possoCadastraEssaSessao(sessoesJaExistente, sessaoMeioDia);

        Assert.assertTrue(possoCadastra);
    }
}
