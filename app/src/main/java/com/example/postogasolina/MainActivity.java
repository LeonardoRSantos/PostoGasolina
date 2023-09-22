package com.example.postogasolina;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postogasolina.adapter.CustomSpinnerAdapter;
import com.example.postogasolina.controller.CalculadoraController;
import com.example.postogasolina.model.EstabelecimentoManager;

public class MainActivity extends AppCompatActivity {
    private EditText etNumeroLitros, etPrecoLitro;
    private Spinner spinnerCombustivel, spinnerFormaPagamento;
    private Button btnCalcular;
    private CalculadoraController controller;

    private EstabelecimentoManager estabelecimentoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        estabelecimentoManager = new EstabelecimentoManager(etPrecoLitro);

        etNumeroLitros = findViewById(R.id.etNumeroLitros);
        etPrecoLitro = findViewById(R.id.etPrecoLitro);
        spinnerCombustivel = findViewById(R.id.spinnerCombustivel);
        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento);
        btnCalcular = findViewById(R.id.btnCalcular);

        controller = new CalculadoraController(this);

        Integer[] combustivelImages = {
                R.drawable.img_default,
                R.drawable.img_gasoline,
                R.drawable.img_ethanol,
                R.drawable.img_diesel
                // Adicione outras imagens conforme necessário
        };

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, combustivelImages);

        spinnerCombustivel.setAdapter(adapter);

        // Adicione o OnCheckedChangeListener ao RadioGroup
        RadioGroup radioGroupEstabelecimento = findViewById(R.id.radioGroupEstabelecimento);
        radioGroupEstabelecimento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    String estabelecimentoName = radioButton.getText().toString().toLowerCase();
                    atualizarPrecoLitro(estabelecimentoName);
                }
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularPrecoTotal();
            }
        });
    }

    private void calcularPrecoTotal() {
        try {
            // Obtenha os valores dos campos de entrada
            double numeroLitros = Double.parseDouble(etNumeroLitros.getText().toString());
            double precoLitro = Double.parseDouble(etPrecoLitro.getText().toString());
            String tipoCombustivel = spinnerCombustivel.getSelectedItem() != null ?
                    spinnerCombustivel.getSelectedItem().toString() : "";
            String formaPagamento = spinnerFormaPagamento.getSelectedItem() != null ?
                    spinnerFormaPagamento.getSelectedItem().toString() : "";

            // Verifique se os campos obrigatórios estão preenchidos
            if (numeroLitros <= 0 || precoLitro <= 0 || tipoCombustivel.isEmpty() || formaPagamento.isEmpty()) {
                // Campos inválidos, exiba uma mensagem de erro
                mostrarMensagem("Preencha todos os campos corretamente.");
                return;
            }

            // Realize o cálculo com o controller
            double precoTotal = controller.calcularPrecoTotal(precoLitro, numeroLitros, tipoCombustivel, formaPagamento);
            String precoTotalFormatado = String.format("%.2f", precoTotal);

            // Exiba o resultado
            TextView textViewResultado = findViewById(R.id.textViewResultado);
            textViewResultado.setText("R$ " + precoTotalFormatado);
        } catch (NumberFormatException e) {
            // Ocorreu um erro ao converter os valores para números
            mostrarMensagem("Informe valores numéricos válidos.");
        }
    }

    private void mostrarMensagem(String mensagem) {
        // Exibe uma mensagem de erro usando Toast
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    // Método para atualizar o preço do litro com base no estabelecimento selecionado
    private void atualizarPrecoLitro(String estabelecimentoName) {
        double novoPrecoLitro = estabelecimentoManager.setEstabelecimento(estabelecimentoName);
        etPrecoLitro.setText(String.valueOf(novoPrecoLitro));
    }
}
