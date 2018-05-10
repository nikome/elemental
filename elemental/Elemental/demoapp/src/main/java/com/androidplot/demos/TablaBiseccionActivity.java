package com.androidplot.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.androidplot.xy.PanZoom;

import java.security.KeyStore;
import java.util.ArrayList;

public class TablaBiseccionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_biseccion);
        //biseccion y reglafalsa
        ArrayList<String> iteraciones= getIntent().getExtras().getStringArrayList("iteraciones");
        ArrayList<String> xm= getIntent().getExtras().getStringArrayList("xmList");
        ArrayList<String> Error= getIntent().getExtras().getStringArrayList("ErrorList");
        ArrayList<String> fxm= getIntent().getExtras().getStringArrayList("fxmList");

            init(iteraciones, xm, Error, fxm);
    }

    public void init(ArrayList<String> iter,ArrayList<String> xm,ArrayList<String> Error,ArrayList<String> fxm){
        TableLayout table = (TableLayout)findViewById(R.id.TablelayoutBisection);
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
                        view2.setText("    "+"Xm");
                        view3.setText("    "+"Error");
                        view4.setText("    "+"Fxm");
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
                        view2.setText("    "+xm.get(i-1));
                        view2.setTextColor(Color.BLACK);
                        view3.setText("    "+Error.get(i-1));
                        view3.setTextColor(Color.BLACK);
                        view4.setText("    "+fxm.get(i-1));
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


