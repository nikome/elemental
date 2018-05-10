package com.androidplot.demos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class LagrangeActivity extends Activity {
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
        setContentView(R.layout.activity_lagrange);
        x = (EditText) findViewById(R.id.Xinput);
        y = (EditText) findViewById(R.id.Yinput);
        valor = (EditText) findViewById(R.id.ValueX);
    }

    public void ingresoPointLagrange(View view) {
        String xpunto = x.getText().toString();
        String ypunto = y.getText().toString();
        puntosListx.add(Double.parseDouble(xpunto));
        puntosListy.add(Double.parseDouble(ypunto));
        x.setText("");
        y.setText("");
        Log.d("rg", "entra");
    }
    public void CalculateLagrange(View view) {
        double[] x = new double[puntosListx.size()];
        double[] y = new double[puntosListy.size()];
        for (int i = 0; i < puntosListy.size(); ++i) {
            x[i] = puntosListx.get(i);
            y[i] = puntosListy.get(i);
        }
        val = valor.getText().toString();
        interpolacionLagrange(x.length, Double.parseDouble(val), x, y);
    }

    public  void interpolacionLagrange(int nroPuntos, double valor, double[] x, double[] y){
        pResultados.clear();
        result = 0;
        String pol = "P(x): ";
        for(int k = 0; k<nroPuntos;k++){
            double productoria = 1;
            String termino = "";
            for(int i = 0; i < nroPuntos ; i++){
                if(i!=k){
                    productoria = productoria * (valor-x[i])/(x[k]-x[i]);
                    termino = termino + ("[(x-"+x[i]+")/("+x[k]+"-"+x[i]+")]");
                }
            }
            if(k==0) {
                pol = "P(x): "+(y[k] > 0 ? "+" : "") + y[k] + "*" + termino ;
                pResultados.add(pol);
            }else{
                pol = (y[k] > 0 ? "+" : "") + y[k] + "*" + termino;
                pResultados.add(pol);
            }
            result += productoria * y[k];
        }
        puntosListy.clear();
        puntosListx.clear();
        val="";
        Intent intent = new Intent(LagrangeActivity.this, LagrangeTableActivity.class);
        intent.putExtra("listap", pResultados);
        intent.putExtra("resultado", String.valueOf(result));
        startActivity(intent);
    }

}