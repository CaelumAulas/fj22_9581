package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.imdb.ImagemCapa;
import br.com.caelum.ingresso.imdb.ImdbCliente;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.ValidaCadastroDeSessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SessaoController {

    @Autowired private SalaDao salaDao;
    @Autowired private FilmeDao filmeDao;
    @Autowired private SessaoDao sessaoDao;
    @Autowired private ValidaCadastroDeSessao validaCadastroDeSessao;
    @Autowired private ImdbCliente imdbCliente;

    @GetMapping("/admin/sessao")
    public ModelAndView formularioSessao(@RequestParam("salaId") Integer id, SessaoForm formulario) {
        System.out.println("retornando formulário");
        ModelAndView view = new ModelAndView("sessao/sessao");

        view.addObject("sala", salaDao.findOne(id));
        view.addObject("filmes", filmeDao.findAll());
        view.addObject("form", formulario);

        return view;
    }

    @PostMapping("/admin/sessao")
    @Transactional
    public ModelAndView cadastraSessao(@Valid SessaoForm formulario, BindingResult bindingResult) {
        System.out.println("temos todas as informações de sessão");

        if(bindingResult.hasErrors()) {
            return formularioSessao(formulario.getSalaId(), formulario);
        }
        Sessao sessao = formulario.toSessao(salaDao, filmeDao);

        List<Sessao> sessoesJaExistente = sessaoDao.buscaSessoesDeUmaSala(sessao.getSala());

        if (validaCadastroDeSessao.possoCadastraEssaSessao(sessoesJaExistente, sessao)) {
            sessaoDao.salvar(sessao);
            return new ModelAndView("redirect:/admin/sala/" + sessao.getSala().getId() +  "/sessoes/");
        }

        return formularioSessao(formulario.getSalaId(), formulario);
    }

    @GetMapping("sessao/{idDaSessao}/lugares")
    public ModelAndView detalheSessao(@PathVariable("idDaSessao") Long idSessao) {
        ModelAndView view = new ModelAndView("sessao/lugares");
        Sessao sessao = sessaoDao.buscaSessaoPeloIdo(idSessao);

        ImagemCapa imagemCapa = imdbCliente
                .buscaDetalhesDoFilme(sessao.getFilme(), ImagemCapa.class)
                .orElse(new ImagemCapa());

        view.addObject("sessao", sessao);
        view.addObject("imagemCapa", imagemCapa);

        return view;
    }

}
