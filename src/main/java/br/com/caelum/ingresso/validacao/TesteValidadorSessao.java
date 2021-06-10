package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TesteValidadorSessao {

    public static void main(String[] args) {

        ValidaCadastroDeSessao validaCadastroDeSessao = new ValidaCadastroDeSessao();

        Sala sala = new Sala("3D");
        Filme filme = new Filme("Venom", Duration.ofMinutes(120), "acao");
        Sessao sessaoDasOnze = new Sessao(sala, filme, LocalTime.parse("11:00"));
        Sessao sessaoDasOito = new Sessao(sala, filme, LocalTime.parse("08:00"));

        List<Sessao> sessoesJaExistente = Arrays.asList(sessaoDasOnze, sessaoDasOito);

        Sessao sessaoMeioDia = new Sessao(sala, filme, LocalTime.parse("12:00"));

        boolean possoCadastra = validaCadastroDeSessao.possoCadastraEssaSessao(sessoesJaExistente, sessaoMeioDia);

        if(!possoCadastra) {
            System.out.println("Deu tudo certo!!");
        }else {
            System.out.println("Deu tudo errado");
        }

        Sessao sessaoDasDuas = new Sessao(sala, filme, LocalTime.parse("14:00"));

        boolean possoCadastra1 = validaCadastroDeSessao.possoCadastraEssaSessao(sessoesJaExistente, sessaoDasDuas);

        if(possoCadastra1) {
            System.out.println("Deu tudo certo!!");
        }else {
            System.out.println("Deu tudo errado");
        }
    }
}
