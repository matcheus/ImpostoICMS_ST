package com.example.matheus.impostos;

import android.content.Context;
import android.icu.math.BigDecimal;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText textValorProduto;
    private EditText textDespesas;
    private TextView resultado;
    private TextView melhorRota;
    private double valorProduto;
    private double despesas;
    private final double mva = 0.3;
    private Spinner spinner;
    private double BC_ST;
    private double ICMS;
    private double ICMS_ST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.estados);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Button button = (Button) findViewById(R.id.calcular);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                botaoCalcular();
            }
        });
    }

    private void definirValores() {
        textValorProduto = (EditText) findViewById(R.id.valorProduto);
        textDespesas = (EditText) findViewById(R.id.valorDespesas);
        if (textValorProduto.getText().toString().matches("")) {
            Toast.makeText(this, "Valor do produto não foi informado", Toast.LENGTH_SHORT).show();
            return;
        }
        if (textDespesas.getText().toString().matches("")) {
            textDespesas.setText("0.00");
            textDespesas.invalidate();
        }
        valorProduto = Double.parseDouble(textValorProduto.getText().toString());
        despesas = Double.parseDouble(textDespesas.getText().toString());
    }

    private void calcularBCST() {
        BC_ST = (valorProduto + despesas) + ((valorProduto + despesas) * mva);
    }

    private void mostrarResultado() {
        resultado = (TextView) findViewById(R.id.resultado);
        String result = String.format("%.2f",ICMS_ST);
        resultado.setText("R$ " + result);
        resultado.invalidate();
        melhorRota = (TextView) findViewById(R.id.melhorRota);
        melhorRota.setText("Melhor rota: MT");
        melhorRota.invalidate();
    }

    private void botaoCalcular() {
        switch (spinner.getSelectedItemPosition()) {
            case 0:
                definirValores();
                calcularBCST();
                ICMS = (valorProduto + despesas) * 0.12;
                ICMS_ST = (BC_ST * 0.18) - ICMS;
                mostrarResultado();
                fecharTeclado();
                break;
            case 1:
                definirValores();
                calcularBCST();
                ICMS = (valorProduto + despesas) * 0.12;
                ICMS_ST = (BC_ST * 0.17) - ICMS;
                mostrarResultado();
                fecharTeclado();
                break;
            case 2:
                definirValores();
                calcularBCST();
                ICMS = (valorProduto + despesas) * 0.12;
                ICMS_ST = (BC_ST * 0.18) - ICMS;
                mostrarResultado();
                fecharTeclado();
                break;
            case 3:
                definirValores();
                calcularBCST();
                ICMS = (valorProduto + despesas) * 0.17;
                ICMS_ST = (BC_ST * 0.17) - ICMS;
                mostrarResultado();
                fecharTeclado();
                break;
            default:
                break;

        }
    }

    public void fecharTeclado() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Spinner spinner = (Spinner) findViewById(R.id.estados);
        spinner.setOnItemSelectedListener(this);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
