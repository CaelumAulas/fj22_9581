package br.com.caelum.ingresso.imdb;

import br.com.caelum.ingresso.model.Filme;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ImdbCliente {


    public <T> Optional<T> buscaDetalhesDoFilme(Filme filme, Class<T> clazz) {

        RestTemplate restTemplate = new RestTemplate();


        String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", filme.getNome());

        try {
            return Optional.ofNullable(restTemplate.getForObject(url, clazz));
        }catch (RestClientException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

}

