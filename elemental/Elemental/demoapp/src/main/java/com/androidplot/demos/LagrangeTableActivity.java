package com.androidplot.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class LagrangeTableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagrange_table);
        ArrayList<String> pCof= getIntent().getExtras().getStringArrayList("listap");
        String res= getIntent().getExtras().getString("resultado");
        tabla(pCof,res);
    }
    public void tabla(ArrayList<String> pCof,String result) {
        TableLayout table = (TableLayout) findViewById(R.id.TableLayoutLagrange);
        for (int i = 0; i < pCof.size() + 1; i++) {
            TableRow row = new TableRow(this);
            TextView view1 = new TextView(this);
            if (i == 0) {

                view1.setText("    " + "Answer");
                view1.setTextColor(Color.BLACK);
                row.addView(view1);
                table.addView(row, i);
            } else {

                view1.setText(pCof.get(i - 1));
                view1.setTextColor(Color.BLACK);
                row.addView(view1);
                table.addView(row, i);
            }

        }
        TableRow rowres = new TableRow(this);
        TextView viewres = new TextView(this);
        viewres.setText("p(x)="+result);
        viewres.setTextColor(Color.BLACK);
        rowres.addView(viewres);
        table.addView(rowres);

    }

}