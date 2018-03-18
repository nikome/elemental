package com.androidplot.demos;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MetodoIncrementalTablaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_incremental_tabla);

        ArrayList<String> iteracionesinc= getIntent().getExtras().getStringArrayList("iteracionesinc");
        ArrayList<String> xn= getIntent().getExtras().getStringArrayList("xnListinc");
        ArrayList<String> fxn= getIntent().getExtras().getStringArrayList("fxlistinc");

        initinc(iteracionesinc,xn,fxn);
    }
    public void initinc(ArrayList<String> iter,ArrayList<String> xn,ArrayList<String> fxn){
        TableLayout table = (TableLayout)findViewById(R.id.TablelayoutIncremental);
        if (table.getChildCount()>0){
            table.removeAllViews();
        }else{

            for (int i = 0; i < iter.size(); i++) {
                TableRow row = new TableRow(this);
                TextView view1 = new TextView(this);
                TextView view2 = new TextView(this);
                TextView view3 = new TextView(this);
                if(i==0){

                    view1.setText("Iterations");
                    view2.setText("Xn");
                    view3.setText("    "+"Fxn");
                    view1.setTextColor(Color.BLACK);
                    view2.setTextColor(Color.BLACK);
                    view3.setTextColor(Color.BLACK);

                    row.addView(view1);
                    row.addView(view2);
                    row.addView(view3);

                    table.addView(row, i);
                }else{

                    view1.setText(iter.get(i-1));
                    view1.setTextColor(Color.BLACK);
                    view2.setText("    "+xn.get(i-1));
                    view2.setTextColor(Color.BLACK);
                    view3.setText("    "+fxn.get(i-1));
                    view3.setTextColor(Color.BLACK);
                    row.addView(view1);
                    row.addView(view2);
                    row.addView(view3);
                    table.addView(row, i);
                }

            }
        }
    }

}
