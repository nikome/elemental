package com.androidplot.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class BewtonDivididoTableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewton_dividido_table);
        ArrayList<String> pCof= getIntent().getExtras().getStringArrayList("listap");
        String res= getIntent().getExtras().getString("resultado");
        ArrayList<String> matrizResult=getIntent().getExtras().getStringArrayList("matriz");
        tabla(pCof,res,matrizResult);
    }

    public void tabla(ArrayList<String> pCof,String result,ArrayList<String> matrizResult) {
        TableLayout table = findViewById(R.id.TableLayoutNewtonDiv2);
        TableLayout table2 = findViewById(R.id.TableLayoutNewtonDiv);
        TableRow rowresmatriz = new TableRow(this);
        for(int i=0;i<matrizResult.size();i++){
            TextView viewresmatriz = new TextView(this);
            viewresmatriz.setText(matrizResult.get(i));
            viewresmatriz.setTextColor(Color.BLACK);
            rowresmatriz.addView(viewresmatriz);
        }
         table.addView(rowresmatriz);
        for (int i = 0; i < pCof.size() + 1; i++) {
            TableRow row = new TableRow(this);
            TextView view1 = new TextView(this);
            if (i == 0) {

                view1.setText("    " + "Answer");
                view1.setTextColor(Color.BLACK);
                row.addView(view1);
                table2.addView(row);
            } else {

                view1.setText(pCof.get(i - 1));
                view1.setTextColor(Color.BLACK);
                row.addView(view1);
                table2.addView(row);
            }

        }
        TableRow rowres = new TableRow(this);
        TextView viewres = new TextView(this);
        viewres.setText("p(x)="+result);
        viewres.setTextColor(Color.BLACK);
        rowres.addView(viewres);
        table2.addView(rowres);

 }


}

