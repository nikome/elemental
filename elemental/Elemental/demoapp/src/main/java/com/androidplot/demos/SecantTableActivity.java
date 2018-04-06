package com.androidplot.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class SecantTableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secant_table);
        ArrayList<String> iteraciones= getIntent().getExtras().getStringArrayList("IteracionesList");
        ArrayList<String> xn= getIntent().getExtras().getStringArrayList("xnList");
        ArrayList<String> Error= getIntent().getExtras().getStringArrayList("ErrorList");
        ArrayList<String> fx= getIntent().getExtras().getStringArrayList("fxList");

        init(iteraciones, xn, Error, fx);
    }
    public void init(ArrayList<String> iter,ArrayList<String> xn,ArrayList<String> Error,ArrayList<String> fx){
        TableLayout table = (TableLayout)findViewById(R.id.TablelayoutSecante);
        if (table.getChildCount()>0){
            table.removeAllViews();
        }else{

            for (int i = 0; i < iter.size()+1; i++) {
                TableRow row = new TableRow(this);
                TextView view1 = new TextView(this);
                TextView view2 = new TextView(this);
                TextView view3 = new TextView(this);
                TextView view4 = new TextView(this);
                if(i==0){

                    view1.setText("Iterations");
                    view2.setText("    "+"Xa");
                    view3.setText("    "+"Error");
                    view4.setText("    "+"f(x)");
                    view1.setTextColor(Color.BLACK);
                    view2.setTextColor(Color.BLACK);
                    view3.setTextColor(Color.BLACK);
                    view4.setTextColor(Color.BLACK);

                    row.addView(view1);
                    row.addView(view2);
                    row.addView(view3);
                    row.addView(view4);

                    table.addView(row, i);
                }else{

                    view1.setText(iter.get(i-1));
                    view1.setTextColor(Color.BLACK);
                    view2.setText("    "+xn.get(i-1));
                    view2.setTextColor(Color.BLACK);
                    view3.setText("    "+Error.get(i-1));
                    view3.setTextColor(Color.BLACK);
                    view4.setText("    "+fx.get(i-1));
                    view4.setTextColor(Color.BLACK);
                    row.addView(view1);
                    row.addView(view2);
                    row.addView(view3);
                    row.addView(view4);
                    table.addView(row, i);
                }

            }
        }

    }

}
