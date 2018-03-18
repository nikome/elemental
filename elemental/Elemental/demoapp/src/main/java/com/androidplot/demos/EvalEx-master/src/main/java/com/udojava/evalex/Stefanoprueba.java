import com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.lang.Math;

public class Steffensen{
  public static void main(String[] args){
      Steffensenmethod("log(x+2)","-1.5","10^-6",100);
    }
    public static String Steffensenmethod(String f, String Xo, String tolerancia, String niter){
        Double xi = Double.parseDouble(Xo);
        Expression gx = new Expression(f);
        gx.setVariable("x",Xo);
        Expression fx = new Expression(f+"-x");
        fx.setVariable("x",Xo);
        Double yi = gx.eval().doubleValue();
        fx.setVariable("x",Double.toString(yi));
        Double zi = fx.eval().doubleValue();
        if(fx.eval().doubleValue() == 0){
            return Xo + " Es la raiz";
        }else{
                Expression t = new Expression(tolerancia);
                Double tol = t.eval().doubleValue();
                Double error = tol + 1;
                Double xin = next(xi,yi,zi);
                int contador = 0;
                while(error > tol && contador < Integer.parseInt(niter) &&  fx.eval().doubleValue()!=0){
                    fx.setVariable("x",Double.toString(xin));
                    gx.setVariable("x",Double.toString(xin));
                    yi = gx.eval().doubleValue();
                    gx.setVariable("x",Double.toString(yi));
                    zi = gx.eval().doubleValue();
                    Double aux = xin;
                    xin = next(aux,yi,zi);
                    error = Math.abs(xin-aux);
                    contador++;
                }
                if(fx.eval().doubleValue() == 0){
                    System.out.println(Double.toString(xin) + " Es la raiz "+ Integer.toString(contador)+ " iteraciones");
                    return Double.toString(xin) + " Es la raiz";
                }else if( error < tol){
                    System.out.println(Double.toString(xin) + " Es una aproximacion de la raiz con error: "+ Double.toString(error)+ " en:"+ Integer.toString(contador)+ " iteraciones");
                    return Double.toString(xin) + " Es una aproximacion de la raiz con error: "+ Double.toString(error)+ " en:"+ Integer.toString(contador)+ " iteraciones";
                }else{
                    return "El programa fallo en: " + niter + " iteraciones";
                }
        }
    }
    public static Double next(Double xi, Double yi, Double zi){
        return zi - (((zi-yi)*(zi-yi))/(zi-(2*yi)+xi));
    }
}
