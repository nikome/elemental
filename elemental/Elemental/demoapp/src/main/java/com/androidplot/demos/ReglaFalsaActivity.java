package com.androidplot.demos;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.math.BigDecimal;
        import java.lang.Math;
        import java.util.ArrayList;

public class ReglaFalsaActivity extends Activity {
    private EditText funcion;
    private EditText Tolerancia;
    private EditText Iteraciones;
    private EditText inferior;
    private EditText superior;
    private TextView Resultado;
    private ArrayList<String> iteraciones = new ArrayList();
    private ArrayList<String> xmList = new ArrayList();
    private ArrayList<String> ErrorList = new ArrayList();
    private ArrayList<String> fxmList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regla_falsa);
        funcion=(EditText)findViewById(R.id.funcion);
        Tolerancia=(EditText)findViewById(R.id.Tolerancia);
        Iteraciones=(EditText)findViewById(R.id.Iteraciones);
        inferior=(EditText)findViewById(R.id.inferior);
        superior=(EditText)findViewById(R.id.superior);
        Resultado=(TextView)findViewById(R.id.Resultado);

        Button metodoP = (Button) findViewById(R.id.showTable);
        metodoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReglaFalsaActivity.this, TablaBiseccionActivity.class);
                intent.putExtra("iteraciones", iteraciones);
                intent.putExtra("xmList", xmList);
                intent.putExtra("ErrorList", ErrorList);
                intent.putExtra("fxmList", fxmList);
                startActivity(intent);

            }
        });
    }



    public static double recta(double a, double b, double fa, double fb) {
        return a - ((fa * (b - a)) / (fb - fa));
    }
    public void Calcular(View view) {
        iteraciones.clear();
        xmList.clear();
        ErrorList.clear();
        fxmList.clear();
        String f = funcion.getText().toString();
        String tol = Tolerancia.getText().toString();
        String xi = inferior.getText().toString();
        String xs = superior.getText().toString();
        String iter=Iteraciones.getText().toString();
        AlertDialog alertDialog = new AlertDialog.Builder(ReglaFalsaActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        try {
                double fxi;
                double fxs;
                int contador;
                double tolerancia;
                tolerancia=Double.parseDouble(tol);
                String Xms;
                BigDecimal d;
                com.androidplot.demos.com.udojava.evalex.Expression expression = new com.androidplot.demos.com.udojava.evalex.Expression(f);

                expression.setPrecision(16);
                expression.setVariable("x",xi);
                d = expression.eval();
                fxi = d.doubleValue();
                expression.setVariable("x",xs);
                d = expression.eval();
                fxs = d.doubleValue();
                double Xm = 0;
                double fxm = 0;
                double Xaux = 0;
                double error = 0;
                if(fxi == 0){
                    String resu = String.valueOf(xi);
                    Resultado.setText(resu);
                }
                else if(fxs == 0){
                    String resu = String.valueOf(xs);
                    Resultado.setText(resu);
                }
                else if(fxi * fxs < 0){
                    Xm = recta(Double.parseDouble(xi),Double.parseDouble(xs),fxi,fxs);
                    Xms = Double.toString(Xm);
                    expression.setVariable("x",Xms);
                    d = expression.eval();
                    fxm = d.doubleValue();
                    contador = 1;

                    iteraciones.add(String.valueOf(contador));
                    xmList.add(String.valueOf(Xm));
                    error = tolerancia + 1;
                    fxmList.add(String.valueOf(fxm));
                    ErrorList.add(String.valueOf("---"));
                    while((error>tolerancia) && fxm != 0 && contador < Integer.parseInt(iter)){
                        if(fxi * fxm < 0){
                            xs = Double.toString(Xm);
                            fxs = fxm;
                        }
                        else{
                            xi = Double.toString(Xm);
                            fxi = fxm;
                        }
                        Xaux = Xm;
                        Xm = recta(Double.parseDouble(xi),Double.parseDouble(xs),fxi,fxs);
                        Xms = Double.toString(Xm);
                        expression.setVariable("x",Xms);
                        d = expression.eval();
                        fxm = d.doubleValue();
                        error = Math.abs(Xm-Xaux);
                        contador++;
                        iteraciones.add(String.valueOf(contador));
                        xmList.add(String.valueOf(Xm));
                        ErrorList.add(String.valueOf(error));
                        fxmList.add(String.valueOf(fxm));
                    }
                    if(fxm == 0){
                        String resu = String.valueOf(Xm);
                        Resultado.setText(resu);
                    }
                    else if(error < tolerancia){
                        String resu = String.valueOf(Xm);
                        Resultado.setText(resu+" is a aproximation");
                    }
                    else{
                        Resultado.setText("The number of iterations failed");
                    }
                }
                else {
                    Resultado.setText("The interval is inadequate");
                }
                }catch(Exception e){
            alertDialog.show();
                }
            }
        }



