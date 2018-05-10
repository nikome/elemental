package com.androidplot.demos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NewtonDividido2Activity extends Activity {
    private EditText x;
    private EditText y;
    private EditText valor;
    private double result;
    private String val;
    private ArrayList<Double> puntosListx = new ArrayList();
    private ArrayList<Double> puntosListy = new ArrayList();
    private ArrayList<String> pResultados = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_dividido2);
        x = (EditText) findViewById(R.id.Xinput);
        y = (EditText) findViewById(R.id.Yinput);
        valor = (EditText) findViewById(R.id.ValueX);


    }


    public void ingresoPoint(View view) {
        String xpunto = x.getText().toString();
        String ypunto = y.getText().toString();
        puntosListx.add(Double.parseDouble(xpunto));
        puntosListy.add(Double.parseDouble(ypunto));
        x.setText("");
        y.setText("");
        Log.d("rg", "entra");

    }

    public void CalculateNewton(View view) {
        double[] x = new double[puntosListx.size()];
        double[] y = new double[puntosListy.size()];
        for (int i = 0; i < puntosListy.size(); ++i) {
            x[i] = puntosListx.get(i);
            y[i] = puntosListy.get(i);
        }
        val = valor.getText().toString();
        interpolacionNewtonDiferenciasDivididas(x.length, Double.parseDouble(val), x, y);
    }

    public void interpolacionNewtonDiferenciasDivididas(int nroPuntos, double valor, double[] x, double[] y) {
        double[][] tabla = new double[nroPuntos][nroPuntos];
        pResultados.clear();
        for (int i = 0; i < nroPuntos; i++) {
            tabla[i][0] = y[i];
        }

        for (int i = 0; i < nroPuntos; i++) {
            for (int j = 1; j < i + 1; j++) {
                tabla[i][j] = (tabla[i][j - 1] - tabla[i - 1][j - 1]) / (x[i] - x[i - j]);

            }
        }
        String pol = "";
        String temp = "";
        result = tabla[0][0];
        double aux = 1;
        for (int i = 1; i < nroPuntos; i++) {
            temp = temp + "(x" + "-" + (x[i - 1]) + ")";
            if(i==1){
                pol = "P(x): " + String.valueOf(puntosListy.get(0))+(tabla[i][i] > 0 ? "+" : "") + (tabla[i][i] + "*" + temp);
                pResultados.add(pol);
            }else {
                pol = (tabla[i][i] > 0 ? "+" : "") + (tabla[i][i] + "*" + temp);
                pResultados.add(pol);
            }
            aux = aux * (valor - x[i - 1]);
            result = result + tabla[i][i] * aux;
        }
        puntosListy.clear();
        puntosListx.clear();
        val="";

        Intent intent = new Intent(NewtonDividido2Activity.this, BewtonDivididoTableActivity.class);
        intent.putExtra("listap", pResultados);
        intent.putExtra("resultado", String.valueOf(result));
        startActivity(intent);
    }


}
