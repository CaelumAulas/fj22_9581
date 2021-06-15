package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessaoDao {

    @PersistenceContext // jpa
    private EntityManager manager;

    public void salvar(Sessao sessao){
        manager.persist(sessao);
    }

    public List<Sessao> buscaSessoesDeUmaSala(Sala sala) {
        return manager.createQuery("select se from Sessao se where se.sala = :sala",
                Sessao.class) //jpql]
        .setParameter("sala", sala).getResultList();
    }

    public List<Sessao> buscaSessoesPeloFilmeId(Integer id) {
        return manager.createQuery("select se from Sessao se where se.filme.id = :id",
                Sessao.class) //jpql]
                .setParameter("id", id)
                .getResultList();
    }

    public List<Sessao> buscaSessoesDoFilme(Filme filme) {
        return manager.createQuery("select se from Sessao se where se.filme = :filme",
                Sessao.class) //jpql]
                .setParameter("filme", filme)
                .getResultList();
    }

    public Sessao buscaSessaoPeloIdo(Long idSessao) {
        return manager.createQuery("select se from Sessao se where se.id = :id", Sessao.class)
                .setParameter("id", idSessao)
                .getSingleResult();
    }
}
