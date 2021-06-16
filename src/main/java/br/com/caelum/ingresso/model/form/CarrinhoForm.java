package br.com.caelum.ingresso.model.form;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sessao;

import java.util.List;
import java.util.stream.Collectors;

public class CarrinhoForm {

    private List<Ingresso> ingressos;

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public List<Ingresso> paraIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
        return ingressos.stream().map(ingresso -> {
            Ingresso ingresso1 = new Ingresso(sessaoDao.buscaSessaoPeloIdo(ingresso.getSessao().getId()),
                    ingresso.getTipoDeIngresso(),
                    lugarDao.buscaPeloId(ingresso.getLugar().getId()));
            return ingresso1;
        }).collect(Collectors.toList());
    }
}
