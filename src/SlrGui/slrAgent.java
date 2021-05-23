
package SlrGui;

import jade.core.Agent;
import jade.core.behaviours.*;

public class slrAgent extends Agent {

	private slrGui myGui;

	// Put agent initializations here
	protected void setup() {

		addBehaviour(new slRegression());
		
	}

	// Put agent clean-up operations here
	protected void takeDown() {
		
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("Seller-agent "+getAID().getName()+" terminating.");
	}
//matar el agente 

	private class slRegression extends OneShotBehaviour  {
		public void action() {
			
	        float[] _y= {1.0f,2.0f,3.0f,4.0f,5.0f,6.0f,7.0f,8.0f,9.0f,10.0f};
	        float[] _x= {2.0f,4.0f,6.0f,8.0f,10.0f,12.0f,14.0f,16.0f,18.0f,20.0f};
	        
//metodo action	    
	        
	        float sumatoriaXY = RegresionLineal.SumatoriaProductoXY(_x,_y);
	        float sumatoriaX = RegresionLineal.SumatoriaArray(_x);
	        float sumatoriaY = RegresionLineal.SumatoriaArray(_y);
	        float sumatoriaXcudrada = RegresionLineal.SumatoriaX(_x);
	        
	        float beta1 = RegresionLineal.CalculaBetaUno(sumatoriaXY,
	                        sumatoriaX, sumatoriaY, sumatoriaXcudrada, _x.length);
	        
	        float beta0 = RegresionLineal.CalculaBetaCero(
	                sumatoriaX, sumatoriaY, beta1,  _x.length);
	        
	        
	        System.out.println("\nValor de beta 1: " 
	                + Float.toString(beta1));
	        
	        System.out.println("\nValor de beta 0: " 
	                + Float.toString(beta0));
	        
	        
	        System.out.println("");
	        Helper.print_regression_equation(beta0, beta1, _x);
	        System.out.println("");
	        
	        myGui = new slrGui(new slrAgent(), beta0,beta1);
			myGui.showGui();
			
		}
	}  // End of inner class OfferRequestsServer
}
