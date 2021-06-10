package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Sessao;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidaCadastroDeSessao {



    private boolean verificaSeDaConflitoDeHorario(Sessao sessaoQueJaExiste, Sessao sessaoParaCriar) {
        LocalDateTime horarioDeInicioDaSessaoJaExiste = getHorarioDeInicio(sessaoQueJaExiste);
        LocalDateTime horarioDeTerminoDaSessaoJaExiste = getHorarioDeTermino(sessaoQueJaExiste);

        LocalDateTime horarioDeInicioDaSessaoParaCriar = getHorarioDeInicio(sessaoParaCriar);
        LocalDateTime horarioDeTerminoDaSessaoParaCriar = getHorarioDeTermino(sessaoParaCriar);

        boolean sessaoTerminaAntesDaSessaoQueJaExiste = horarioDeTerminoDaSessaoParaCriar.isBefore(horarioDeInicioDaSessaoJaExiste);
        boolean sessaoNovaComecaDepoisDaSessaoQueJaExiste = horarioDeTerminoDaSessaoJaExiste.isBefore(horarioDeInicioDaSessaoParaCriar);

        if (sessaoNovaComecaDepoisDaSessaoQueJaExiste || sessaoTerminaAntesDaSessaoQueJaExiste) {
            return false;
        }

        return true;
    }

    public boolean possoCadastraEssaSessao(List<Sessao> sessoesJaExistente, Sessao sessaoParaCadastrar) {
        return sessoesJaExistente.stream().noneMatch(sessao -> verificaSeDaConflitoDeHorario(sessao, sessaoParaCadastrar));
    }

    private LocalDateTime getHorarioDeInicio(Sessao sessao) {
        return sessao.getHorario().atDate(LocalDate.now());
    }

    private LocalDateTime getHorarioDeTermino(Sessao sessao) {
        return getHorarioDeInicio(sessao).plus(sessao.getFilme().getDuracao());
    }
}
