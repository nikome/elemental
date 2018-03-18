package com.androidplot.demos;

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
    ArrayList<String> iteracionesList = new ArrayList();
    ArrayList<String> xnList = new ArrayList();
    ArrayList<String> fxList = new ArrayList();
    ArrayList<String> ErrorList = new ArrayList();
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
        Button metodoP = (Button) findViewById(R.id.showTable);
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
    }
    //public static String SecanteMetodo(String f,String tol,String xo, String x1,String niters){
    public void Calcular(View view){
            iteracionesList.clear();
            xnList.clear();
            ErrorList.clear();
            fxList.clear();
            String f=funcion.getText().toString();
            String tol=Tolerancia.getText().toString();
            String niters=Iteraciones.getText().toString();
            String xo=X0.getText().toString();
            String x1=X1.getText().toString();

        com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);
        expression.setPrecision(16);
        com.androidplot.demos.com.udojava.evalex.Expression tole = new com.androidplot.demos.com.udojava.evalex.Expression(tol);
        int niter = Integer.parseInt(niters);
        expression.setPrecision(15);
        Double tolerancia = tole.eval().doubleValue();
        Double fx = 0.0;
        BigDecimal d;
        Double fx1 = 0.0;
        Double error = 0.0;
        int cont = 0;
        Double x2=0.0;
        Double x1a = 0.0;
        Double xoa = 0.0;
        Double den = -1.0;
        expression.setVariable("x", xo);
        d = expression.eval();
        fx = d.doubleValue();
        if (fx == 0){
            String resu = String.valueOf(xo+" is a root");
            Resultado.setText(resu);
        }else{
            expression.setVariable("x",x1);
            d = expression.eval();
            fx1 = d.doubleValue();
            error = tolerancia + 1;
            den = fx1-fx;
            iteracionesList.add(String.valueOf(cont));
            xnList.add(String.valueOf(xo));
            fxList.add(String.valueOf(fx1));
            ErrorList.add( "---");
            while((error > tolerancia) && (fx1 != 0)&& (den !=0) && cont < niter){
                x1a = Double.parseDouble(x1);
                xoa = Double.parseDouble(xo);
                x2 = x1a - fx1*(x1a-xoa)/den;
                error = Math.abs(x2-x1a);
                xo = x1;
                fx = fx1;
                x1 = Double.toString(x2);
                expression.setVariable("x",x1);
                d = expression.eval();
                fx1 = d.doubleValue();
                den = fx1 - fx;
                cont++;
                iteracionesList.add(String.valueOf(cont));
                xnList.add(String.valueOf(x1));
                fxList.add(String.valueOf(fx1));
                ErrorList.add(String.valueOf(error));
            }
            if(fx1 == 0){
                String resu = String.valueOf(x1+" is a root");
                Resultado.setText(resu);
            }else if(error < tolerancia){
                String resu = String.valueOf(x1+" is an aproximation");
                Resultado.setText(resu);
            }else if( den == 0){
                String resu = String.valueOf("A possible multiple root");
                Resultado.setText(resu);
            }else{
                String resu = String.valueOf("No results");
                Resultado.setText(resu);
            }

        }

    }


}
