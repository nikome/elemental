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

public class TrazadorSimpleActivity extends Activity {
    private EditText points;
    private ArrayList<Double> puntosListx = new ArrayList();
    private ArrayList<Double> puntosListy = new ArrayList();
    private ArrayList<String> pResultados = new ArrayList();
    private Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazador_simple);
        points = (EditText) findViewById(R.id.points);
        myDialog = new Dialog(this);
    }
    public void ShowPopupSimple(View v) {
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
    public void ingresoPointSimple(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(TrazadorSimpleActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutSimple);
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
    public void CalculateSimple(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(TrazadorSimpleActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutSimple);
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
        trazadorSimple(x.length, x, y);
        } catch (Exception e) {
            alertDialog.show();
        }
    }


    public  void trazadorSimple(int nroPuntos, double[] xi, double[] fi){
        pResultados.clear();
        int n = xi.length;
        double [][] polinomio = new double [2][n];
        int tramo = 1;
        while ((tramo<n)){
            double m = (fi[tramo]-fi[tramo-1])/(xi[tramo]-xi[tramo-1]);
            double inicio = fi[tramo-1]-m*xi[tramo-1];
            polinomio[0][tramo-1] = m;
            polinomio[1][tramo-1] = inicio;
            tramo++;
        }
        for (int i = 1; i < n; ++i){
            pResultados.add("x = [" + xi[i-1] + "," + xi[i] + "]");
            pResultados.add(polinomio[0][i-1]+"*x" + "+(" + polinomio[1][i-1]+")");
        }
       // for (int i=0;)
        //Log.d(pResultados[i],"efef");
        puntosListy.clear();
        puntosListx.clear();
        Intent intent = new Intent(TrazadorSimpleActivity.this, SplineTableActivity.class);
        intent.putExtra("listap", pResultados);
        startActivity(intent);
    }


}
