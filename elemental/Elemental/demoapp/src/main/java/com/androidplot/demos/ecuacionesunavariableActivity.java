package com.androidplot.demos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class ecuacionesunavariableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecuacionesunavariable);

        Button metodobiseccion = (Button) findViewById(R.id.Biseccion);
        metodobiseccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ecuacionesunavariableActivity.this, metodobiseccionActivity.class));
            }
        });

        Button reglafalsa = (Button) findViewById(R.id.ReglaFalsa);
        reglafalsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ecuacionesunavariableActivity.this, ReglaFalsaActivity.class));
            }
        });

        Button busquedasincrementales = (Button) findViewById(R.id.Busquedas);
        busquedasincrementales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ecuacionesunavariableActivity.this,BusquedasIncrementalesActivity.class));
            }
        });
        Button puntofijo = (Button) findViewById(R.id.Puntofijo);
        puntofijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ecuacionesunavariableActivity.this,PuntofijoActivity.class));
            }
        });
        Button Newton = (Button) findViewById(R.id.Newton);
        Newton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ecuacionesunavariableActivity.this,NewtonActivity.class));
            }
        });
        Button secante = (Button) findViewById(R.id.Secante);
        secante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ecuacionesunavariableActivity.this,SecantMethodActivity.class));
            }
        });
        Button Raicesmultiples = (Button) findViewById(R.id.RaicesMultiples);
        Raicesmultiples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ecuacionesunavariableActivity.this,MultipleRootActivity.class));
            }
        });
    }

}
