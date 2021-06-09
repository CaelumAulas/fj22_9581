package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Filme filme;

    private LocalTime horario;

    public Sessao(Sala sala, Filme filme, LocalTime horario) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
    }

    /**
     * @deprecated deve ser utilizado somente pelo hibernate, para conseguir criar os objetos
     * quando retornados do banco de dados
     */
    public Sessao() {
    }

    public Long getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public LocalTime getHorario() {
        return horario;
    }
}
