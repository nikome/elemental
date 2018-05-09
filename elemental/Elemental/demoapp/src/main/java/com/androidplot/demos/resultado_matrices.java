package com.androidplot.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class resultado_matrices extends Activity {
    private TableLayout MatrixA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_matrices);
        MatrixA =  findViewById(R.id.TableResultado);
        TableRow row= new TableRow(this);
        TextView edit= new TextView(this);
        for(int i = 0;i<3;i++){
            for(int j=0;j<3;j++) {
                edit.setTextColor(Color.BLACK);
                edit.setText("0");
                edit.setTextSize(100);
                row.addView(edit);
                edit= new TextView(this);
            }
            MatrixA.addView(row);
            row= new TableRow(this);
        }
    }

}
