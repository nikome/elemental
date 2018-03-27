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
import android.widget.Switch;
import android.widget.TextView;

import java.math.BigDecimal;
import java.lang.Math;
import java.util.ArrayList;
public class PuntofijoActivity extends Activity {
    private EditText funcion;
    private EditText Tolerancia;
    private EditText xini;
    private EditText Iteraciones;
    private TextView Resultado;
    private TextView stefensenresu;
    private Switch relativeAbsolute;
    private EditText functionfx;
    private ArrayList<String> iteraciones = new ArrayList();
    private ArrayList<String> xaList = new ArrayList();
    private ArrayList<String> ErrorList = new ArrayList();
    private ArrayList<String> gxList = new ArrayList();
    private  ArrayList<String> fxList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntofijo);
        funcion=(EditText)findViewById(R.id.funcion);
        functionfx=(EditText) findViewById(R.id.funcionfx);
        Tolerancia=(EditText)findViewById(R.id.Tolerancia);
        xini=(EditText)findViewById(R.id.xini);
        Iteraciones=(EditText)findViewById(R.id.Iteraciones);
        Resultado=(TextView)findViewById(R.id.Resultado);
        stefensenresu=(TextView) findViewById(R.id.StefensenResult);
        relativeAbsolute=(Switch) findViewById(R.id.switchRA);
        Button metodoP = (Button) findViewById(R.id.showTable);
        metodoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PuntofijoActivity.this, PuntoFijoTableActivity.class);
                intent.putExtra("iteraciones", iteraciones);
                intent.putExtra("xaList", xaList);
                intent.putExtra("ErrorList", ErrorList);
                intent.putExtra("gxList", gxList);
                intent.putExtra("fxList",fxList);
                startActivity(intent);

            }
        });
    }
            public void Calcular(View view) {
                iteraciones.clear();
                xaList.clear();
                ErrorList.clear();
                gxList.clear();
                fxList.clear();
                AlertDialog alertDialog = new AlertDialog.Builder(PuntofijoActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("There is an error in the written variables, Try again please");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                try {
                String funciong=funcion.getText().toString();
                String tolerancia=Tolerancia.getText().toString();
                String numIteraciones=Iteraciones.getText().toString();
                String x0=xini.getText().toString();
                String fxop = functionfx.getText().toString();

            double puntoInicial = Double.parseDouble(x0);
            com.androidplot.demos.com.udojava.evalex.Expression fx;
            if (fxop.equals("")){
                fx = new com.androidplot.demos.com.udojava.evalex.Expression("("+funciong+")"+"-x");
            }else{
                fx = new com.androidplot.demos.com.udojava.evalex.Expression(fxop);
            }
            com.androidplot.demos.com.udojava.evalex.Expression gx = new com.androidplot.demos.com.udojava.evalex.Expression(funciong);
            com.androidplot.demos.com.udojava.evalex.Expression t = new com.androidplot.demos.com.udojava.evalex.Expression(tolerancia);
            t.setPrecision(16);
            gx.setPrecision(16);
            fx.setPrecision(16);
            double tol = t.eval().doubleValue();
            double error = tol + 1;
            BigDecimal fxe, gxe;
            gx.setVariable("x",puntoInicial+"");
            fx.setVariable("x",puntoInicial+"");
            gxe = gx.eval();
            fxe = fx.eval();
            int i = 0;
            iteraciones.add(String.valueOf(i));
            xaList.add(x0);
            gxList.add(String.valueOf(gxe));
            if(fxop.equals("")){
                fxList.add("---");
            }else{
                fxList.add(String.valueOf(fxe.doubleValue()));
            }
            ErrorList.add("---");
            while((i < Integer.parseInt(numIteraciones)) && (error > tol) && (fxe.doubleValue() != 0)){
                gx.setVariable("x",puntoInicial+"");
                gxe = gx.eval();
                fx.setVariable("x",gxe.doubleValue() + "");
                fxe=fx.eval();

                if(relativeAbsolute.isChecked()) {
                    error = Math.abs(gxe.doubleValue() - puntoInicial);
                }else{
                    error = Math.abs((gxe.doubleValue() - puntoInicial)/gxe.doubleValue());
                }
                puntoInicial = gxe.doubleValue();
                i++;
                iteraciones.add(String.valueOf(i));
                xaList.add(String.valueOf(puntoInicial));
                gxList.add(String.valueOf(gxe));
                if(fxop.equals("")){
                    fxList.add(String.valueOf("---"));
                }else{
                    fxList.add(String.valueOf(fxe));
                }
                ErrorList.add(String.valueOf(error));
            }
            if(fxe.doubleValue() == 0){
                String resu = String.valueOf(puntoInicial+" is a root");
                Resultado.setText(resu);
            }
            if(error < tol){
                String resu = String.valueOf(puntoInicial+" is an aproximation");
                Resultado.setText(resu);
            }else{
                String resu = String.valueOf("No results");
                Resultado.setText(resu);
            } } catch (Exception e) {
                    alertDialog.show();
                }
        }



    public void Steffensen(View view){
       // String f, String Xo, String tolerancia, String niter
        iteraciones.clear();
        xaList.clear();
        ErrorList.clear();
        gxList.clear();
        fxList.clear();
        String f=funcion.getText().toString();
        String tolerancia=Tolerancia.getText().toString();
        String niter=Iteraciones.getText().toString();
        String Xo=xini.getText().toString();

        Double xi = Double.parseDouble(Xo);
        com.androidplot.demos.com.udojava.evalex.Expression gx = new com.androidplot.demos.com.udojava.evalex.Expression(f);
        gx.setVariable("x",Xo);
        gx.setPrecision(16);
        com.androidplot.demos.com.udojava.evalex.Expression fx = new com.androidplot.demos.com.udojava.evalex.Expression(f+"-x");
        fx.setPrecision(16);
        fx.setVariable("x",Xo);
        Double yi = gx.eval().doubleValue();
        gx.setVariable("x",Double.toString(yi));
        Double zi = gx.eval().doubleValue();
        double tempfx=fx.eval().doubleValue();
        if(fx.eval().doubleValue() == 0){
            String resu = String.valueOf(Xo+" is a root");
            Resultado.setText(resu);
        }else{
            com.androidplot.demos.com.udojava.evalex.Expression t = new com.androidplot.demos.com.udojava.evalex.Expression(tolerancia);
            t.setPrecision(16);
            Double tol = t.eval().doubleValue();
            Double error = tol + 1;
            Double xin = next(xi,yi,zi);
            int contador = 0;
            iteraciones.add(String.valueOf(contador));
            xaList.add(String.valueOf(xin));
            gxList.add(String.valueOf(zi));
            fxList.add(String.valueOf(tempfx));
            ErrorList.add("---");
            try{
            while(error > tol && contador < Integer.parseInt(niter) &&  fx.eval().doubleValue()!=0){
                fx.setVariable("x",Double.toString(xin));
                tempfx=fx.eval().doubleValue();
                gx.setVariable("x",Double.toString(xin));
                yi = gx.eval().doubleValue();
                gx.setVariable("x",Double.toString(yi));
                zi = gx.eval().doubleValue();
                Double aux = xin;
                xin = next(aux,yi,zi);
                error = Math.abs(xin-aux);
                if(relativeAbsolute.isChecked()) {
                    error = Math.abs(xin - aux);
                }else{
                    error = Math.abs((xin - aux)/xin);
                }
                contador++;
                iteraciones.add(String.valueOf(contador));
                xaList.add(String.valueOf(xin));
                gxList.add(String.valueOf(aux));
                fxList.add(String.valueOf(tempfx));
                ErrorList.add(String.valueOf(error));
            }
            }catch(com.androidplot.demos.com.udojava.evalex.Expression.ExpressionException ex){

            }
            if(fx.eval().doubleValue() == 0){
                String resu = String.valueOf(Double.toString(xin)+" is a root");
                stefensenresu.setText(resu);
            }else if( error < tol){
                String resu = String.valueOf(Double.toString(xin)+"is an aproximation");
                stefensenresu.setText(resu);
                }else{
                String resu = String.valueOf("No results");
                stefensenresu.setText(resu);
            }
        }

    }
    public static Double next(Double xi, Double yi, Double zi){
        return zi - (((zi-yi)*(zi-yi))/(zi-(2*yi)+xi));
    }
}


