package com.androidplot.demos;
/**
 *
 * @author oonikoo
 */
public class GaussTotalApoyo {

    /**
     * @param args the command line arguments
     */
    public static int n = 1;
    public int marcas [];
    public GaussTotalApoyo(int n){
        this.n = n;
        marcas = new int [n];
    }
    

    
    public double [] sustitucionRegresiva(double [][] A, double [] b){
        double [][] Ab;
        double [] x = new double [n];
        Ab = escalonar(A,b);
        for(int j = 0; j < n-1; ++j){
            x[j] = 1;
        }
        x[n-1] = Ab[n-1][n] / Ab[n-1][n-1];
        for(int i = n-1; i >= 0; --i){
            double sumatoria = 0;
            for(int p = i+1; p < n; ++p){
                sumatoria += Ab[i][p] * x[p];
            }
            x[i] = (Ab[i][n] - sumatoria) / Ab[i][i];
        }
        return x;
    }
    
    private double [][] escalonar(double [][] A, double [] b){
        double var = determinante(A);
        if(var != 0){
            double [][] Ab;
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
                    Ab = intercambioCol(Ab,colMayor,bb);
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

    private double [][] intercambioFilas(double[][] Ab, int i, int j){
        double acum [] = new double [n+1];
        for(int k = 0; k < n+1; ++k){
            acum[k] = Ab[j][k];
            Ab[j][k] = Ab[i][k];
            Ab[i][k] = acum[k];
        }
        return Ab;
    }

    private static double [][] intercambioCol(double [][] Ab, int i, int j){
        double acum [] = new double [n];
        for(int k = 0; k < n; ++k){
            acum[k] = Ab[k][j];
            Ab[k][j] = Ab[k][i];
            Ab[k][i] = acum[k];
        }
        return Ab;
    }

    private int [] intercambioMarcas(int [] marcas, int i, int j){
        int temp = marcas[i];
        marcas[i] = marcas[j];
        marcas[j] = temp;
        return marcas;
    }
    
    public static double determinante (double [][] matriz){
        assert matriz != null;
        assert matriz.length>0;
        assert matriz.length == matriz[0].length;

        double determinante = 0.0;

        int filas = matriz.length;
        int columnas = matriz[0].length;

        if ((filas==1) && (columnas==1))
                return matriz[0][0];


        int signo=1;

        for (int columna=0;columna<columnas;columna++)
        {
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

}
