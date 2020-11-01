/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Elyneker Luciani
 */
public class Calcular {

    public BigDecimal calcularValorDasDiarias(BigDecimal valor, int diasHospedados) {
        BigDecimal valorDiaria = valor;
        return valorDiaria.multiply(new BigDecimal(diasHospedados));

    }

    public BigDecimal calcularPrecoProdutoQuantidade(String valorUnit, int quantidade) {
        BigDecimal valor = new BigDecimal(valorUnit);
        return valor.multiply(new BigDecimal(quantidade));
    }

    public BigDecimal somarTotal(ArrayList<String[]> lista, int posicao) {
        BigDecimal valor = new BigDecimal("0.00");
        try {
            for (String[] array : lista) {
                valor = valor.add(new BigDecimal(String.valueOf(array[posicao])));
            }
        } catch (Exception e) {
            System.out.println("Calcular.somar: " + e);
        }
        return valor;
    }
    
    public BigDecimal totalHospedagem(BigDecimal valorDiaria, BigDecimal valorConsumido) {
        BigDecimal valor = new BigDecimal("0.00");
        try {
            valor = valor.add(valorDiaria).add(valorConsumido);
        } catch (Exception e) {
            System.out.println("Calcular.totalHospedagem: " + e);
        }
        return valor;
    }

}
