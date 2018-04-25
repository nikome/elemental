package com.androidplot.demos;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Console;

public class matrix2 extends Activity {
    private EditText Fila1_Columna1;
    private EditText Fila1_Columna2;
    private EditText Fila1_Columna3;
    private EditText Fila2_Columna1;
    private EditText Fila2_Columna2;
    private EditText Fila2_Columna3;
    private EditText Fila3_Columna1;
    private EditText Fila3_Columna2;
    private EditText Fila3_Columna3;
    private TextView Sol_fila1_columna1;
    private TableLayout MatrixA;
    private TableLayout VectorB;
    private TableLayout VectorX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix2);
    }

    public void Gauus(View view){
        String respuesta="";
        MatrixA = (TableLayout) findViewById(R.id.MatrixAC);
        VectorB = (TableLayout) findViewById(R.id.VectorB);
        int n = MatrixA.getChildCount();
        double [][] A = new double [n][n];
        double [] B= new double [n];
        for(int i=0;i<MatrixA.getChildCount();i++){
            TableRow row = (TableRow) MatrixA.getChildAt(i);
            TableRow rowb = (TableRow) VectorB.getChildAt(i);
            for(int x = 0;x<row.getChildCount();x++){
                EditText f = (EditText) row.getChildAt(x);
                A[i][x] = Double.valueOf(f.getText().toString());
            }
            EditText s = (EditText) rowb.getChildAt(i);
            B[i] = Double.valueOf(s.getText().toString());
        }
        double [] x = new double[n];
        x = sustitucionRegresiva(A,B,n);
        VectorX = (TableLayout) findViewById(R.id.VectorX);

    }

    private double [] sustitucionRegresiva(double [][] A, double [] b, int n){
        double [][] Ab = escalonar(A,b,n);
        double [] x = new double [n];

        for(int j = 0; j < n-1; ++j){
            x[j] = 1;
        }
        x[n-1] = Ab[n-1][n] / Ab[n-1][n-1];
        for(int i = n-1; i >= 0; --i){
            double sumatoria = 0;
            for(int p = i+1; p < n; ++p){
                sumatoria += Ab[i][p] * x[p];
            }
            x[i] = (Ab[i][n] - sumatoria) / Ab[i][i];
        }
        return x;
    }

    private double [][]escalonar(double [][] A, double [] b, int n){
        double [][] Ab = new double [n][n];
        double multi = 0;
        Ab = aumentar(A,b,n);
        for(int i = 0; i < n-1; ++i){
            if(Ab[i][i] == 0){
                Ab = intercambio(Ab,i,n);
            }
        }
        for(int k = 0; k < n-1; ++k){
            for(int i = k+1; i < n; ++i){
                multi = Ab[i][k] / Ab[k][k];
                for(int j = k; j < n+1; ++j){
                    Ab[i][j] -= multi * Ab[k][j];
                }
            }
        }
        return Ab;
    }

    private double [][] intercambio(double[][] Ab, int j,int n){
        for(int i = j+1; i < n; ++i){
            if(Ab[i][j] != 0){
                return cambio(Ab,i,j,n);
            }
        }
        return Ab;
    }

    private double [][] cambio(double[][] Ab, int i, int j,int n){
        double acum [] = new double [n+1];
        for(int k = 0; k < n+1; ++k){
            acum[k] = Ab[j][k];
            Ab[j][k] = Ab[i][k];
            Ab[i][k] = acum[k];
        }
        return Ab;
    }

    private double [][] aumentar(double [][] A, double [] b, int n){
        double [][] Ab = new double [n][n+1];
        for(int i = 0; i < n; ++i){
            Ab[i][n] = b[i];
            for(int j = 0; j < n; ++j){
                Ab[i][j] = A[i][j];
            }
        }
        return Ab;
    }
}
