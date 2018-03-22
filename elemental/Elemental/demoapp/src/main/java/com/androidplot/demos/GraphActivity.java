package com.androidplot.demos;
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

public class GraphActivity extends Activity {
    LineGraphSeries<DataPoint> series;
    private String var="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        var = getIntent().getExtras().getString("funcion");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        double x,y;
        x=-100.0;
        GraphView graph=(GraphView) findViewById(R.id.Grafica);
        series = new LineGraphSeries<DataPoint>();
        for (int i=0;i<10000 ;i++){
            x=x+0.1;
            com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(var);
            expression.setVariable("x",x+"");
            y=expression.eval().doubleValue();
            series.appendData(new DataPoint(x,y),true,10000);
        }
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(series);
    }
    }

