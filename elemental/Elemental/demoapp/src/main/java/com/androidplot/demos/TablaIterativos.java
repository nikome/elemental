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
        //init();
    }
   /* public void init(String [] iteraciones, String [] error, String [][] ValoresX,int n){
        TableLayout table = (TableLayout)findViewById(R.id.Table1);
        String nameTittle ="";
        int numIterations= iteraciones.length;
        int contX=0;
        String x="  x";
        TableRow rowTittles = new TableRow(this);
        rowTittles.getBackground();
        TextView viewIter = new TextView (this);
        viewIter.setText("iteration");
        rowTittles.addView(viewIter);
        for (int j=0;j<n;j++){
            nameTittle = x + String.valueOf(contX);
            TextView nameTittleE= new TextView(this);
            nameTittleE.setText("      "+nameTittle);
            rowTittles.addView(nameTittleE);
            contX=contX+1;
        }
        TextView errorTittle= new TextView(this);
        errorTittle.setText("    Error");
        rowTittles.addView(errorTittle);
        table.addView(rowTittles,0);

        for(int i=1;i<numIterations;i++){
            TableRow row = new TableRow(this);
            row.getBackground();
            TextView view = new TextView (this);
            TextView view2 = new TextView (this);
            view.setText("      "+String.valueOf(i-1));
            row.addView(view);
            for(int k=0; k<ValoresX.length;k++){
                TextView temp = new TextView(this);
                temp.setText("      "+ValoresX[i][k]);
                row.addView(temp);
            }
            view2.setText("     "+error[i]);
            row.addView(view2);
            table.addView(row,i);
        }
    }
    */
}
