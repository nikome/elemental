package com.androidplot.demos;

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
    }
    public void CalculateCuadratico(View view) {
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
        //trazadorSimple(x.length, x, y);
    }

}
