package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Filme filme;

    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
    private Set<Ingresso> ingressos = new HashSet<>();

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

    public Map<String, List<Lugar>> getMapaDeLugares() {
        return sala.getMapaDeLugares();
    }

    public Set<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(Set<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public boolean isDisponivel(Lugar selecionado) {
//        List<Lugar> lugaresVendidos = ingressos.stream().map(Ingresso::getLugar).collect(Collectors.toList());
//        return lugaresVendidos.stream().noneMatch(ingresso -> ingresso.equals(selecionado));

        return ingressos.stream()
                .map(Ingresso::getLugar) // lista de lugares
                .noneMatch(ingresso -> ingresso.equals(selecionado)); // o lugar selecionado não deve existir na lista


    }

}
