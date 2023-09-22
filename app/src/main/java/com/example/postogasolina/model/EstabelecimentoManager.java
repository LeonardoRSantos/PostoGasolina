package com.example.postogasolina.model;

import android.widget.EditText;

public class EstabelecimentoManager {

    private double precoLitro;
    private EditText etPrecoLitro;

    public EstabelecimentoManager(EditText etPrecoLitro){
        this.etPrecoLitro = etPrecoLitro;
        this.precoLitro = 0.0;
    }

    public double getPrecoLitro() {
        return precoLitro;
    }

    public double setEstabelecimento(String estabelecimentoName) {

        double precoLitro = 0.0;

        if ("americano - 5,49".equalsIgnoreCase(estabelecimentoName)) {
            precoLitro = 5.49;
        } else if ("ipiranga - 5,31".equalsIgnoreCase(estabelecimentoName)) {
            precoLitro = 5.31;
        } else if ("bonazo - 5,48".equalsIgnoreCase(estabelecimentoName)) {
            precoLitro = 5.48;
        } else if ("shell - 5,46".equalsIgnoreCase(estabelecimentoName)) {
            precoLitro = 5.46;
        } else if ("dubai - 5,49".equalsIgnoreCase(estabelecimentoName)) {
            precoLitro = 5.49;
        }

        return precoLitro;
    }

}
