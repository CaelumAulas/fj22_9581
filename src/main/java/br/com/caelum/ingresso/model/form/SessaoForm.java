package br.com.caelum.ingresso.model.form;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Sessao;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class SessaoForm {

    @NotNull private Integer salaId, filmeId;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime horario; //00:00 - 23:59


    public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
        return new Sessao(salaDao.findOne(salaId),
                filmeDao.findOne(filmeId),
                horario);
    };

    public Integer getSalaId() {
        return salaId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public Integer getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Integer filmeId) {
        this.filmeId = filmeId;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
