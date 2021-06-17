package br.com.caelum.ingresso.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Carrinho {

    private List<Ingresso> ingressos = new ArrayList<>();

    public void add(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public boolean isSelecionado(Lugar lugar) {
        return ingressos.stream() // lista de ingresso -> transforme
                .map(Ingresso::getLugar)  //lista de lugares
                .anyMatch(lugarSelecionado -> lugarSelecionado.equals(lugar)); // o lugar que recebmos existe na lista de lugares selecionados
    }
}
