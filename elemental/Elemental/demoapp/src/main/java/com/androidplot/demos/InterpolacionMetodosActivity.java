package com.androidplot.demos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class InterpolacionMetodosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolacion_metodos);

        Button Newton = (Button) findViewById(R.id.NewtonDivision);
        Newton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InterpolacionMetodosActivity.this, NewtonDividido2Activity.class));
            }
        });
        Button lagrange = (Button) findViewById(R.id.Lagrange);
        lagrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InterpolacionMetodosActivity.this, LagrangeActivity.class));
            }
        });

        Button Simple = (Button) findViewById(R.id.Simple);
        Simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InterpolacionMetodosActivity.this, TrazadorSimpleActivity.class));

            }
        });

        Button cubico = (Button) findViewById(R.id.Cubico);
        cubico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InterpolacionMetodosActivity.this, CubicoActivity.class));

            }
        });
    }


}