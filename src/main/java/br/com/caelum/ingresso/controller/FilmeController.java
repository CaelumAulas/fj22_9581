package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.imdb.DetalheFilme;
import br.com.caelum.ingresso.imdb.ImdbCliente;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {


    @Autowired
    private FilmeDao filmeDao;
    @Autowired private SessaoDao sessaoDao;
    @Autowired private ImdbCliente imdbCliente;


    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Filme filme){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
            filme = filmeDao.findOne(id.get());
        }

        modelAndView.addObject("filme", filme);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid Filme filme, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(filme.getId()), filme);
        }

        filmeDao.save(filme);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }


    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        filmeDao.delete(id);
    }

    @GetMapping("/filme/em-cartaz")
    public ModelAndView filmesEmCartaz() {
        ModelAndView view = new ModelAndView("filme/em-cartaz");

        view.addObject("filmes", filmeDao.findAll());

        return view;
    }

    @GetMapping("/filme/{id}/detalhe")
    ///filme/3/detalhe --> path variable
    //filmes?category=acao& --> request parameters
    public ModelAndView detelhaFilmeEmCartaz(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("filme/detalhe");

        Filme filmeParaSerRetornadoNaPagina = filmeDao.findOne(id);
        DetalheFilme detalheDoFilme = imdbCliente.buscaDetalhesDoFilme(filmeParaSerRetornadoNaPagina, DetalheFilme.class).orElse(new DetalheFilme());
        List<Sessao> sessoesDoFilme = sessaoDao.buscaSessoesDoFilme(filmeParaSerRetornadoNaPagina);

        view.addObject("sessoes", sessoesDoFilme);
        view.addObject("detalhes", detalheDoFilme);

        return view;
    }
}
