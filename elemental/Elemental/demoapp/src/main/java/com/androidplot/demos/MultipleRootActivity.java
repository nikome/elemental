package com.androidplot.demos;

import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.math.BigDecimal;
import java.lang.Math;
import java.util.ArrayList;

public class MultipleRootActivity extends Activity {
    private EditText funciong;
    private EditText Tolerancia;
    private EditText xini;
    private EditText Iteraciones;
    private EditText PDerivada;
    private EditText SDerivada;
    private TextView Resultado;
    private Switch relativeAbsolute;
    ArrayList<String> iteracionesList = new ArrayList();
    ArrayList<String> xnList = new ArrayList();
    ArrayList<String> fxList = new ArrayList();
    ArrayList<String> fxpList = new ArrayList();
    ArrayList<String> fxppList = new ArrayList();
    ArrayList<String> ErrorList = new ArrayList();
    private Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_root);
        relativeAbsolute=(Switch) findViewById(R.id.switchARM);
        funciong = (EditText) findViewById(R.id.funcion);
        Tolerancia = (EditText) findViewById(R.id.Tolerancia);
        xini = (EditText) findViewById(R.id.xini);
        Iteraciones = (EditText) findViewById(R.id.Iteraciones);
        PDerivada = (EditText) findViewById(R.id.derivada);
        SDerivada = (EditText) findViewById(R.id.SegundaDer);
        Resultado = (TextView) findViewById(R.id.Resultado);
        Button metodoP = (Button) findViewById(R.id.showTable);
        myDialog = new Dialog(this);
        metodoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultipleRootActivity.this, MultipleRootTable2Activity.class);
                intent.putExtra("iteraciones", iteracionesList);
                intent.putExtra("xnList", xnList);
                intent.putExtra("ErrorList", ErrorList);
                intent.putExtra("fxList", fxList);
                intent.putExtra("fpxList", fxpList);
                intent.putExtra("fppxList", fxppList);
                startActivity(intent);
            }
        });

        Button graficar = (Button) findViewById(R.id.Graficador);
        graficar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultipleRootActivity.this, GraphActivity.class);
                intent.putExtra("funcion",funciong.getText().toString());
                startActivity(intent);
            }
        });
    }
    public void ShowPopuproots(View v){
        TextView txtClose;
        myDialog.setContentView(R.layout.raices_multiples_ayuda);
        txtClose=(TextView) myDialog.findViewById(R.id.close);
        ImageView image = (ImageView) myDialog.findViewById(R.id.funcion);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    public void Calcular(View view) {
        iteracionesList.clear();
        xnList.clear();
        fxList.clear();
        fxpList.clear();
        fxppList.clear();
        ErrorList.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(MultipleRootActivity.this).create();
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
            String niters = Iteraciones.getText().toString();
            String Xo = xini.getText().toString();
            String funciond = PDerivada.getText().toString();
            String funcion2d = SDerivada.getText().toString();
            Double fx, fdx, fddx, Xb;
            Double Xa = Double.parseDouble(Xo);
            int niter = Integer.parseInt(niters);
            int contador = 1;
            BigDecimal d;
            Double denominador = 0.0;
            com.androidplot.demos.com.udojava.evalex.Expression tole = new com.androidplot.demos.com.udojava.evalex.Expression(tol);
            Double tolerancia = tole.eval().doubleValue();
            Double error = tolerancia + 1;
            com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(funcion);
            expression.setPrecision(16);
            com.androidplot.demos.com.udojava.evalex.Expression expressiond = new com.androidplot.demos.com.udojava.evalex.Expression(funciond);
            expressiond.setPrecision(16);
            com.androidplot.demos.com.udojava.evalex.Expression expressiondd = new com.androidplot.demos.com.udojava.evalex.Expression(funcion2d);
            expressiondd.setPrecision(16);
            expression.setVariable("x", Double.toString(Xa));
            expressiond.setVariable("x", Double.toString(Xa));
            expressiondd.setVariable("x", Double.toString(Xa));
            d = expression.eval();
            fx = d.doubleValue();
            d = expressiond.eval();
            fdx = d.doubleValue();
            d = expressiondd.eval();
            fddx = d.doubleValue();
            denominador = (Math.pow(fdx, 2)) - (fx * fddx);
            iteracionesList.add(0, String.valueOf(contador));
            xnList.add(String.valueOf(Xa));
            fxList.add(String.valueOf(fx));
            fxpList.add(String.valueOf(fdx));
            fxppList.add(String.valueOf(fddx));
            ErrorList.add(String.valueOf("---"));
            while ((error > tolerancia) && (fx != 0) && (denominador != 0) && (contador < niter)) {
                Xb = Xa - ((fx * fdx) / denominador);
                if(relativeAbsolute.isChecked()) {
                    error = Math.abs(Xb - Xa);
                }else{
                    error = Math.abs((Xb - Xa)/Xb);
                }
                expression.setVariable("x", Double.toString(Xb));
                expressiond.setVariable("x", Double.toString(Xb));
                expressiondd.setVariable("x", Double.toString(Xb));
                d = expression.eval();
                fx = d.doubleValue();
                d = expressiond.eval();
                fdx = d.doubleValue();
                d = expressiondd.eval();
                fddx = d.doubleValue();
                Xa = Xb;
                denominador = (Math.pow(fdx, 2)) - (fx * fddx);
                contador++;
                iteracionesList.add(String.valueOf(contador));
                xnList.add(String.valueOf(Xa));
                fxList.add(String.valueOf(fx));
                fxpList.add(String.valueOf(fdx));
                fxppList.add(String.valueOf(fddx));
                ErrorList.add(String.valueOf(error));
            }
            if (error < tolerancia) {
                String resu = String.valueOf(Double.toString(Xa) + " is an aproximation");
                Resultado.setText(resu);
            } else if (fx == 0) {
                String resu = String.valueOf(Double.toString(Xa) + " is a root");
                Resultado.setText(resu);
            } else if (denominador == 0) {
                String resu = String.valueOf("Failure of the method");
                Resultado.setText(resu);
            } else {
                String resu = String.valueOf("Limit of iteration reached");
                Resultado.setText(resu);
            }

        } catch (Exception e) {
            alertDialog.show();
        }
    }
}


