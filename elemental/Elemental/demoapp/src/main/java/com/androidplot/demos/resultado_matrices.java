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

public class resultado_matrices extends Activity {
    private TableLayout MatrixA;
    private double [][] Ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_matrices);
        Bundle extras = getIntent().getExtras();
        Ab= (double[][]) extras.getSerializable("ResultadoAB");
        MatrixA =  findViewById(R.id.TableResultado);
        TableRow row= new TableRow(this);
        TextView edit= new TextView(this);
        for(int i = 0;i<Ab.length;i++){
            for(int j=0;j<Ab[0].length;j++) {
                edit.setTextColor(Color.BLACK);
                edit.setText(String.valueOf(Ab[i][j])+"|");
                edit.setTextSize(15);
                row.addView(edit);
                edit= new TextView(this);
            }
            MatrixA.addView(row);
            row= new TableRow(this);
        }
    }

}
