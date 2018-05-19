package com.androidplot.demos;

import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.math.BigDecimal;
import java.lang.Math;
import java.util.ArrayList;

public class SecantMethodActivity extends Activity {
    private EditText funcion;
    private EditText Tolerancia;
    private EditText X0;
    private EditText X1;
    private EditText Iteraciones;
    private TextView Resultado;
    private TextView ResultadoMuller;
    private Switch relativeAbsolute;
    ArrayList<String> iteracionesList = new ArrayList();
    ArrayList<String> xnList = new ArrayList();
    ArrayList<String> fxList = new ArrayList();
    ArrayList<String> ErrorList = new ArrayList();
    private Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secant_method);
        funcion=(EditText)findViewById(R.id.funcion);
        Tolerancia=(EditText)findViewById(R.id.Tolerancia);
        X0=(EditText)findViewById(R.id.X0);
        X1=(EditText)findViewById(R.id.X1);
        Iteraciones=(EditText)findViewById(R.id.Iteraciones);
        Resultado=(TextView)findViewById(R.id.Resultado);
        ResultadoMuller=(TextView) findViewById(R.id.ResultMuller);
        Button metodoP = (Button) findViewById(R.id.showTable);
        relativeAbsolute=(Switch) findViewById(R.id.switchARS);
        myDialog = new Dialog(this);
        metodoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecantMethodActivity.this, SecantTableActivity.class);
                intent.putExtra("IteracionesList", iteracionesList);
                intent.putExtra("xnList", xnList);
                intent.putExtra("fxList", fxList);
                intent.putExtra("ErrorList", ErrorList);
                startActivity(intent);

            }
        });
        Button graficar = (Button) findViewById(R.id.Graficador);
        graficar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecantMethodActivity.this, GraphActivity.class);
                intent.putExtra("funcion",funcion.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void ShowPopupSecante(View v){
        TextView txtClose;
        myDialog.setContentView(R.layout.secante_ayuda);
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
        ErrorList.clear();
        fxList.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(SecantMethodActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
            String f = funcion.getText().toString();
            String tol = Tolerancia.getText().toString();
            String niters = Iteraciones.getText().toString();
            String xo = X0.getText().toString();
            String x1 = X1.getText().toString();

            com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);
            expression.setPrecision(16);
            com.androidplot.demos.com.udojava.evalex.Expression tole = new com.androidplot.demos.com.udojava.evalex.Expression(tol);
            int niter = Integer.parseInt(niters);
            expression.setPrecision(16);
            Double tolerancia = tole.eval().doubleValue();
            Double fx = 0.0;
            BigDecimal d;
            Double fx1 = 0.0;
            Double error = 0.0;
            int cont = 0;
            Double x2 = 0.0;
            Double x1a = 0.0;
            Double xoa = 0.0;
            Double den = -1.0;
            expression.setVariable("x", xo);
            d = expression.eval();
            fx = d.doubleValue();
            if (fx == 0) {
                String resu = String.valueOf(xo + " is a root");
                Resultado.setText(resu);
            } else {
                expression.setVariable("x", x1);
                d = expression.eval();
                fx1 = d.doubleValue();
                error = tolerancia + 1;
                den = fx1 - fx;
                iteracionesList.add(String.valueOf(cont));
                xnList.add(String.valueOf(xo));
                fxList.add(String.valueOf(fx1));
                ErrorList.add("---");
                while ((error > tolerancia) && (fx1 != 0) && (den != 0) && cont < niter) {
                    x1a = Double.parseDouble(x1);
                    xoa = Double.parseDouble(xo);
                    x2 = x1a - fx1 * (x1a - xoa) / den;
                    if(relativeAbsolute.isChecked()) {
                        error = Math.abs(x2 - x1a);
                    }else{
                        error = Math.abs((x2 - x1a)/x2);
                    }
                    xo = x1;
                    fx = fx1;
                    x1 = Double.toString(x2);
                    expression.setVariable("x", x1);
                    d = expression.eval();
                    fx1 = d.doubleValue();
                    den = fx1 - fx;
                    cont++;
                    iteracionesList.add(String.valueOf(cont));
                    xnList.add(String.valueOf(x1));
                    fxList.add(String.valueOf(fx1));
                    ErrorList.add(String.valueOf(error));
                }
                if (fx1 == 0) {
                    String resu = String.valueOf(x1 + " is a root");
                    Resultado.setText(resu);
                } else if (error < tolerancia) {
                    String resu = String.valueOf(x1 + " is an aproximation");
                    Resultado.setText(resu);
                } else if (den == 0) {
                    String resu = String.valueOf("A possible multiple root");
                    Resultado.setText(resu);
                } else {
                    String resu = String.valueOf("No results");
                    Resultado.setText(resu);
                }

            }


        } catch (Exception e) {
            alertDialog.show();
        }
    }
        public void Muller(View view){
        iteracionesList.clear();
        xnList.clear();
        ErrorList.clear();
        fxList.clear();
        String f=funcion.getText().toString();
        String tolerancia=Tolerancia.getText().toString();
        String niter=Iteraciones.getText().toString();
        String q=X0.getText().toString();
        String w=X1.getText().toString();

        Double a = Double.parseDouble(q);
        Double b = Double.parseDouble(w);
        double res = 0.0;
        Double c = (a+b)/2;
        Double contador = 0.0;
        com.androidplot.demos.com.udojava.evalex.Expression tol = new com.androidplot.demos.com.udojava.evalex.Expression(tolerancia);
        Double tole = tol.eval().doubleValue();
        com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);
        expression.setVariable("x",Double.toString(a));
        Double f1 = expression.eval().doubleValue();
        expression.setVariable("x",Double.toString(b));
        Double f2 = expression.eval().doubleValue();
        expression.setVariable("x",Double.toString(c));
        Double f3 = expression.eval().doubleValue();
        double d1 = f1 - f3;
        double d2 = f2 - f3;
        double h1 = a - c;
        double h2 = b - c;
        double a0 = f3;
        double x = 0.0;
        double y = 0.0;
        double a1 = 0.0;
        double a2 = 0.0;
        try {
             a1 = (((d2 * Math.pow(h1, 2)) - (d1 * Math.pow(h2, 2)))
                    / ((h1 * h2) * (h1 - h2)));
             a2 = (((d1 * h2) - (d2 * h1)) / ((h1 * h2) * (h1 - h2)));
             x = ((-2 * a0) / (a1 + Math.abs(Math.sqrt(a1 * a1 - 4 * a0 * a2))));
             y = ((-2 * a0) / (a1 - Math.abs(Math.sqrt(a1 * a1 - 4 * a0 * a2))));
        }catch(com.androidplot.demos.com.udojava.evalex.Expression.ExpressionException ex){
            String resu = String.valueOf("Check the inputs");
            ResultadoMuller.setText(resu);
        }

        if (x >= y)
            res = x + c;
        else
            res = y + c;
        a = b;
        b = c;
        c = res;
            //expression.setVariable("x",Double.toString(res));
            //Double fx = expression.eval().doubleValue();
        Double error = tole + 1.0;
            //iteracionesList.add(String.valueOf(contador));
            //xnList.add(String.valueOf(res));
            //fxList.add(String.valueOf(fx));
            //ErrorList.add( "---");
        try{
            while(contador < Integer.parseInt(niter) && error > tole) {
                expression.setVariable("x", Double.toString(a));
                f1 = expression.eval().doubleValue();
                expression.setVariable("x", Double.toString(b));
                f2 = expression.eval().doubleValue();
                expression.setVariable("x", Double.toString(c));
                f3 = expression.eval().doubleValue();
                d1 = f1 - f3;
                d2 = f2 - f3;
                h1 = a - c;
                h2 = b - c;
                a0 = f3;
                a1 = (((d2 * Math.pow(h1, 2)) - (d1 * Math.pow(h2, 2)))
                        / ((h1 * h2) * (h1 - h2)));
                a2 = (((d1 * h2) - (d2 * h1)) / ((h1 * h2) * (h1 - h2)));
                x = ((-2 * a0) / (a1 + Math.abs(Math.sqrt(a1 * a1 - 4 * a0 * a2))));
                y = ((-2 * a0) / (a1 - Math.abs(Math.sqrt(a1 * a1 - 4 * a0 * a2))));


                if (x >= y){
                    res = x + c;


                }else{
                    res = y + c;

                }

                if(relativeAbsolute.isChecked()) {
                    error = Math.abs(res - c);
                }else{
                    error = Math.abs((res - c)/res);
                }
                a = b;
                b = c;
                c = res;
                contador++;

            }
        }catch(com.androidplot.demos.com.udojava.evalex.Expression.ExpressionException ex){
            String resu = String.valueOf("Check the inputs");
            ResultadoMuller.setText(resu);
        }
        if (error < tole){
            String resu = String.valueOf(res+" is a root");
            ResultadoMuller.setText(resu);
        }else{
            String resu = String.valueOf("No results");
            ResultadoMuller.setText(resu);
        }
    }


}
