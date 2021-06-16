package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.form.CarrinhoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompraController {

    @Autowired private Carrinho carrinho;
    @Autowired private SessaoDao sessaoDao;
    @Autowired private LugarDao lugarDao;

    @PostMapping("/compra/ingressos")
    public String processoDeCompra(CarrinhoForm formulario) {
        System.out.println("Recebendo lista com "  + formulario.getIngressos().size());
        List<Ingresso> ingressos = formulario.paraIngressos(sessaoDao, lugarDao);
        System.out.println("ingressos processados" + ingressos.size());

        ingressos.forEach(carrinho::add);

        return "redirect:/compra";
    }

    @GetMapping("/lugares/{id}")
    public String processoDeCompra(@PathVariable("id") Integer id) {
        Lugar lugar = lugarDao.buscaPeloId(id);

        System.out.println("Lugar selecionado: " + lugar.getFileira());

        return "redirect:/compra";
    }


}
