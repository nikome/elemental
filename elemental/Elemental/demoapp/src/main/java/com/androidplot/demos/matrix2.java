package com.androidplot.demos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class matrix2 extends Activity {
    private TableLayout MatrixA;
    private TableLayout VectorB;
    private TableLayout VectorX;
    private double[][] Ab;
    private ConstraintLayout principla;
    private double[][] L;
    private double[][] U;
    public int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ab=new double[n][n];
        L = new  double[n][n];
        U= new double[n][n];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix2);
        Button metodobiseccion = (Button) findViewById(R.id.Iterativos);
        metodobiseccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(matrix2.this, Iterativos.class));
            }
        });
            Button resultadoAB = (Button) findViewById(R.id.MatrixAB);
            resultadoAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(matrix2.this, resultado_matrices.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("ResultadoAB", Ab);
                    intent.putExtra("ResultadoAB", Ab);
                    startActivity(intent);
                }
            });


        Button resultadoLU = (Button) findViewById(R.id.matrix_LU);
        resultadoLU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(matrix2.this, resultado_matrices_LU.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("ResultadoL",L);
                intent.putExtra("ResultadoL",L);
                mBundle.putSerializable("U",U);
                intent.putExtra("U",U);
                startActivity(intent);
            }
        });
        calcularMatriz();

    }
    public void calcularMatriz(){

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
        MatrixA=findViewById(R.id.VectorX);
        MatrixA.removeViewAt(n-1);
    }
    public void GaussSimple(View view){
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double [] resx = new double[n];
        A = getMatrixA();
        b=getVectorB();
        resx = sustitucionRegresiva(A,b);
        VectorX = findViewById(R.id.VectorX);
        n = VectorX.getChildCount();
        for(int i=0;i<n;i++) {
            TextView f = (TextView) VectorX.getChildAt(i);
            f.setText(String.valueOf(resx[i]));
        }
        VectorX.setBackgroundColor(Color.rgb(44,132,30));
    }
    public void PivoteoParcial(View view){
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double [] resx = new double[n];
        A = getMatrixA();
        b=getVectorB();
        resx = sustitucionRegresiva2(A,b);
        VectorX = findViewById(R.id.VectorX);
        for(int i=0;i<n;i++) {
            TextView f = (TextView) VectorX.getChildAt(i);
            f.setText(String.valueOf(resx[i]));
        }
        VectorX.setBackgroundColor(Color.rgb(44,132,30));
    }

    public void Crout(View view){
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double [] resx = new double[n];
        A = getMatrixA();
        b=getVectorB();
        resx = crout(A,b);
        VectorX = findViewById(R.id.VectorX);
        for(int i=0;i<n;i++) {
            TextView f = (TextView) VectorX.getChildAt(i);
            f.setText(String.valueOf(resx[i]));
        }
        VectorX.setBackgroundColor(Color.rgb(44,132,30));
    }

    public void Cholesky(View view){
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double [] resx = new double[n];
        A = getMatrixA();
        b=getVectorB();
        resx = cholesky(A,b);
        VectorX = findViewById(R.id.VectorX);
        for(int i=0;i<n;i++) {
            TextView f = (TextView) VectorX.getChildAt(i);
            f.setText(String.valueOf(resx[i]));
        }
        VectorX.setBackgroundColor(Color.rgb(44,132,30));
    }

    public void Doolittle(View view){
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double [] resx = new double[n];
        A = getMatrixA();
        b=getVectorB();
        resx = doolitle(A,b);
        VectorX = findViewById(R.id.VectorX);
        for(int i=0;i<n;i++) {
            TextView f = (TextView) VectorX.getChildAt(i);
            f.setText(String.valueOf(resx[i]));
        }
        VectorX.setBackgroundColor(Color.rgb(44,132,30));
    }

    public void PivoteoTotal(View view){
        double A[][] = new double[n][n];
        double b[] = new double[n];
        double [] resx = new double[n];
        A = getMatrixA();
        b=getVectorB();
        resx = sustitucionRegresiva3(A,b);
        VectorX = findViewById(R.id.VectorX);
        for(int i=0;i<n;i++) {
            TextView f = (TextView) VectorX.getChildAt(i);
            f.setText(String.valueOf(resx[i]));
        }
        VectorX.setBackgroundColor(Color.rgb(44,132,30));
    }

    private double[] crout(double[][] A,double[]b){
        AlertDialog alertDialog = new AlertDialog.Builder(matrix2.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double[] x= new double[n];
        try {


            L = matrizDiagonal(0);
            U = matrizDiagonal(1);
            for (int k = 0; k < n; ++k) {
                double suma = 0;
                for (int p = 0; p < k; ++p) {
                    suma += L[k][p] * U[p][k];
                }
                L[k][k] = A[k][k] - suma;
                for (int i = k + 1; i < n; ++i) {
                    double suma2 = 0;
                    for (int p = 0; p < k; ++p) {
                        suma2 += L[i][p] * U[p][k];
                    }
                    L[i][k] = (A[i][k] - suma2) / U[k][k];
                }
                for (int j = k + 1; j < n; ++j) {
                    double suma3 = 0;
                    for (int p = 0; p < k; ++p) {
                        suma3 += L[k][p] * U[p][j];
                    }
                    U[k][j] = (A[k][j] - suma3) / L[k][k];
                }
            }
            double[] z = sustitucionProgresiva(L, b);
            x = sustitucionRegresiva(U, z);

        }catch (Exception e){
            alertDialog.show();
        }
        return x;
    }

    private double[] doolitle(double[][] A,double[] b){
        AlertDialog alertDialog = new AlertDialog.Builder(matrix2.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double[] x=new double[n];
        try {
            L = matrizDiagonal(1);
            U = matrizDiagonal(0);
            for (int k = 0; k < n; ++k) {
                double suma = 0;
                for (int p = 0; p < k; ++p) {
                    suma += L[k][p] * U[p][k];
                }
                U[k][k] = A[k][k] - suma;
                for (int i = k + 1; i < n; ++i) {
                    double suma2 = 0;
                    for (int p = 0; p < k; ++p) {
                        suma2 += L[i][p] * U[p][k];
                    }
                    L[i][k] = (A[i][k] - suma2) / U[k][k];
                }
                for (int j = k + 1; j < n; ++j) {
                    double suma3 = 0;
                    for (int p = 0; p < k; ++p) {
                        suma3 += L[k][p] * U[p][j];
                    }
                    U[k][j] = (A[k][j] - suma3) / L[k][k];
                }
            }
            double[] z = sustitucionProgresiva(L, b);
            x = sustitucionRegresiva(U, z);
        }catch (Exception e){
            alertDialog.show();
        }
        return x;
    }

    private double[] cholesky(double[][] A,double[]b){
        AlertDialog alertDialog = new AlertDialog.Builder(matrix2.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double[] x = new double[n];
        try {
            L = matrizDiagonal(1);
            U = matrizDiagonal(1);
            for (int k = 0; k < n; ++k) {
                double suma = 0;
                for (int p = 0; p < k; ++p) {
                    suma += L[k][p] * U[p][k];
                }
                L[k][k] = Math.sqrt(A[k][k] - suma);
                U[k][k] = L[k][k];
                for (int i = k + 1; i < n; ++i) {
                    double suma2 = 0;
                    for (int p = 0; p < k; ++p) {
                        suma2 += L[i][p] * U[p][k];
                    }
                    L[i][k] = (A[i][k] - suma2) / U[k][k];
                }
                for (int j = k + 1; j < n; ++j) {
                    double suma3 = 0;
                    for (int p = 0; p < k; ++p) {
                        suma3 += L[k][p] * U[p][j];
                    }
                    U[k][j] = (A[k][j] - suma3) / L[k][k];
                }
            }
            double[] z = sustitucionProgresiva(L, b);
            x = sustitucionRegresiva(U, z);
        }catch (Exception e){
            alertDialog.show();
        }
        return x;
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

    public double [] sustitucionRegresiva(double [][] A, double [] b){
        AlertDialog alertDialog = new AlertDialog.Builder(matrix2.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Ab = escalonar(A,b);
        double [] x = new double [n];
        try {
            for (int j = 0; j < n - 1; ++j) {
                x[j] = 1;
            }
            x[n - 1] = Ab[n - 1][n] / Ab[n - 1][n - 1];
            for (int i = n - 1; i >= 0; --i) {
                double sumatoria = 0;
                for (int p = i + 1; p < n; ++p) {
                    sumatoria += Ab[i][p] * x[p];
                }
                x[i] = (Ab[i][n] - sumatoria) / Ab[i][i];
            }
        }catch (Exception e){
            alertDialog.show();
        }
        return x;
    }

    private double [] sustitucionRegresiva3(double [][] A, double [] b){
        AlertDialog alertDialog = new AlertDialog.Builder(matrix2.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Ab = escalonar4(A,b);
        double [] x = new double [n];
        try {
            for (int j = 0; j < n - 1; ++j) {
                x[j] = 1;
            }
            x[n - 1] = Ab[n - 1][n] / Ab[n - 1][n - 1];
            for (int i = n - 1; i >= 0; --i) {
                double sumatoria = 0;
                for (int p = i + 1; p < n; ++p) {
                    sumatoria += Ab[i][p] * x[p];
                }
                x[i] = (Ab[i][n] - sumatoria) / Ab[i][i];
            }
        }catch (Exception e){
            alertDialog.show();
        }
        return x;
    }

    private double[] sustitucionProgresiva(double[][] L , double[] b){
        double [][] Lb=aumentar(L,b);
        double [] x = new double[n];
        x[0]=Lb[0][n]/Lb[0][0];
        for(int j=1; j<n;++j){
            x[j]=1;
        }
        for(int i=1; i<n ; ++i){
            double sumatoria =0;
            for(int p=0;p<i;++p){
                sumatoria+=Lb[i][p]*x[p];
            }
            x[i]=(Lb[i][n]-sumatoria)/Lb[i][i];
        }
        return x;
    }

    private double[][] matrizDiagonal(int dig){
        double[][] A = new double[n][n];
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                if(i==j){
                    A[i][j]=dig;
                }else{
                    A[i][j]=0;
                }
            }
        }
        return A;
    }

    private double [][]escalonar(double [][] A, double [] b){
        double multi = 0;
        Ab = aumentar(A,b);
        for(int i = 0; i < n-1; ++i){
            if(Ab[i][i] == 0){
                Ab = intercambio(Ab,i);
            }
        }
        for(int k = 0; k < n-1; ++k){
            for(int i = k+1; i < n; ++i){
                multi = Ab[i][k] / Ab[k][k];
                for(int j = k; j < n+1; ++j){
                    Ab[i][j] -= multi * Ab[k][j];
                }
            }
        }
        return Ab;
    }

    private double [][] escalonar4(double [][] A, double [] b){
        int marcas [] = new int [n];
        double var = determinante(A);
        if(var != 0){
            for(int initLista = 0; initLista < n; ++initLista){
                marcas[initLista] = initLista+1;
            }
            double mayor = 0;
            int filaMayor = 0;
            double multi = 0;
            int colMayor = 0;
            Ab = aumentar(A,b);
            for(int bb = 0; bb < n-1; ++bb){
                mayor = 0;
                filaMayor = bb;
                colMayor = bb;
                for(int r = bb; r < n; ++r){
                    for(int s = bb; s < n; ++s){
                        if (Math.abs(Ab[r][s]) > mayor){
                            mayor = Math.abs(Ab[r][s]);
                            filaMayor = r;
                            colMayor = s;
                        }
                    }
                }
                if(mayor == 0){
                    System.out.print("Error");
                    break;
                }else if(filaMayor != bb){
                    Ab = intercambioFilas(Ab,filaMayor,bb);
                }if(colMayor != bb){
                    Ab = intercambioCol(Ab,colMayor,bb,n);
                    marcas = intercambioMarcas(marcas, colMayor, bb);
                }
                for(int i = bb+1; i < n; ++i){
                    multi = Ab[i][bb] / Ab[bb][bb];
                    for(int j = bb; j < n+1; ++j){
                        Ab[i][j] = Ab[i][j] - multi*Ab[bb][j];
                    }
                }
            }
            return Ab;
        }
        return null; //Error no hay respuesta
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

    private double [][] intercambioFilas(double[][] Ab, int i, int j){
        double acum [] = new double [n+1];
        for(int k = 0; k < n+1; ++k){
            acum[k] = Ab[j][k];
            Ab[j][k] = Ab[i][k];
            Ab[i][k] = acum[k];
        }
        return Ab;
    }

    private int [] intercambioMarcas(int [] marcas, int i, int j){
        int temp = marcas[i];
        marcas[i] = marcas[j];
        marcas[j] = temp;
        return marcas;
    }

    private static double [][] intercambioCol(double [][] Ab, int i, int j,int n){
        double acum [] = new double [n];
        for(int k = 0; k < n; ++k){
            acum[k] = Ab[k][j];
            Ab[k][j] = Ab[k][i];
            Ab[k][i] = acum[k];
        }
        return Ab;
    }

    private double [][] intercambio(double[][] Ab, int j){
        for(int i = j+1; i < n; ++i){
            if(Ab[i][j] != 0){
                return cambio(Ab,i,j);
            }
        }
        return Ab;
    }

    private double [][] cambio(double[][] Ab, int i, int j){
        double acum [] = new double [n+1];
        for(int k = 0; k < n+1; ++k){
            acum[k] = Ab[j][k];
            Ab[j][k] = Ab[i][k];
            Ab[i][k] = acum[k];
        }
        return Ab;
    }
    private double [][] aumentar(double [][] A, double [] b){
        double [][] Ab = new double [n][n+1];
        for(int i = 0; i < n; ++i){
            Ab[i][n] = b[i];
            for(int j = 0; j < n; ++j){
                Ab[i][j] = A[i][j];
            }
        }
        return Ab;
    }
    private double [] sustitucionRegresiva2(double [][] A, double [] b){
        AlertDialog alertDialog = new AlertDialog.Builder(matrix2.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        double [] x = new double [n];
        try {
            Ab = escalonar2(A,b);
            for (int j = 0; j < n - 1; ++j) {
                x[j] = 1;
            }
            x[n - 1] = Ab[n - 1][n] / Ab[n - 1][n - 1];
            for (int i = n - 1; i >= 0; --i) {
                double sumatoria = 0;
                for (int p = i + 1; p < n; ++p) {
                    sumatoria += Ab[i][p] * x[p];
                }
                x[i] = (Ab[i][n] - sumatoria) / Ab[i][i];
            }
        }catch (Exception e){
            alertDialog.show();
        }

        return x;
    }
    private double [][] escalonar2(double [][] A, double [] b){
        double [][] Ab = new double [n][n];
        double multi = 0;
        double mayor=0;
        int filaMayor=0;
        Ab = aumentar(A,b);
        AlertDialog alertDialog = new AlertDialog.Builder(matrix2.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("There is an error in the written variables, Try again please");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        for(int i = 0; i < n-1; ++i){
            mayor=Math.abs(Ab[i][i]);
            filaMayor=i;
            for(int j=i+1;j<n;++j){
                if((Math.abs(Ab[j][i]))>mayor){
                    mayor=Math.abs(Ab[j][i]);
                    filaMayor=j;
                }
            }
            if(mayor==0){
                alertDialog.show();
            }
            else if(filaMayor!=i){
                Ab=cambio(Ab,filaMayor,i);
            }
            for(int k = 0; k < n-1; ++k){
                for(int l = k+1; l < n; ++l){
                    multi = Ab[l][k] / Ab[k][k];
                    for(int m = k; m < n+1; ++m){
                        Ab[l][m] -= multi * Ab[k][m];
                    }
                }
            }
        }
        return Ab;
    }
}
