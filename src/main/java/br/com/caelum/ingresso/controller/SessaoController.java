package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessaoController {

    @Autowired private SalaDao salaDao;
    @Autowired private FilmeDao filmeDao;

    @GetMapping("/admin/sessao")
    public ModelAndView formularioSessao(@RequestParam("salaId") Integer id) {
        System.out.println("retornando formulário");
        ModelAndView view = new ModelAndView("sessao/sessao");

        view.addObject("sala", salaDao.findOne(id));
        view.addObject("filmes", filmeDao.findAll());

        return view;
    }

}
