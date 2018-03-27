package com.androidplot.demos;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class GraphActivity extends Activity {
    private LineGraphSeries<DataPoint> series;
    private GraphView graph;
    private EditText funcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        funcion=(EditText)findViewById(R.id.funcion);
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

                String f = funcion.getText().toString();
                double x=0,y=0;
                x=-50.0;
                graph=(GraphView) findViewById(R.id.Grafica);
                series = new LineGraphSeries<DataPoint>();
                for (int i=0;i<1000 ;i++){
                    x=x+0.1;
                    com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);
                    expression.setVariable("x",x+"");
                    y=expression.eval().doubleValue();
                    series.appendData(new DataPoint(x,y),true,1000);
                }
                graph.getViewport().setYAxisBoundsManual(true);
                //graph.getViewport().setMinY(-150);
                //graph.getViewport().setMaxY(150);

                //graph.getViewport().setXAxisBoundsManual(true);
                //graph.getViewport().setMinX(-300);
                //graph.getViewport().setMaxX(300);

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
}

