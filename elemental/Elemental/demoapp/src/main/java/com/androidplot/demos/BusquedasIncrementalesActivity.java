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
public class BusquedasIncrementalesActivity extends Activity {
    private EditText funcion;
    private EditText delta;
    private EditText xinicial;
    private EditText iterationes;
    private TextView Resultado;
    ArrayList<String> iteracionesList = new ArrayList();
    ArrayList<String> xnList = new ArrayList();
    ArrayList<String> fxList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquedas_incrementales);
        funcion = (EditText) findViewById(R.id.funcioninc);
        iterationes = (EditText) findViewById(R.id.iteracionesinc);
        xinicial = (EditText) findViewById(R.id.xinicialinc);
        delta = (EditText) findViewById(R.id.deltainc);
        Resultado = (TextView) findViewById(R.id.Resultado);

        Button metodoP = (Button) findViewById(R.id.showTable);
        metodoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusquedasIncrementalesActivity.this, MetodoIncrementalTablaActivity.class);
                intent.putExtra("iteracionesinc", iteracionesList);
                intent.putExtra("xnListinc", xnList);
                intent.putExtra("fxlistinc", fxList);

                startActivity(intent);

            }
        });

        Button grafica = (Button) findViewById(R.id.Graficador);
        grafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusquedasIncrementalesActivity.this, GraphActivity.class);
                intent.putExtra("funcion",funcion.getText().toString());
                startActivity(intent);

            }
        });
    }

    public void Calcular(View view) {
        String f = funcion.getText().toString();
        String xini = xinicial.getText().toString();
        String delt = delta.getText().toString();
        String iter = iterationes.getText().toString();
        AlertDialog alertDialog = new AlertDialog.Builder(BusquedasIncrementalesActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
            int iterations = Integer.parseInt(iter);
            double Delta = Double.parseDouble(delt);
            double x0Double = Double.parseDouble(xini);
            double x1;
            int counter;
            BigDecimal evaluatedFunction, evaluatedFunctionAux = null;
            com.androidplot.demos.com.udojava.evalex.Expression functionToEval = new com.androidplot.demos.com.udojava.evalex.Expression(f);

            functionToEval.setVariable("x", xini);
            evaluatedFunction = functionToEval.eval();
            double tol = Math.pow(10, -9);
            double AbsFuction = Math.abs(evaluatedFunction.doubleValue());
            if (AbsFuction < tol) {
                String resu = String.valueOf(xini + "is a root");
                Resultado.setText(resu);
            } else {
                x1 = x0Double + Delta;
                counter = 1;
                functionToEval.setVariable("x", String.valueOf(x1));
                evaluatedFunctionAux = functionToEval.eval();
                iteracionesList.add(String.valueOf(counter));
                xnList.add(String.valueOf(x1));
                fxList.add(String.valueOf(evaluatedFunction));
                while (((evaluatedFunction.doubleValue() * evaluatedFunctionAux.doubleValue()) > 0) && (counter < iterations)) {
                    x0Double = x1;
                    evaluatedFunction = evaluatedFunctionAux;
                    x1 = x0Double + Delta;
                    functionToEval.setVariable("x", String.valueOf(x1));
                    evaluatedFunctionAux = functionToEval.eval();
                    counter++;
                    iteracionesList.add(String.valueOf(counter));
                    xnList.add(String.valueOf(x1));
                    fxList.add(String.valueOf(evaluatedFunction));
                }

                if ((Math.abs(evaluatedFunctionAux.doubleValue()) < tol)) {
                    String resu = String.valueOf(x1 + "is a root");
                    Resultado.setText(resu);
                } else if ((evaluatedFunction.doubleValue() * evaluatedFunctionAux.doubleValue()) < tol) {
                    String resu = String.valueOf("there is a root between of " + x0Double + " and " + x1);
                    Resultado.setText(resu);
                } else {
                    String resu = String.valueOf("Fail");
                    Resultado.setText(resu);
                }
            }
        } catch (Exception e) {
            alertDialog.show();
        }
    }
}



