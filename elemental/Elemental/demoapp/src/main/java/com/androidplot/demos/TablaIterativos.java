package com.androidplot.demos;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TablaIterativos extends Activity{
    private ArrayList<String> iteracioneslist = new ArrayList();
    private ArrayList<String> ErrorList = new ArrayList();
    private String[][] matrizXsolucion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_biseccion);
        Bundle extras = getIntent().getExtras();
        matrizXsolucion = (String[][]) extras.getSerializable("MatrizX");
        iteracioneslist = getIntent().getExtras().getStringArrayList("iteraciones");
        ErrorList = getIntent().getExtras().getStringArrayList("Error");
        int n = matrizXsolucion[0].length;
        init(iteracioneslist,ErrorList,matrizXsolucion,n);
    }
    public void init(ArrayList<String> iteraciones, ArrayList<String> error, String [][] ValoresX,int n) {
        TableLayout table = (TableLayout) findViewById(R.id.TablelayoutBisection);
            String nameTittle = "";
            int numIterations = iteraciones.size();
            int contX = 0;
            String x = "  x";
            TableRow rowTittles = new TableRow(this);
            rowTittles.getBackground();
            TextView viewIter = new TextView(this);
            viewIter.setText("iteration");
            rowTittles.addView(viewIter);
            for (int j = 0; j < n; j++) {
                nameTittle = x + String.valueOf(contX);
                TextView nameTittleE = new TextView(this);
                nameTittleE.setText("      " + nameTittle);
                rowTittles.addView(nameTittleE);
                contX = contX + 1;
            }
            TextView errorTittle = new TextView(this);
            errorTittle.setText("    Error");
            rowTittles.addView(errorTittle);
            table.addView(rowTittles, 0);
            TableRow row = new TableRow(this);
            TextView view = new TextView(this);
            TextView view2 = new TextView(this);

            for (int i = 0; i < iteracioneslist.size(); i++) {
                view.setText("      " + String.valueOf(iteracioneslist.get(i)));
                row.addView(view);
                for (int j = 0; j < ValoresX[0].length; j++) {
                    view = new TextView(this);
                    view.setText("      " + ValoresX[i][j]);
                    row.addView(view);
                }
                view2.setText("     " + error.get(i));
                row.addView(view2);
                table.addView(row);
                view2 = new TextView(this);
                view = new TextView(this);
                row = new TableRow(this);
            }

        }
    }


