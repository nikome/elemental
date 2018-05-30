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

public class CubicoActivity extends Activity {
    private EditText points;
    private ArrayList<Double> puntosListx = new ArrayList();
    private ArrayList<Double> puntosListy = new ArrayList();
    private ArrayList<String> pResultados = new ArrayList();
    private Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubico);
        points = (EditText) findViewById(R.id.points);
        myDialog = new Dialog(this);
    }
    public void ShowPopupCubico(View v) {
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
    public void ingresoPointCubico(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(CubicoActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutCubico);
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
    public void CalculateCubico(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(CubicoActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutCubico);
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
        trazadorCubico(x, y);
        } catch (Exception e) {
            alertDialog.show();
        }
    }

    public void trazadorCubico(double [] xi,double []  yi){
        pResultados.clear();
        int n = xi.length;

        double [] h = new double [n-1];
        for (int j=0;j<n-1;j++){
            h[j]= xi[j+1]-xi[j];
        }
        double [][] A = new double [n-2][n-2];
        double [] B = new double [n-2];
        double [] S = new double [n];
        A[0][0] = 2*(h[0]+h[1]);
        A[0][1] = h[1];
        B[0] = 6*((yi[2]-yi[1])/h[1]-  (yi[1]-yi[0])/h[0]);
        for (int i=1;i<n-3;i++){
            A[i][i-1] = h[i];
            A[i][i] = 2*(h[i]+h[i+1]);
            A[i][i+1] = h[i+1];
            B[i]=6*((yi[2]-yi[1])/h[1] - (yi[1]-yi[0])/h[0]);
        }
        A[n-3][n-4] = h[n-3];
        A[n-3][n-3] = 2*(h[n-3]+h[n-2]);
        B[n-3]=6*((yi[n-1]-yi[n-2])/h[n-2] - (yi[n-2]-yi[n-3])/h[n-3]);
        GaussTotalApoyo gauss = new GaussTotalApoyo(A.length);
        //matrix2 gauss = new matrix2();
       // gauss.n = A.length;
        double [] r = gauss.sustitucionRegresiva(A,B);
        for (int j=1;j<n-1;j++){
            S[j]=r[j-1];
        }
        S[0]=0;
        S[n-1]=0;

        double [] a = new double[n-1];
        double [] b = new double[n-1];
        double [] c = new double[n-1];
        double [] d = new double[n-1];

        for (int j=0;j<n-1;j++){
            a[j] = (S[j+1]-S[j])/(6*h[j]);
            b[j] = S[j]/2;
            c[j] = (yi[j+1]-yi[j])/h[j] - (2*h[j]*S[j]+h[j]*S[j+1])/6;
            d[j] = yi[j];
        }


        double [][] resultado = new double [4][n];

        for (int j=0;j<n-1;j++){

            resultado[0][j] = a[j];
            resultado[1][j] = b[j] - a[j]*3*xi[j];
            resultado[2][j] = c[j] - b[j]*2*xi[j] + a[j]*3*Math.pow(xi[j],2);
            resultado[3][j] = d[j] - c[j]*xi[j] + b[j]*Math.pow(xi[j],2) - a[j]*Math.pow(xi[j],3);
        }

        for(int i=0; i<resultado.length-1;++i){
            String res="";
            for(int j=0;j<resultado.length;++j){
                res+=resultado[j][i]+"x^"+(3-j)+",  ";
            }
            pResultados.add(res);
        }
        puntosListy.clear();
        puntosListx.clear();
        Intent intent = new Intent(CubicoActivity.this, SplineTableActivity.class);
        intent.putExtra("listap", pResultados);
        startActivity(intent);
    }

}
