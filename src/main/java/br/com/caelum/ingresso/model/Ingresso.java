package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Sessao sessao;

    @ManyToOne
    private Lugar lugar;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;

    /**
     * @deprecated deve ser utilizado somente pelo hibernate, para conseguir criar os objetos
     * quando retornados do banco de dados
     */
    public Ingresso() {
    }

    public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
        this.sessao = sessao;
        this.tipoDeIngresso = tipoDeIngresso;
        this.preco = tipoDeIngresso.aplicaDesconto(sessao.getPreco());
        this.lugar = lugar;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getValorOriginal() {
        return this.sessao.getPreco();
    }

    public TipoDeIngresso getTipoDeIngresso() {
        return tipoDeIngresso;
    }

    public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
        this.tipoDeIngresso = tipoDeIngresso;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Objects.equals(id, ingresso.id) && Objects.equals(sessao, ingresso.sessao) && Objects.equals(lugar, ingresso.lugar) && Objects.equals(preco, ingresso.preco) && tipoDeIngresso == ingresso.tipoDeIngresso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessao, lugar, preco, tipoDeIngresso);
    }
}
