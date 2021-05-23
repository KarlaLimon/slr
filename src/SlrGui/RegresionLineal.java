
package SlrGui;

public class RegresionLineal {
    
    // Este metodo retorna el producto de la variable dependiexte x independiente
    public static float SumatoriaProductoXY(float x[], float y[]){
        
        float arr[] = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            arr[i] = x[i] * y[i];
        }
                
        return SumatoriaArray(arr);
    }
    
    // Esta metodo retorna la sumatoria de la variable independiente al cuadrado
    public static float SumatoriaX (float x[]){
        
        float arr[] = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            arr[i] = (float) Math.pow(x[i], 2);
        }
        
        return SumatoriaArray(arr);
    }
    
    // Retorna la sumatoria de un arreglo
    public static float SumatoriaArray(float array[]){
        
        float _value = 0.0f;
        for (int i = 0; i < array.length; i++) {
            _value = _value + array[i];
        }
        
        return _value;
    }
    //no son funciones propias calculo de coeficientes. 
    //otra clase de utildades instanciar, recursividad,
    //pagar en espacio

    // Calcula beta0
    public static float CalculaBetaUno(float Exy, float Ex, float Ey, float Ex2, int total){
        
        float m = 0.0f, part1 = 0.0f, part2 = 0.0f;
        part1 = (total*(Exy) - (Ex*Ey));
        part2 = (total*Ex2)- (float) (Ex*Ex);
        
        return m = part1 / part2;
    }
     //doubles
    // Calcula beta1
    public static float CalculaBetaCero(float x, float y, float beta1, int total){
        
        float b = 0.0f;
        b = (y/total) - (beta1*(x/total));
        
        return b;
    }
    
    public static void CalcularX(float b0, float b1, float value){
        System.out.println("y = " + Float.toString((b0 + (b1*value))) + " ");
    }
}
