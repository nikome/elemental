package com.androidplot.demos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class CuadraticoActivity extends Activity {
    private EditText points;
    private ArrayList<Double> puntosListx = new ArrayList();
    private ArrayList<Double> puntosListy = new ArrayList();
    private ArrayList<String> pResultados = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadratico);
        points = (EditText) findViewById(R.id.points);
    }

    public void ingresoPointCuadratico(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(CuadraticoActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutCuadratico);
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
    public void CalculateCuadratico(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(CuadraticoActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutCuadratico);
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
        trazadorCuadratico(x, y);
    } catch (Exception e) {
        alertDialog.show();
    }
    }

    public double [][] proccess_params(double[] x, double[] y){
        int n = x.length;
        double [][] puntos = new double [n][2];

        for(int i = 0; i < n; ++i){
            puntos[i][0] = x[i];
            puntos[i][1] = y[i];
        }

        return puntos;
    }

    public  double [] trazadorCuadratico(double[] x, double[] y){
        pResultados.clear();
        double [][] puntos = proccess_params(x, y);

        int n = puntos.length - 1;
        double matrix [][] = new double [n*3][n*3];
        double vector_ind [] = new double [n*3];

        int j = 0;
        int k = 0;
        for(int i = 0; i < n*2; i=i+2){
            System.out.println(puntos[k+1][0]);
            matrix[i][j+0] = Math.pow(puntos[k][0],2);
            matrix[i][j+1] = puntos[k][0];
            matrix[i][j+2] = 1;

            matrix[i+1][j+0] = Math.pow(puntos[k+1][0],2);
            matrix[i+1][j+1] = puntos[k+1][0];
            matrix[i+1][j+2] = 1;

            j += 3;
            k += 1;
        }

        j = 1;
        k = 0;
        for(int i = n*2; i < n*3-1; ++i){
            matrix[i][k + 0] = 2 * puntos[j][0];
            matrix[i][k + 1] = 1;

            matrix[i][k + 2+1] = - 2 * puntos[j][0];
            matrix[i][k + 3+1] = - 1;
            j += 1;
            k += 3;
        }
        matrix[n*3-1][0] = 1;

        vector_ind[0] = puntos[0][1];
        j = 1;
        System.out.println();
        for(int i = 1; i < n; ++i){
            vector_ind[j] = puntos[i][1];
            vector_ind[j+1] = puntos[i][1];
            j += 2;
        }
        GaussTotalApoyo gauss = new GaussTotalApoyo(matrix.length);

        System.out.println("independiente: ");
        double [] r = gauss.sustitucionRegresiva(matrix,vector_ind);
        System.out.println("r:");
        for(int i = 0; i < r.length; ++i){
            System.out.print(r[i]+ ", ");
        }
        System.out.println("solucion:");

        generate(r,puntos.length-1, gauss.marcas);
        puntosListy.clear();
        puntosListx.clear();
        return r;
    }
    public  void generate(double [] coe, int n, int [] marcas){
        for(int i = 0; i < n*3; i=i+3){
            pResultados.add(coe[buscar(marcas,i+1)]+"x^2+("+coe[buscar(marcas,i+2)]+")x+("+coe[buscar(marcas,i+3)]+")");
        }

        Intent intent = new Intent(CuadraticoActivity.this, SplineTableActivity.class);
        intent.putExtra("listap", pResultados);
        startActivity(intent);
    }

    public int buscar(int [] marcas, int r){
        for(int i = 0; i < marcas.length; i++){
            if(marcas[i] == r){
                return i;
            }
        }
        return 0;
    }


}
