package com.androidplot.demos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
    private TextView aitkenresu;

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
        aitkenresu=(TextView) findViewById(R.id.AitkenResult);

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
            String xi = inferior.getText().toString();
            String xs = superior.getText().toString();

            int res = 0;
            double x1 = Double.parseDouble(xi);
            double x2 = Double.parseDouble(xs);
            double r = x2 - x1;
            com.androidplot.demos.com.udojava.evalex.Expression expression2= new com.androidplot.demos.com.udojava.evalex.Expression("(log("+r+")-log("+ Double.parseDouble(tol)+"))/log(2)");
            expression2.setPrecision(16);
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
            expression.setPrecision(16);
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
                    Resultado.setText("Limit of iterations reached");
                }
            } else {
                Resultado.setText("No results");
            }

    }catch(Exception e){
            alertDialog.show();
    }

    }

    Double  Xs;
    Double  Xi;
    Double error2 = 1.0;
    Double Xaux = 0.0;
    public Double Biseccion(String f, double tol){
        Double Xm = 0.0;
        com.androidplot.demos.com.udojava.evalex.Expression expresion = new com.androidplot.demos.com.udojava.evalex.Expression(f);
        expresion.setPrecision(16);
        expresion.setVariable("x", Xs+"");
        Double fxs = expresion.eval().doubleValue();
        expresion.setVariable("x", this.Xi+"");
        Double fxi = expresion.eval().doubleValue();
        if(fxs == 0){
            error2 = 0.0;
            return this.Xs;
        }else if(fxi == 0){
            error2 = 0.0;
            return this.Xi;
        }else {
            if (error2 > tol) {
                if (fxi * fxs < 0) {
                    Xm = (this.Xs + this.Xi) / 2;
                    expresion.setVariable("x", Xm + "");
                    Double fxm = expresion.eval().doubleValue();
                    fxmList.add(String.valueOf(fxm));
                    if (fxi * fxm < 0) {
                        this.Xs = Xm;
                        fxs = fxm;
                    } else {
                        this.Xi = Xm;
                        fxi = fxm;
                    }
                    Xaux = Xm;
                    Xm = (Xi + Xs)/2;
                    error2 = Math.abs(Xm - Xaux);
                }
            }else{
                return Xaux;
            }
        }
        return Xm;
    }

    public void AitkenMethod(View view){
        iteraciones.clear();
        xmList.clear();
        ErrorList.clear();
        fxmList.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(metodobiseccionActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        String f = funcion.getText().toString();
        String tole = Tolerancia.getText().toString();
        String xi = inferior.getText().toString();
        String xs = superior.getText().toString();

        int res = 0;
        double x1 = Double.parseDouble(xi);
        double x2 = Double.parseDouble(xs);
        double r = x2 - x1;
        com.androidplot.demos.com.udojava.evalex.Expression expression2= new com.androidplot.demos.com.udojava.evalex.Expression("(log("+r+")-log("+ Double.parseDouble(tole)+"))/log(2)");
        expression2.setPrecision(16);
        r = expression2.eval().doubleValue();
        res = (int)r;
        if(r == (int)r){
            res = res - 1;
        }
        res=res+1;
        this.Xs = Double.parseDouble(xs);
        this.Xi = Double.parseDouble(xi);
        Double tol = Double.parseDouble(tole);
        error2 = tol + 1;
        Xaux = 13.5;
        Double X1 = Biseccion(f,tol);
        Double X2 = Biseccion(f,tol);
        Double X3 = Biseccion(f,tol);
        Double Ax1 = Ax(X1,X2,X3);
        Double Ax2 = 0.0;
        Double error = tol + 1;
        int i = 0;
        iteraciones.add(String.valueOf(i));
        xmList.add(String.valueOf(X3));
        error = tol + 1;
        ErrorList.add(String.valueOf("---"));
        while(error > tol && error2 > tol && i < res){
            X1 = Biseccion(f,tol);
            X2 = Biseccion(f,tol);
            X3 = Biseccion(f,tol);
            Ax2 = Ax(X1,X2,X3);
            error = Math.abs(Ax2 - Ax1);
            Ax1 = Ax2;
            i++;
            iteraciones.add(String.valueOf(i));
            xmList.add(String.valueOf(X3));
            ErrorList.add(String.valueOf(error));
        }
        if(error < tol){
            i++;
            String resu = String.valueOf(X3+" is an aproximation");
            aitkenresu.setText(resu);
        }else if (error2 < tol){
            i++;
            String resu = String.valueOf(X3+" is an aproximation");
            aitkenresu.setText(resu);
        }else{
            String resu = String.valueOf("No results");
            aitkenresu.setText(resu);
        }
        }catch(Exception e){
            alertDialog.show();
        }
    }
    private  Double Ax(Double X1,Double X2, Double X3) {
        return X1 - (((X2 + X3) * (X2 + X3)) / (X1 + X3 - (2 * X2)));
    }
}
//("x","-5","8","100","0.000001")



