package com.androidplot.demos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class LagrangeActivity extends Activity {
    private EditText points;
    private EditText valor;
    private double result;
    private String val;
    private ArrayList<Double> puntosListx = new ArrayList();
    private ArrayList<Double> puntosListy = new ArrayList();
    private ArrayList<String> pResultados = new ArrayList();
    private Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagrange);
        points = (EditText) findViewById(R.id.points);
        valor = (EditText) findViewById(R.id.ValueX);
        myDialog = new Dialog(this);
    }

    public void ingresoPointLagrange(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(LagrangeActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutLagrangePoints);
        String n = points.getText().toString();

        if (table.getChildCount() > 0) {
            table.removeAllViews();
        } else {
            for (int i = 0; i < Integer.parseInt(n); i++) {
                TableRow row = new TableRow(this);
                EditText view1 = new EditText(this);
                EditText view2 = new EditText(this);
                view1.setHint("write a number");
                view1.setTextColor(Color.BLACK);
                view1.setHintTextColor(Color.BLACK);
                view2.setHint("write a number");
                view2.setTextColor(Color.BLACK);
                view2.setHintTextColor(Color.BLACK);
                row.addView(view1);
                row.addView(view2);
                table.addView(row);
            }
        }
        } catch (Exception e) {
            alertDialog.show();
        }
    }
    public void ShowPopupLagrange(View v) {
        TextView txtClose;
        myDialog.setContentView(R.layout.ayuda_interpolacion);
        txtClose = (TextView) myDialog.findViewById(R.id.close);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void CalculateLagrange(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(LagrangeActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutLagrangePoints);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row1= (TableRow)table.getChildAt(i);
            EditText xpoint=(EditText ) row1.getChildAt(0);
            EditText ypoint=(EditText ) row1.getChildAt(1);
            String numberx=xpoint.getText().toString();
            String numbery=ypoint.getText().toString();
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
        interpolacionLagrange(x.length, Double.parseDouble(val), x, y);
    } catch (Exception e) {
        alertDialog.show();
    }
    }

    public  void interpolacionLagrange(int nroPuntos, double valor, double[] x, double[] y){
        pResultados.clear();
        result = 0;
        String pol = "P(x): ";
        for(int k = 0; k<nroPuntos;k++){
            double productoria = 1;
            String termino = "";
            for(int i = 0; i < nroPuntos ; i++){
                if(i!=k){
                    productoria = productoria * (valor-x[i])/(x[k]-x[i]);
                    termino = termino + ("[(x-"+x[i]+")/("+x[k]+"-"+x[i]+")]");
                }
            }
            if(k==0) {
                pol = "P(x): "+(y[k] > 0 ? "+" : "") + y[k] + "*" + termino ;
                pResultados.add(pol);
            }else{
                pol = (y[k] > 0 ? "+" : "") + y[k] + "*" + termino;
                pResultados.add(pol);
            }
            result += productoria * y[k];
        }
        puntosListy.clear();
        puntosListx.clear();
        val="";
        Intent intent = new Intent(LagrangeActivity.this, LagrangeTableActivity.class);
        intent.putExtra("listap", pResultados);
        intent.putExtra("resultado", String.valueOf(result));
        startActivity(intent);
    }

}