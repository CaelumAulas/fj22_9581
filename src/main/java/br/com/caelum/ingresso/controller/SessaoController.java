package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.ValidaCadastroDeSessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/sessao")
public class SessaoController {

    @Autowired private SalaDao salaDao;
    @Autowired private FilmeDao filmeDao;
    @Autowired private SessaoDao sessaoDao;
    @Autowired private ValidaCadastroDeSessao validaCadastroDeSessao;

    @GetMapping
    public ModelAndView formularioSessao(@RequestParam("salaId") Integer id, SessaoForm formulario) {
        System.out.println("retornando formulário");
        ModelAndView view = new ModelAndView("sessao/sessao");

        view.addObject("sala", salaDao.findOne(id));
        view.addObject("filmes", filmeDao.findAll());
        view.addObject("form", formulario);

        return view;
    }

    @PostMapping
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
}
