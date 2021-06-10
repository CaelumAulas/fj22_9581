package br.com.caelum.ingresso.validacao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TesteStream {

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);

        boolean todosOsNumerosSaoMenoresQue10 = numeros.stream().noneMatch(numero -> numero > 10);
        boolean todosOsNumerosSaoMenoresQue3 = numeros.stream().noneMatch(numero -> numero < 3);


        List<Integer> numeroMaiorDoQue9 = numeros.stream().map(numero -> {
            return numero * 3;
        }).filter(numero -> numero > 9).collect(Collectors.toList());

        long totalDeNumeroMaiorDoQue9 = numeros.stream().map(numero -> {
            return numero * 3;
        }).filter(numero -> numero > 9).count();




        System.out.println("Todos Sao menores que 10 " + todosOsNumerosSaoMenoresQue10);
        System.out.println("Todos Sao menores que 3 " + todosOsNumerosSaoMenoresQue3);

    }
}
