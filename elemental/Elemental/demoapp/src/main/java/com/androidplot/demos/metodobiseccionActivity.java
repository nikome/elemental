package com.androidplot.demos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.lang.Math;
import java.util.ArrayList;

public class metodobiseccionActivity extends Activity {

    private EditText funcion;
    private EditText Tolerancia;
    private EditText inferior;
    private EditText superior;
    private TextView Resultado;
    private ArrayList<String> iteraciones = new ArrayList();
    private ArrayList<String> xmList = new ArrayList();
    private ArrayList<String> ErrorList = new ArrayList();
    private ArrayList<String> fxmList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metodobiseccion);
        funcion=(EditText)findViewById(R.id.funcion);
        Tolerancia=(EditText)findViewById(R.id.Tolerancia);
        inferior=(EditText)findViewById(R.id.inferior);
        superior=(EditText)findViewById(R.id.superior);
        Resultado=(TextView)findViewById(R.id.Resultado);

        Button metodoP = (Button) findViewById(R.id.showTable);
        metodoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(metodobiseccionActivity.this, TablaBiseccionActivity.class);
                intent.putExtra("iteraciones", iteraciones);
                intent.putExtra("xmList", xmList);
                intent.putExtra("ErrorList", ErrorList);
                intent.putExtra("fxmList", fxmList);
                startActivity(intent);

            }
        });
    }
    public void Calcular(View view) {
        iteraciones.clear();
        xmList.clear();
        ErrorList.clear();
        fxmList.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(metodobiseccionActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Error ingresando variables, intente de nuevo por favor");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {



            String f = funcion.getText().toString();
            String tol = Tolerancia.getText().toString();
            String xi = inferior.getText().toString();
            String xs = superior.getText().toString();

            int res = 0;
            double x1 = Double.parseDouble(xi);
            double x2 = Double.parseDouble(xs);
            double r = x2 - x1;
            com.androidplot.demos.com.udojava.evalex.Expression expression2= new com.androidplot.demos.com.udojava.evalex.Expression("(log("+r+")-log("+ Double.parseDouble(tol)+"))/log(2)");
            r = expression2.eval().doubleValue();
            res = (int)r;
            if(r == (int)r){
                res = res - 1;
            }
            res=res+1;
            double fxi;
            double fxs;
            int contador;
            double tolerancia;
            String Xms;
            BigDecimal d;
            tolerancia =Double.parseDouble(tol) ;
            com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);
            expression.setVariable("x", xi);
            d = expression.eval();
            fxi = d.doubleValue();
            expression.setVariable("x", xs);
            d = expression.eval();
            fxs = d.doubleValue();
            double Xm = 0;
            double fxm = 0;
            double Xaux = 0;
            double error = 0;
            if (fxi == 0) {
                String resu = String.valueOf(xi);
                Resultado.setText(resu);
            } else if (fxs == 0) {
                String resu = String.valueOf(xs);
                Resultado.setText(resu);
            } else if (fxi * fxs < 0) {
                Xm = ((Double.parseDouble(xi) + Double.parseDouble(xs)) / 2);
                Xms = Double.toString(Xm);
                expression.setVariable("x", Xms);
                d = expression.eval();
                fxm = d.doubleValue();
                contador = 1;
                iteraciones.add(String.valueOf(contador));
                xmList.add(String.valueOf(Xm));
                error = tolerancia + 1;
                ErrorList.add(String.valueOf("---"));
                fxmList.add(String.valueOf(fxm));
                while ((error > tolerancia) && fxm != 0 && contador < res) {
                    if (fxi * fxm < 0) {
                        xs = Double.toString(Xm);
                        fxs = fxm;
                    } else {
                        xi = Double.toString(Xm);
                        fxi = fxm;
                    }
                    Xaux = Xm;
                    Xm = ((Double.parseDouble(xi) + Double.parseDouble(xs)) / 2);
                    Xms = Double.toString(Xm);
                    expression.setVariable("x", Xms);
                    d = expression.eval();
                    fxm = d.doubleValue();
                    error = Math.abs(Xm - Xaux);
                    contador++;
                    iteraciones.add(String.valueOf(contador));
                    xmList.add(String.valueOf(Xm));
                    ErrorList.add(String.valueOf(error));
                    fxmList.add(String.valueOf(fxm));
                }
                if (fxm == 0) {
                    System.out.println(Xm + " Es raiz");

                    String resu = String.valueOf(Xm);
                    Resultado.setText(resu);
                } else if (error < tolerancia) {
                    String resu = String.valueOf(Xm);
                    Resultado.setText(resu);
                } else {
                    Resultado.setText("Fracaso el numero de iteraciones ");
                }
            } else {
                Resultado.setText("El intervalo es inadecuado");
            }

    }catch(Exception e){
            alertDialog.show();
    }

    }

    }

