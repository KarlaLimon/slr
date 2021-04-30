package Slr;

public class Helper {
    
    public static void fill_array(float arr[]){
    
        for (int i = 0; i < arr.length; i++) {
            System.out.println("[" + arr[i] + "]");
        }
    }
    
    public static void print_regression_equation(float b0, float b1, float arr[]){

        for (int i = 0; i < arr.length; i++) {
            System.out.println("y = " + Float.toString((b0 + (b1*arr[i]))) + " ");
        }
    }
    
    
    
    public static void clean_screen(){
        
        try{
            final String os = System.getProperty("os.name"); 
            if(os.contains("Windows")){
                //Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                Runtime.getRuntime().exec("clear"); 
            }
        }catch (final Exception e){
            e.printStackTrace();  
        }  
    }
}

