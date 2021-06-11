package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private BigDecimal preco;

    public Sessao(Sala sala, Filme filme, LocalTime horario) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.preco = sala.getPreco().add(filme.getPreco());
    }

    /**
     * @deprecated deve ser utilizado somente pelo hibernate, para conseguir criar os objetos
     * quando retornados do banco de dados
     */
    public Sessao() {
    }

    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
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
