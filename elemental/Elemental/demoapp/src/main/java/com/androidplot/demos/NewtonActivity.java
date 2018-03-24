package com.androidplot.demos;

import android.os.Bundle;
import android.app.Activity;
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
public class NewtonActivity extends Activity {
    private EditText funciong;
    private EditText Tolerancia;
    private EditText xini;
    private EditText Iteraciones;
    private EditText Derivada;
    private TextView Resultado;
    private ArrayList<String> iteracionesList = new ArrayList();
    private ArrayList<String> xnList = new ArrayList();
    private ArrayList<String> fxList = new ArrayList();
    private ArrayList<String> fpxList = new ArrayList();
    private ArrayList<String> ErrorList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton);
        funciong = (EditText) findViewById(R.id.funcion);
        Tolerancia = (EditText) findViewById(R.id.Tolerancia);
        xini = (EditText) findViewById(R.id.xini);
        Iteraciones = (EditText) findViewById(R.id.Iteraciones);
        Derivada = (EditText) findViewById(R.id.derivada);
        Resultado = (TextView) findViewById(R.id.Resultado);

        Button metodoP = (Button) findViewById(R.id.showTable);
        metodoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewtonActivity.this, NewtonTableActivity.class);
                intent.putExtra("iteraciones", iteracionesList);
                intent.putExtra("xnList", xnList);
                intent.putExtra("ErrorList", ErrorList);
                intent.putExtra("fxList", fxList);
                intent.putExtra("fpxList", fpxList);
                startActivity(intent);
            }
        });
    }

    public void Calcular(View view) {
        iteracionesList.clear();
        xnList.clear();
        fxList.clear();
        fpxList.clear();
        ErrorList.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(NewtonActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
            String funcion = funciong.getText().toString();
            String tol = Tolerancia.getText().toString();
            String niter = Iteraciones.getText().toString();
            String Xo = xini.getText().toString();
            String funciond = Derivada.getText().toString();
            double fx;
            double fdx;
            BigDecimal d;
            com.androidplot.demos.com.udojava.evalex.Expression tole = new com.androidplot.demos.com.udojava.evalex.Expression(tol);
            tole.setPrecision(16);
            Double tolerancia = tole.eval().doubleValue();
            String x;
            //Empieza el metodo
            com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(funcion);

            expression.setPrecision(16);
            com.androidplot.demos.com.udojava.evalex.Expression expressiond = new com.androidplot.demos.com.udojava.evalex.Expression(funciond);
            expressiond.setPrecision(16);
            expression.setVariable("x", Xo);
            expressiond.setVariable("x", Xo);
            d = expression.eval();
            fx = d.doubleValue();
            d = expressiond.eval();
            fdx = d.doubleValue();
            double X1 = 0;
            int contador = 0;
            double error = tolerancia + 1;
            iteracionesList.add(String.valueOf(contador));
            xnList.add(Xo);
            fxList.add(String.valueOf(fx));
            fpxList.add(String.valueOf(fdx));
            ErrorList.add("---");
            while ((error > tolerancia) && (fx != 0) && (fdx != 0) && (contador < Integer.parseInt(niter))) {
                X1 = Double.parseDouble(Xo) - (fx / fdx);
                expression.setVariable("x", Double.toString(X1));
                expressiond.setVariable("x", Double.toString(X1));
                d = expression.eval();
                fx = d.doubleValue();
                d = expressiond.eval();
                fdx = d.doubleValue();

                error = Math.abs(X1 - Double.parseDouble(Xo));
                Xo = Double.toString(X1);
                contador++;
                iteracionesList.add(String.valueOf(contador));
                xnList.add(Xo);
                fxList.add(String.valueOf(fx));
                fpxList.add(String.valueOf(fdx));
                ErrorList.add(String.valueOf(error));
            }
            if (fx == 0) {
                String resu = String.valueOf(Xo + " is a root");
                Resultado.setText(resu);
            } else if (error < tolerancia) {
                String resu = String.valueOf(Double.toString(X1) + " is an aproximation");
                Resultado.setText(resu);
            } else if (fdx == 0) {
                String resu = String.valueOf(Double.toString(X1) + " is a possible multiple root");
                Resultado.setText(resu);

            } else {
                String resu = String.valueOf("Failed the number of iterations");
                Resultado.setText(resu);
            }
        } catch (Exception e) {
            alertDialog.show();
        }
    }
}



