package com.androidplot.demos;

import android.graphics.drawable.Drawable;
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
    private TableLayout MatrixA;
    private TableLayout VectorB;
    private TableLayout VectorX;
    public int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix2);
    }

    public void Gauus(View view){
        MatrixA =  findViewById(R.id.MatrixAC);
        VectorB =  findViewById(R.id.VectorB);
        n = MatrixA.getChildCount();
        double [][] A = new double [n][n];
        double [] b = new double [n];
        double [] resx = new double[n];
        Log.d("TAMAÑO: ",String.valueOf(n));
        for(int i=0;i<MatrixA.getChildCount();++i){
            TableRow row = (TableRow) MatrixA.getChildAt(i);
            for(int x = 0;x<row.getChildCount();++x){
                EditText f = (EditText) row.getChildAt(x);
                A[i][x] = Double.valueOf(f.getText().toString());
            }
        }
        for(int i=0;i<n;i++) {
            TableRow row = (TableRow) VectorB.getChildAt(i);
            EditText f = (EditText) row.getChildAt(0);
            b[i] = Double.valueOf(f.getText().toString());
            Log.d("PRUEBA: "+i+"  ", f.getText().toString());
        }
        resx = sustitucionRegresiva(A,b);
        VectorX = findViewById(R.id.VectorX);
        for(int i=0;i<n;i++) {
            TableRow row = (TableRow) VectorX.getChildAt(i);
            TextView f = (TextView) row.getChildAt(0);
            f.setText(String.valueOf(resx[i]));
        }
        


    }

    private double [] sustitucionRegresiva(double [][] A, double [] b){
        double [][] Ab = escalonar(A,b);
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

    private double [][]escalonar(double [][] A, double [] b){
        double [][] Ab = new double [n][n];
        double multi = 0;
        Ab = aumentar(A,b);
        for(int i = 0; i < n-1; ++i){
            if(Ab[i][i] == 0){
                Ab = intercambio(Ab,i);
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

    private double [][] intercambio(double[][] Ab, int j){
        for(int i = j+1; i < n; ++i){
            if(Ab[i][j] != 0){
                return cambio(Ab,i,j);
            }
        }
        return Ab;
    }

    private double [][] cambio(double[][] Ab, int i, int j){
        double acum [] = new double [n+1];
        for(int k = 0; k < n+1; ++k){
            acum[k] = Ab[j][k];
            Ab[j][k] = Ab[i][k];
            Ab[i][k] = acum[k];
        }
        return Ab;
    }
    private double [][] aumentar(double [][] A, double [] b){
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
