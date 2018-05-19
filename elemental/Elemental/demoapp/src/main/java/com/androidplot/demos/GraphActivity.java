package com.androidplot.demos;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GraphActivity extends Activity {
    private LineGraphSeries<DataPoint> series;
    private GraphView graph;
    private EditText limitA;
    private EditText limitB;
    private EditText funcion;
    private Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        limitA=(EditText) findViewById(R.id.LimitGA);
        limitB=(EditText) findViewById(R.id.LimitGB);
        myDialog = new Dialog(this);
        funcion=(EditText)findViewById(R.id.funcion);
        funcion.setText(getIntent().getExtras().getString("funcion"));
        Button fxPlot = (Button) findViewById(R.id.Graficar);
        fxPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(GraphActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("There is an error in the written variables, Try again please");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                try {
                String lima = limitA.getText().toString();
                String limb = limitB.getText().toString();
                String f = funcion.getText().toString();
                double x=0,y=0;
                x=Integer.parseInt(lima);
                graph=(GraphView) findViewById(R.id.Grafica);
                series = new LineGraphSeries<DataPoint>();
                while(x<Integer.parseInt(limb)){
                    x=x+0.01;
                    com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);
                    expression.setVariable("x",x+"");
                    y=expression.eval().doubleValue();
                    series.appendData(new DataPoint(x,y),true,10000);
                }
               graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(Integer.parseInt(lima));
                graph.getViewport().setMaxY(Integer.parseInt(lima));

                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(Integer.parseInt(lima));
                graph.getViewport().setMaxX(Integer.parseInt(lima));

                // enable scaling and scrolling
                graph.getViewport().setScalable(true);
                graph.getViewport().setScalableY(true);

                graph.addSeries(series);
                }catch(Exception e){
                    alertDialog.show();
                }
            }
        });
        funcion=(EditText)findViewById(R.id.funcion);
        Button borrar = (Button) findViewById(R.id.borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graph.removeAllSeries();
            }
            });


}
    public void ShowPopupgraficar(View v){
        TextView txtClose;
        myDialog.setContentView(R.layout.graficadora_ayuda);
        txtClose=(TextView) myDialog.findViewById(R.id.close);
        ImageView image = (ImageView) myDialog.findViewById(R.id.funcion);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}
