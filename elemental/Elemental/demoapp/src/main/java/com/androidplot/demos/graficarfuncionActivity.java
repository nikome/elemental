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
    public EditText Funcion;
    public EditText LimitA;
    public  EditText LimitB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficarfuncion);


        Button fxPlot = (Button) findViewById(R.id.Graficar);
        fxPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   startActivity(new Intent(graficarfuncionActivity.this, FXPlotExampleActivity.class));
                Intent intent = new Intent(graficarfuncionActivity.this, FXPlotExampleActivity.class);
                Funcion=(EditText)findViewById(R.id.Funcion);
                LimitA=(EditText)findViewById(R.id.LimitA);
                LimitB=(EditText)findViewById(R.id.LimitB);
                String A=LimitA.getText().toString();
                String B=LimitB.getText().toString();
                String f = Funcion.getText().toString();
                intent.putExtra("limitA",A);
                intent.putExtra("limitB",B);
                intent.putExtra("funcion", f);
                startActivity(intent);
            }
        });


    }



}
