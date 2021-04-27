/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
 *****************************************************************/

package bookTrading;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slr.Helper;
import slr.RegresionLineal;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.*;
import java.io.IOException;

public class slrAgent extends Agent {

	private slrGui myGui;

	// Put agent initializations here
	protected void setup() {

		// Register the book-selling service in the yellow pages
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("slr");
		sd.setName("slr oneshot");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}


		// Add the behaviour serving purchase orders from buyer agents
		addBehaviour(new slRegression());
		
	}

	// Put agent clean-up operations here
	protected void takeDown() {
		// Deregister from the yellow pages
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("Seller-agent "+getAID().getName()+" terminating.");
	}


	private class slRegression extends OneShotBehaviour  {
		public void action() {
			
	        float[] _y= {2.0f,3.0f,4.0f,4.0f,4.0f,6.0f,5.0f,7.0f};
	        float[] _x= {1.0f,2.0f,2.0f,3.0f,4.0f,4.0f,5.0f,6.0f};
	        
	    
	        
	        float sumatoriaXY = RegresionLineal.SumatoriaProductoXY(_x,_y);
	        float sumatoriaX = RegresionLineal.SumatoriaArray(_x);
	        float sumatoriaY = RegresionLineal.SumatoriaArray(_y);
	        float sumatoriaXcudrada = RegresionLineal.SumatoriaX(_x);
	        
	        float beta1 = RegresionLineal.CalculaBetaCero(sumatoriaXY,
	                        sumatoriaX, sumatoriaY, sumatoriaXcudrada, _x.length);
	        
	        float beta0 = RegresionLineal.CalculaBetaUno(
	                sumatoriaX, sumatoriaY, beta1, _x.length);
	        
	        
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
