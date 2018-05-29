package com.androidplot.demos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Iterativos extends Activity {


    private TableLayout MatrixA;
    private TableLayout VectorB;
    private TableLayout VectorX;
    private EditText tolerancia;
    private EditText iteraciones;
    private EditText valorw;
    private ArrayList<String> iteracioneslist = new ArrayList();
    private ArrayList<String> ErrorList = new ArrayList();
    private String[][] matrizXsolucion;
    public int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        matrizXsolucion= new String[n][n];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterativos);
        Button metodobiseccion = (Button) findViewById(R.id.Tablas);
        metodobiseccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Iterativos.this, TablaIterativos.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("MatrizX",matrizXsolucion);
                intent.putExtra("MatrizX",matrizXsolucion);
                intent.putExtra("iteraciones",iteracioneslist);
                intent.putExtra("Error",ErrorList);
                startActivity(intent);
                iteracioneslist.clear();
                ErrorList.clear();
            }
        });
        a();
    }

        public void a(){

            MatrixA =  findViewById(R.id.MatrixAC);
            TableRow row= new TableRow(this);
            EditText edit= new EditText(this);
            for(int i = 0;i<3;i++){
                for(int j=0;j<3;j++) {
                    edit.setTextColor(Color.BLACK);
                    edit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    edit.setText("0");
                    edit.setTextSize(10);
                    row.addView(edit);
                    edit= new EditText(this);
                }
                MatrixA.addView(row);
                row= new TableRow(this);
            }
            MatrixA = findViewById(R.id.VectorB);
            edit= new EditText(this);
            for(int i = 0;i<3;i++){
                edit.setTextColor(Color.BLACK);
                edit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setText("0");
                edit.setTextSize(10);
                MatrixA.addView(edit);
                edit= new EditText(this);
            }
            MatrixA = findViewById(R.id.VectorXo);
            edit= new EditText(this);
            for(int i = 0;i<3;i++){
                edit.setTextColor(Color.BLACK);
                edit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setText("0");
                edit.setTextSize(10);
                MatrixA.addView(edit);
                edit= new EditText(this);
            }
            MatrixA=findViewById(R.id.VectorX);
            TextView view = new TextView(this);
            for(int i = 0;i<3;i++){
                view.setTextColor(Color.WHITE);
                view.setTextSize(10);
                MatrixA.addView(view);
                view= new TextView(this);
            }
        }
    public void addRow(View view){
        /**MatrixA = findViewById(R.id.VectorB);
         ViewGroup.LayoutParams params = MatrixA.getLayoutParams();
         params.height=MatrixA.getHeight()+130;
         MatrixA.setX(MatrixA.getX()+10);
         MatrixA.setLayoutParams(params);
         MatrixA =findViewById(R.id.VectorX);
         params = MatrixA.getLayoutParams();
         MatrixA.setX(MatrixA.getX()+10);
         params.height=MatrixA.getHeight()+130;
         MatrixA.setLayoutParams(params);
         MatrixA =  findViewById(R.id.Container);
         params = MatrixA.getLayoutParams();
         params.width=MatrixA.getWidth()+105;
         params.height=MatrixA.getHeight()+130;
         MatrixA.setLayoutParams(params);
         MatrixA =  findViewById(R.id.MatrixAC);
         params = MatrixA.getLayoutParams();
         params.width=MatrixA.getWidth()+105;
         params.height=MatrixA.getHeight()+130;
         MatrixA.setLayoutParams(params);*/
        MatrixA = findViewById(R.id.MatrixAC);
        n = MatrixA.getChildCount();
        MatrixA =  findViewById(R.id.MatrixAC);
        TableRow row= new TableRow(this);
        EditText edit= new EditText(this);
        for(int i = 0;i<n+1;i++){
            edit.setTextColor(Color.BLACK);
            edit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            edit.setText("0");
            edit.setTextSize(10);
            row.addView(edit);
            edit= new EditText(this);
        }
        MatrixA.addView(row);
        for(int x=0;x<n;x++){
            row = (TableRow) MatrixA.getChildAt(x);
            edit= new EditText(this);
            edit.setTextColor(Color.BLACK);
            edit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            edit.setText("0");
            edit.setTextSize(10);
            row.addView(edit);
        }
        MatrixA = findViewById(R.id.VectorB);
        edit= new EditText(this);
        edit.setTextColor(Color.BLACK);
        edit.setText("0");
        edit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        edit.setTextSize(10);
        MatrixA.addView(edit);
        MatrixA = findViewById(R.id.VectorXo);
        edit= new EditText(this);
        edit.setTextColor(Color.BLACK);
        edit.setText("0");
        edit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        edit.setTextSize(10);
        MatrixA.addView(edit);
        MatrixA=findViewById(R.id.VectorX);
        TextView f =new TextView(this);
        f.setTextColor(Color.WHITE);
        f.setTextSize(10);
        MatrixA.addView(f);

    }

    public void quitRow(View view){
        /**MatrixA = findViewById(R.id.VectorB);
         ViewGroup.LayoutParams params = MatrixA.getLayoutParams();
         params.height=MatrixA.getHeight()-130;
         MatrixA.setX(MatrixA.getX()-10);
         MatrixA =findViewById(R.id.VectorX);
         params = MatrixA.getLayoutParams();
         params.height=MatrixA.getHeight()-130;
         MatrixA.setX(MatrixA.getX()-10);
         MatrixA.setLayoutParams(params);
         MatrixA =  findViewById(R.id.Container);
         params = MatrixA.getLayoutParams();
         params.width=MatrixA.getWidth()-105;
         params.height=MatrixA.getHeight()-130;
         MatrixA.setLayoutParams(params);
         MatrixA =  findViewById(R.id.MatrixAC);
         params = MatrixA.getLayoutParams();
         params.width=MatrixA.getWidth()-105;
         params.height=MatrixA.getHeight()-130;
         MatrixA.setLayoutParams(params);*/
        MatrixA = findViewById(R.id.MatrixAC);
        n=MatrixA.getChildCount();
        for(int i=0;i<n;i++){
            TableRow row = (TableRow) MatrixA.getChildAt(i);
            row.removeViewAt(n-1);
        }
        MatrixA.removeViewAt(n-1);
        MatrixA = findViewById(R.id.VectorB);
        MatrixA.removeViewAt(n-1);
        MatrixA = findViewById(R.id.VectorXo);
        MatrixA.removeViewAt(n-1);
        MatrixA=findViewById(R.id.VectorX);
        MatrixA.removeViewAt(n-1);
    }

    public void JacobbiSor(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        tolerancia = findViewById(R.id.tolerancia);
        iteraciones = findViewById(R.id.iteraciones);
        valorw = findViewById(R.id.labelw);
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double X_o[] = new double[n];
        double [] resx = new double[n];
        alertDialog.setMessage("The determinate is equal to cero be careful, try again");
        A = getMatrixA();
        double det = determinante(A);
        if(det == 0){
            alertDialog.show();
        }else {
            b = getVectorB();
            X_o = getVectorXo();
            double tol = 0;
            int niter = 0;
            double w = 0;
            try {
                tol = Double.valueOf(tolerancia.getText().toString());
                niter = Integer.valueOf(iteraciones.getText().toString());
                w = Double.valueOf(valorw.getText().toString());
                Log.d("W ", String.valueOf(w));
                if (w < 0 || w > 2) {
                    alertDialog.setMessage("There is an error in the written variables W should be from 0 to 2, Try again please");
                    alertDialog.show();
                }
            } catch (Exception e) {
                alertDialog.setMessage("There is an error in the written variables, Try again please");
                alertDialog.show();
            }
            resx = jacobiRelajado(tol, niter, X_o, A, b, w);
            VectorX = findViewById(R.id.VectorX);
            n = VectorX.getChildCount();
            for (int i = 0; i < n; i++) {
                TextView f = (TextView) VectorX.getChildAt(i);
                f.setText(String.valueOf(resx[i]));
            }
            VectorX.setBackgroundColor(Color.rgb(44, 132, 30));
        }
    }

    public void GaussSeidelSor(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        tolerancia = findViewById(R.id.tolerancia);
        iteraciones = findViewById(R.id.iteraciones);
        valorw = findViewById(R.id.labelw);
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double X_o[] = new double[n];
        double [] resx = new double[n];
        alertDialog.setMessage("The determinate is equal to cero be careful, try again");
        A = getMatrixA();
        double det = determinante(A);
        if(det == 0){
            alertDialog.show();
        }else {
            b = getVectorB();
            X_o = getVectorXo();
            double tol = 0;
            int niter = 0;
            double w = 0;
            Log.d("LLEGUE", "LLEGUE");
            try {
                tol = Double.valueOf(tolerancia.getText().toString());
                niter = Integer.valueOf(iteraciones.getText().toString());
                w = Double.valueOf(valorw.getText().toString());
                if (w < 0 || w > 2) {
                    alertDialog.setMessage("There is an error in the written variables W should be from 0 to 2, Try again please");
                    alertDialog.show();
                }
            } catch (Exception e) {
                alertDialog.setMessage("There is an error in the written variables, Try again please");
                alertDialog.show();
            }
            resx = gaussSeidelRelajado(tol, niter, X_o, A, b, w);
            VectorX = findViewById(R.id.VectorX);
            n = VectorX.getChildCount();
            for (int i = 0; i < n; i++) {
                TextView f = (TextView) VectorX.getChildAt(i);
                f.setText(String.valueOf(resx[i]));
            }
            VectorX.setBackgroundColor(Color.rgb(44, 132, 30));
        }
    }

    private double[] gaussSeidelRelajado(double tol,int nitter,double [] x0,double[][] A, double [] b,double w){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        int contador = 0;
        double dispercion=tol+1;
        double[] nodio = new double[n];
        double[] x1 = new double[x0.length];
        iteracioneslist.add(String.valueOf(contador));
        ErrorList.add("----");
        matrizXsolucion=new String[nitter][x0.length];
        Log.d("LLEGUE G","LLEGUE");
        try {
            Log.d("LLEGUE G2","LLEGUE");
            while (dispercion > tol && contador < nitter) {
                x1 = calcularNuevoSeidel2(x0, A, b, w);
                for (int i = 0; i < x1.length; i++) {
                    if (String.valueOf(x1[i]) != "null") {
                        matrizXsolucion[contador][i] = String.valueOf(x1[i]);
                    }
                }
                dispercion = norma2(x0, x1);
                x0 = x1;
                contador += 1;
                iteracioneslist.add(String.valueOf(contador));
                ErrorList.add(String.valueOf(dispercion));
            }
        }catch (Exception e){
            Log.d("EXCEPTION        ",e.toString());
            alertDialog.setMessage("There is an error in the written variables, Try again please");
            alertDialog.show();
        }
        if(dispercion<tol){
            Log.d("LLEGUE G3","LLEGUE");
            return x1;
        }else{
            alertDialog.setMessage("The method fail");
            alertDialog.show();
            return nodio;
        }

    }

    private double[] calcularNuevoSeidel2(double[] x0,double[][] A,double[] b,double w){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is a cero in the diagonal of the matrix, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double[] x1 =new double[x0.length];
        for(int i=0;i<x0.length;++i){
            x1[i]=x0[i];
        }
        for(int i=0; i < x0.length;++i){
            double suma=0;
            for(int j=0;j<x0.length;++j){
                if(j != i){
                    suma += A[i][j]*x1[j];
                }
            }
            if(A[i][i]==0){
                alertDialog.show();
            }
            x1[i]=(b[i]-suma)/A[i][i];
            x1[i] = w*(x1[i])+(1-w)*(x0[i]);
        }
        return x1;
    }

    private double[] jacobiRelajado(double tol,int nitter,double [] x0,double[][] A, double [] b,double w){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        int contador = 0;
        double[] nodio= new double[n];
        double dispercion=tol+1;
        double[] x1 = new double[x0.length];
        matrizXsolucion = new String[nitter][x0.length];
        iteracioneslist.add(String.valueOf(contador));
        ErrorList.add("-----");
        try {
            while (dispercion > tol && contador < nitter) {
                x1 = calcularNuevoJacobi2(x0, A, b, w);
                for (int i = 0; i < x1.length; i++) {
                    if (String.valueOf(x1[i]) != "null") {
                        matrizXsolucion[contador][i] = String.valueOf(x1[i]);
                    }
                }
                dispercion = norma2(x0, x1);
                x0 = x1;
                contador += 1;
                iteracioneslist.add(String.valueOf(contador));
                ErrorList.add(String.valueOf(dispercion));
            }
        }catch (Exception e){
            alertDialog.setMessage("There is an error in the written variables, Try again please");
            alertDialog.show();
        }
        if(dispercion<tol){
            return x0;
        }else{
            alertDialog.setMessage("The method fail");
            alertDialog.show();
            return nodio;
        }

    }
    private double norma2(double[] x0,double[] x1){
        double suma=0;
        for(int i=0;i<x0.length;++i){
            suma += Math.pow((x1[i]-x0[i]),2);
        }
        return Math.pow(suma,0.5);
    }
    private double[] calcularNuevoJacobi2(double[] x0,double[][] A,double[] b,double w){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is a cero in the diagonal of the matrix, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double[] x1 = new double[x0.length];
        for(int i=0; i < x0.length;++i){
            double suma=0;
            for(int j=0;j<x0.length;++j){
                if(j != i){
                    suma += A[i][j]*x0[j];
                }
            }
            if(A[i][i]==0){
                alertDialog.show();
            }
            x1[i]=(b[i]-suma)/A[i][i];
            x1[i] = (w*(x1[i])+(1-w)*(x0[i]));
        }
        return x1;
    }

    public void Jacobbi(View view){
        AlertDialog mensaje = new AlertDialog.Builder(Iterativos.this).create();
        mensaje.setTitle("Alert");
        mensaje.setMessage("There is an error in the written variables, Try again please");
        mensaje.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        tolerancia = findViewById(R.id.tolerancia);
        iteraciones = findViewById(R.id.iteraciones);
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double X_o[] = new double[n];
        double [] resx = new double[n];
        mensaje.setMessage("The determinate is equal to cero be careful, try again");
        A = getMatrixA();
        double det = determinante(A);
        if(det == 0){
            mensaje.show();
        }else {
            b = getVectorB();
            X_o = getVectorXo();
            double tol = 0;
            int niter = 0;
            try {
                tol = Double.valueOf(tolerancia.getText().toString());
                niter = Integer.valueOf(iteraciones.getText().toString());
            } catch (Exception e) {
                mensaje.show();
            }
            resx = jacobi(tol, niter, X_o, A, b);
            VectorX = findViewById(R.id.VectorX);
            n = VectorX.getChildCount();
            for (int i = 0; i < n; i++) {
                TextView f = (TextView) VectorX.getChildAt(i);
                f.setText(String.valueOf(resx[i]));
            }
            VectorX.setBackgroundColor(Color.rgb(44, 132, 30));
        }
    }

    public void GaussSeidel(View view){
        AlertDialog mensaje2 = new AlertDialog.Builder(Iterativos.this).create();
        mensaje2.setTitle("Alert");
        mensaje2.setMessage("There is an error in the written variables, Try again please");
        mensaje2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        tolerancia = findViewById(R.id.tolerancia);
        iteraciones = findViewById(R.id.iteraciones);
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double X_o[] = new double[n];
        double [] resx = new double[n];
        mensaje2.setMessage("The determinate is equal to cero be careful, try again");
        A = getMatrixA();
        double det = determinante(A);
        if(det == 0){
            mensaje2.show();
        }else {
            b = getVectorB();
            X_o = getVectorXo();
            double tol = 0;
            int niter = 0;
            try {
                tol = Double.valueOf(tolerancia.getText().toString());
                niter = Integer.valueOf(iteraciones.getText().toString());
            } catch (Exception e) {
                mensaje2.show();
            }
            resx = gaussSeidel(tol, niter, X_o, A, b);
            VectorX = findViewById(R.id.VectorX);
            n = VectorX.getChildCount();
            for (int i = 0; i < n; i++) {
                TextView f = (TextView) VectorX.getChildAt(i);
                f.setText(String.valueOf(resx[i]));
            }
            VectorX.setBackgroundColor(Color.rgb(44, 132, 30));
        }
    }

    private double[] gaussSeidel(double tol,int nitter,double [] x0,double[][] A, double [] b){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        int contador = 0;
        double[] nodio = new double[n];
        double dispercion=tol+1;
        double[] x1 = new double[x0.length];
        //imprimir(contador,x0,dispercion);
        matrizXsolucion = new String[nitter][x0.length];
        iteracioneslist.add(String.valueOf(contador));
        ErrorList.add("-----");
        try {
            while (dispercion > tol && contador < nitter) {
                x1 = calcularNuevoSeidel(x0, A, b);
                for (int i = 0; i < x1.length; i++) {
                    if (String.valueOf(x1[i]) != "null") {
                        matrizXsolucion[contador][i] = String.valueOf(x1[i]);
                    }
                }
                dispercion = norma(x0, x1);
                x0 = x1;
                contador += 1;
                iteracioneslist.add(String.valueOf(contador));
                ErrorList.add(String.valueOf(dispercion));
            }
        }catch (Exception e){
            alertDialog.setMessage("There is an error in the written variables, Try again please");
            alertDialog.show();
        }
        if(dispercion<tol){
            alertDialog.setMessage("The method fail");
            alertDialog.show();
            return x1;
        }else{
            return nodio;
        }

    }

    private double[] calcularNuevoSeidel(double[] x0,double[][] A,double[] b){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is a cero in the diagonal of the matrix, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double[] x1 =new double[x0.length];
        for(int i=0;i<x0.length;++i){
            x1[i]=x0[i];
        }
        for(int i=0; i < x0.length;++i){
            double suma=0;
            for(int j=0;j<x0.length;++j){
                if(j != i){
                    suma += A[i][j]*x1[j];
                }
            }
            if(A[i][i]==0){
                alertDialog.show();
            }
            x1[i]=(b[i]-suma)/A[i][i];
        }
        return x1;
    }
    private double[] jacobi(double tol,int nitter,double [] x0,double[][] A, double [] b){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        int contador = 0;
        double[] nodio = new double[n];
        double dispercion=tol+1;
        double[] x1 = new double[x0.length];
        iteracioneslist.add(String.valueOf(contador));
        ErrorList.add("----");
        matrizXsolucion=new String[nitter][x0.length];
        try {
            while (dispercion > tol && contador < nitter) {
                x1 = calcularNuevoJacobi(x0, A, b);
                for (int i = 0; i < x1.length; i++) {
                    if (String.valueOf(x1[i]) != "null") {
                        matrizXsolucion[contador][i] = String.valueOf(x1[i]);
                    }
                }
                dispercion = norma(x0, x1);
                x0 = x1;
                contador += 1;
                iteracioneslist.add(String.valueOf(contador));
                ErrorList.add(String.valueOf(dispercion));
            }
        }catch (Exception e){
            alertDialog.setMessage("There is an error in the written variables, Try again please");
            alertDialog.show();
        }
        if(dispercion<tol){
            return x0;
        }else{
            alertDialog.setMessage("The method fail");
            alertDialog.show();
            return nodio;
        }
    }
    private void imprimir(int contador,double[] x0,double dispercion){
        System.out.print(contador);
        for(int i=0;i<x0.length;++i){
            System.out.print("x:"+x0[i]);
        }
        System.out.print("error: "+dispercion);
        System.out.println();
    }
    private double norma(double[] x0,double[] x1){
        double mayor=0;
        for(int i=0;i<x0.length;++i){
            if(Math.abs(x0[i]-x1[i])>mayor){
                mayor=Math.abs(x0[i]-x1[i]);
            }
        }
        return mayor;
    }
    private double[] calcularNuevoJacobi(double[] x0,double[][] A,double[] b){
        AlertDialog alertDialog = new AlertDialog.Builder(Iterativos.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is a cero in the diagonal of the matrix, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double[] x1 = new double[x0.length];
        for(int i=0; i < x0.length;++i){
            double suma=0;
            for(int j=0;j<x0.length;++j){
                if(j != i){
                    suma += A[i][j]*x0[j];
                }
            }
            if(A[i][i]==0){
                alertDialog.show();
            }
            x1[i]=(b[i]-suma)/A[i][i];
        }
        return x1;
    }
    public double[][] getMatrixA(){
        MatrixA =  findViewById(R.id.MatrixAC);
        n = MatrixA.getChildCount();
        double [][] A = new double [n][n];
        for(int i=0;i<MatrixA.getChildCount();++i){
            TableRow row = (TableRow) MatrixA.getChildAt(i);
            for(int x = 0;x<row.getChildCount();++x){
                EditText f = (EditText) row.getChildAt(x);
                A[i][x] = Double.valueOf(f.getText().toString());
            }
        }

        return A;
    }

    public static double determinante (double [][] matriz){
        assert matriz != null;
        assert matriz.length>0;
        assert matriz.length == matriz[0].length;

        double determinante = 0.0;

        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Si la matriz es 1x1, el determinante es el elemento de la matriz
        if ((filas==1) && (columnas==1))
            return matriz[0][0];


        int signo=1;

        for (int columna=0;columna<columnas;columna++)
        {
            // Obtiene el adjunto de fila=0, columna=columna, pero sin el signo.
            double[][] submatriz = getSubmatriz(matriz, filas, columnas,
                    columna);
            determinante = determinante + signo*matriz[0][columna]*determinante(submatriz);
            signo*=-1;
        }

        return determinante;
    }
    public static double[][] getSubmatriz(double[][] matriz,int filas,int columnas,int columna) {
        double [][] submatriz = new double[filas-1][columnas-1];
        int contador=0;
        for (int j=0;j<columnas;j++)
        {
            if (j==columna) continue;
            for (int i=1;i<filas;i++)
                submatriz[i-1][contador]=matriz[i][j];
            contador++;
        }
        return submatriz;
    }

    public double[] getVectorB(){
        VectorB =  findViewById(R.id.VectorB);
        n = VectorB.getChildCount();
        double [] b = new double [n];
        for(int i=0;i<n;i++) {
            EditText f = (EditText) VectorB.getChildAt(i);
            b[i] = Double.valueOf(f.getText().toString());
        }
        return b;
    }
    public double[] getVectorXo(){
        VectorB =  findViewById(R.id.VectorXo);
        n = VectorB.getChildCount();
        double [] Xo = new double [n];
        for(int i=0;i<n;i++) {
            EditText f = (EditText) VectorB.getChildAt(i);
            Xo[i] = Double.valueOf(f.getText().toString());
        }
        return Xo;
    }
}
