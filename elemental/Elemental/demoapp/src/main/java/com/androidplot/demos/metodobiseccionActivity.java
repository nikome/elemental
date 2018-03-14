package com.androidplot.demos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.*;
import com.androidplot.demos.com.udojava.evalex.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.lang.Math;

public class metodobiseccionActivity extends Activity {

    private EditText funcion;
    private EditText iteraciones;
    private EditText inferior;
    private EditText superior;
    private TextView Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metodobiseccion);
        funcion=(EditText)findViewById(R.id.funcion);
        iteraciones=(EditText)findViewById(R.id.iteraciones);
        inferior=(EditText)findViewById(R.id.inferior);
        superior=(EditText)findViewById(R.id.superior);
        Resultado=(TextView)findViewById(R.id.Resultado);
    }
    public void Calcular(View view){
        System.out.println("entro");
        String f=funcion.getText().toString();
        String n=iteraciones.getText().toString();
        String xi=inferior.getText().toString();
        String xs=superior.getText().toString();
        double fxi;
        double fxs;
        int contador;
        double tolerancia;
        String Xms;
        BigDecimal d;
        tolerancia = 0.5E-3;
        com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);
        expression.setVariable("x",xi);
        d = expression.eval();
        fxi = d.doubleValue();
        expression.setVariable("x",xs);
        d = expression.eval();
        fxs = d.doubleValue();
        double Xm = 0;
        double fxm = 0;
        double Xaux = 0;
        double error = 0;
        if(fxi == 0){
            String resu=String.valueOf(xi);
            Resultado.setText(resu);
        }
        else if(fxs == 0){
            String resu=String.valueOf(xs);
            Resultado.setText(resu);
        }
        else if(fxi * fxs < 0){
            Xm = ((Double.parseDouble(xi) + Double.parseDouble(xs))/2);
            Xms = Double.toString(Xm);
            expression.setVariable("x",Xms);
            d = expression.eval();
            fxm = d.doubleValue();
            contador = 1;
            error = tolerancia + 1;
            while((error>tolerancia) && fxm != 0 && contador < Integer.parseInt(n)){
                if(fxi * fxm < 0){
                    xs = Double.toString(Xm);
                    fxs = fxm;
                }
                else{
                    xi = Double.toString(Xm);
                    fxi = fxm;
                }
                Xaux = Xm;
                Xm = ((Double.parseDouble(xi) + Double.parseDouble(xs))/2);
                Xms = Double.toString(Xm);
                expression.setVariable("x",Xms);
                d = expression.eval();
                fxm = d.doubleValue();
                error = Math.abs(Xm-Xaux);
                contador++;
            }
            if(fxm == 0){
                System.out.println(Xm + " Es raiz");

                String resu=String.valueOf(Xm);
                Resultado.setText(resu);
            }
            else if(error < tolerancia){
                String resu=String.valueOf(Xm);
                Resultado.setText(resu);
            }
            else{
                Resultado.setText("Fracaso el numero de iteraciones ");
            }
        }
        else{
            Resultado.setText("El intervalo es inadecuado");
        }
    }



    }

