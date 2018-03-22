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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficarfuncion);


        Button fxPlot = (Button) findViewById(R.id.Graficar);
        fxPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   startActivity(new Intent(graficarfuncionActivity.this, FXPlotExampleActivity.class));
                Intent intent = new Intent(graficarfuncionActivity.this, GraphActivity.class);
                Funcion=(EditText)findViewById(R.id.Funcion);
                String f = Funcion.getText().toString();
                intent.putExtra("funcion", f);
                startActivity(intent);
            }
        });


    }



}
