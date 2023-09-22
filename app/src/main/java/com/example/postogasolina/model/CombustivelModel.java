package com.example.postogasolina.model;

public class CombustivelModel {

    private String tipoCombustivel;
    private double precoLitro;

    public CombustivelModel(String tipoCombustivel, double precoLitro) {
        this.tipoCombustivel = tipoCombustivel;
        this.precoLitro = precoLitro;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public double getPrecoLitro() {
        return precoLitro;
    }
}
