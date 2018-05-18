package com.androidplot.demos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class NewtonDividido2Activity extends Activity {
    private EditText points;
    private EditText valor;
    private double result;
    private String val;
    private ArrayList<Double> puntosListx = new ArrayList();
    private ArrayList<Double> puntosListy = new ArrayList();
    private ArrayList<String> pResultados = new ArrayList();
    private ArrayList<String> tablaResultados = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_dividido2);
        points = (EditText) findViewById(R.id.Points);
        valor = (EditText) findViewById(R.id.ValueX);
    }


    public void ingresoPoint(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(NewtonDividido2Activity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutNewtonPoints);
        String n = points.getText().toString();
        if (table.getChildCount() > 0) {
            table.removeAllViews();
        } else {
            for (int i = 0; i < Integer.parseInt(n); i++) {
                Log.d("entra,", "shii");
                TableRow row = new TableRow(this);
                EditText view1 = new EditText(this);
                EditText view2 = new EditText(this);
                view1.setHint("write a number");
                view1.setTextColor(Color.BLACK);
                view2.setHint("write a number");
                view2.setTextColor(Color.BLACK);
                row.addView(view1);
                row.addView(view2);
                table.addView(row);
            }
        }
        } catch (Exception e) {
            alertDialog.show();
        }
    }

    public void CalculateNewton(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(NewtonDividido2Activity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutNewtonPoints);
        String n = points.getText().toString();
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row1 = (TableRow) table.getChildAt(i);
            EditText xpoint = (EditText) row1.getChildAt(0);
            EditText ypoint = (EditText) row1.getChildAt(1);
            String numberx = xpoint.getText().toString();
            String numbery = ypoint.getText().toString();
            puntosListx.add(Double.parseDouble(numberx));
            puntosListy.add(Double.parseDouble(numbery));
        }
        double[] x = new double[puntosListx.size()];
        double[] y = new double[puntosListy.size()];
        for (int i = 0; i < puntosListy.size(); ++i) {
            x[i] = puntosListx.get(i);
            y[i] = puntosListy.get(i);
        }
        val = valor.getText().toString();

        interpolacionNewtonDiferenciasDivididas(x.length, Double.parseDouble(val), x, y);
        } catch (Exception e) {
            alertDialog.show();
        }
    }

    public void interpolacionNewtonDiferenciasDivididas(int nroPuntos, double valor,
                                                        double[] x, double[] y) {
        double[][] tabla = new double[nroPuntos][nroPuntos];
        pResultados.clear();
        tablaResultados.clear();
        for (int i = 0; i < nroPuntos; i++) {
            tabla[i][0] = y[i];
        }

        for (int i = 0; i < nroPuntos; i++) {
            for (int j = 1; j < i + 1; j++) {
                tabla[i][j] = (tabla[i][j - 1] - tabla[i - 1][j - 1]) / (x[i] - x[i - j]);

            }
        }
        float[][] tablaResult = new float[nroPuntos][nroPuntos];
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla.length; j++) {
                tablaResult[i][j] = (float) tabla[i][j];

            }
        }
        String resultado;
        resultado = "Xi" + "\n";
        for (int j = 0; j < tabla.length; j++) {
            resultado += tablaResult[j][0] + "\n";
        }
        tablaResultados.add(resultado);
        resultado = "";
        for (int i = 0; i < tabla.length; i++) {
            resultado = "f" + (i) + "[]" + "   \n";
            for (int j = 0; j < tabla.length; j++) {
                resultado += tablaResult[j][i] + "   \n";
            }
            tablaResultados.add(resultado);
            resultado = "";
        }

        String pol = "";
        String temp = "";
        result = tabla[0][0];
        double aux = 1;
        for (int i = 1; i < nroPuntos; i++) {
            temp = temp + "(x" + "-" + (x[i - 1]) + ")";
            if (i == 1) {
                pol = "P(x): " + String.valueOf(puntosListy.get(0)) + (tabla[i][i] > 0 ? "+" : "") + (tabla[i][i] + "*" + temp);
                pResultados.add(pol);
            } else {
                pol = (tabla[i][i] > 0 ? "+" : "") + (tabla[i][i] + "*" + temp);
                pResultados.add(pol);
            }
            aux = aux * (valor - x[i - 1]);
            result = result + tabla[i][i] * aux;
        }
        puntosListy.clear();
        puntosListx.clear();
        val = "";

        Intent intent = new Intent(NewtonDividido2Activity.this, BewtonDivididoTableActivity.class);
        intent.putExtra("listap", pResultados);
        intent.putExtra("resultado", String.valueOf(result));
        intent.putExtra("matriz", tablaResultados);
        startActivity(intent);
    }
}