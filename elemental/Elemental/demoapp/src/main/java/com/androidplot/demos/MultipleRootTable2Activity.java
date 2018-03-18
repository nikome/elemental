package com.androidplot.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MultipleRootTable2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_root_table2);
        ArrayList<String> iteraciones= getIntent().getExtras().getStringArrayList("iteraciones");
        ArrayList<String> xn= getIntent().getExtras().getStringArrayList("xnList");
        ArrayList<String> Error= getIntent().getExtras().getStringArrayList("ErrorList");
        ArrayList<String> fxList= getIntent().getExtras().getStringArrayList("fxList");
        ArrayList<String> fpxList= getIntent().getExtras().getStringArrayList("fpxList");
        ArrayList<String> fppxList= getIntent().getExtras().getStringArrayList("fppxList");

        init(iteraciones, xn, Error, fxList,fpxList,fppxList);
    }
    public void init(ArrayList<String> iter,ArrayList<String> xn,ArrayList<String> Error,ArrayList<String> fx,ArrayList<String> fpx,ArrayList<String> fppx){
        TableLayout table = (TableLayout)findViewById(R.id.TablelayoutMultipleRoot);
        if (table.getChildCount()>0){
            table.removeAllViews();
        }else{

            for (int i = 0; i < iter.size()+1; i++) {
                TableRow row = new TableRow(this);
                TextView view1 = new TextView(this);
                TextView view2 = new TextView(this);
                TextView view3 = new TextView(this);
                TextView view4 = new TextView(this);
                TextView view5 = new TextView(this);
                TextView view6 = new TextView(this);
                if(i==0){

                    view1.setText("Iterations");
                    view2.setText("    "+"Xn");
                    view3.setText("    "+"Error");
                    view4.setText("    "+"fx");
                    view5.setText("    "+"fpx");
                    view6.setText("    "+"fppx");
                    view1.setTextColor(Color.BLACK);
                    view2.setTextColor(Color.BLACK);
                    view3.setTextColor(Color.BLACK);
                    view4.setTextColor(Color.BLACK);
                    view5.setTextColor(Color.BLACK);
                    view6.setTextColor(Color.BLACK);

                    row.addView(view1);
                    row.addView(view2);
                    row.addView(view3);
                    row.addView(view4);
                    row.addView(view5);
                    row.addView(view6);
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
                    view5.setText("    "+fpx.get(i-1));
                    view5.setTextColor(Color.BLACK);
                    view6.setText("    "+fppx.get(i-1));
                    view6.setTextColor(Color.BLACK);
                    row.addView(view1);
                    row.addView(view2);
                    row.addView(view3);
                    row.addView(view4);
                    row.addView(view5);
                    row.addView(view6);
                    table.addView(row, i);
                }

            }
        }

    }


}
