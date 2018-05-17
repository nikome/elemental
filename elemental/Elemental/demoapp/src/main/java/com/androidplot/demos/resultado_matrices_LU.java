package com.androidplot.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class resultado_matrices_LU extends Activity {
    private TableLayout MatrixA;
    private TableLayout LU;
    private double [][] L;
    private double [][] U;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_matrices);
        Bundle extras = getIntent().getExtras();
        L =(double[][]) extras.getSerializable("ResultadoL");
        U= (double[][]) extras.getSerializable("U");
        MatrixA =  findViewById(R.id.TableResultado);
        TableRow row= new TableRow(this);
        TextView edit= new TextView(this);
        edit = new TextView(this);
        row = new TableRow(this);
        edit.setTextSize(75);
        edit.setText("L");
        row.addView(edit);
        MatrixA.addView(row);
        edit = new TextView(this);
        row = new TableRow(this);
        for(int i = 0;i<L.length;i++){
            for(int j=0;j<L[0].length;j++) {
                edit.setTextColor(Color.BLACK);
                edit.setText(String.valueOf(L[i][j])+"|");
                edit.setTextSize(15);
                row.addView(edit);
                edit= new TextView(this);
            }
            MatrixA.addView(row);
            row= new TableRow(this);
        }
        edit = new TextView(this);
        row = new TableRow(this);
        edit.setTextSize(75);
        edit.setText("U");
        row.addView(edit);
        MatrixA.addView(row);
        edit = new TextView(this);
        row = new TableRow(this);
        for(int i = 0;i<U.length;i++){
            for(int j=0;j<U[0].length;j++) {
                edit.setTextColor(Color.BLACK);
                edit.setText(String.valueOf(U[i][j])+"|");
                edit.setTextSize(15);
                row.addView(edit);
                edit= new TextView(this);
            }
            MatrixA.addView(row);
            row= new TableRow(this);
        }


    }

}
