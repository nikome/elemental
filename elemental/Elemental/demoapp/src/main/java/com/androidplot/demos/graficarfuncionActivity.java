package com.androidplot.demos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class graficarfuncionActivity extends Activity {
    private Button fxPlot;
    public EditText funcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficarfuncion);

        //LimitA=(EditText)findViewById(R.id.LimitA);
        //LimitB=(EditText)findViewById(R.id.LimitB);

        //final String n = LimitA.getText().toString();
        //final String xi = LimitB.getText().toString();

        fxPlot = (Button) findViewById(R.id.Graficar);
        fxPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   startActivity(new Intent(graficarfuncionActivity.this, FXPlotExampleActivity.class));
                Intent intent = new Intent(graficarfuncionActivity.this, FXPlotExampleActivity.class);
                funcion=(EditText)findViewById(R.id.funcion);
                String f = funcion.getText().toString();
                intent.putExtra("EXTRA_SESSION_ID", "x");
                startActivity(intent);
            }
        });


    }



}
