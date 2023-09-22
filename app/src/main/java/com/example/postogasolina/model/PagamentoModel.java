package com.example.postogasolina.model;

public class PagamentoModel {

    private String formaPagamento;

    public PagamentoModel(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double calcularPrecoTotal(double precoLitro, double numeroLitrosVendidos) {
        double precoTotal = precoLitro * numeroLitrosVendidos;

        switch (formaPagamento) {
            case "Crédito":
                precoTotal *= 1.10; // Acréscimo de 10% no crédito

                break;
            case "Débito":
            case "Pix":
                precoTotal *= 1.05; // Acréscimo de 5% no débito e no Pix
                break;
            case "Espécie":
                precoTotal *= 0.95; // Desconto de 5% em espécie
                break;
        }

        return precoTotal;
    }
}
