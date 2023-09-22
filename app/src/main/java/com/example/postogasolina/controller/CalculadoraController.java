package com.example.postogasolina.controller;

import com.example.postogasolina.MainActivity;
import com.example.postogasolina.model.CombustivelModel;
import com.example.postogasolina.model.ConsumoModel;
import com.example.postogasolina.model.PagamentoModel;

public class CalculadoraController {

    private MainActivity view;

    public CalculadoraController(MainActivity view) {
        this.view = view;
    }

    public double calcularPrecoTotal(double precoLitro, double numeroLitrosVendidos, String tipoCombustivel, String formaPagamento) {
        ConsumoModel consumo = new ConsumoModel(numeroLitrosVendidos);
        CombustivelModel combustivel = new CombustivelModel(tipoCombustivel, precoLitro);
        PagamentoModel pagamento = new PagamentoModel(formaPagamento);

        return pagamento.calcularPrecoTotal(combustivel.getPrecoLitro(), consumo.getNumeroLitrosVendidos());
    }
}
