
package SlrGui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Slr.RegresionLineal;

class slrGui extends JFrame {	
	private slrAgent myAgent;
	
	private JTextField xField;
	float beta0,beta1;
	slrGui(slrAgent a, float beta0,float beta1) {
		super(a.getLocalName());
        this.beta0 = beta0;
        this.beta1 = beta1;
        
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Agrega el valor a calcular:"));
		xField = new JTextField(15);
		p.add(xField);

		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Calcular");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					
					String xVal = xField.getText().trim();
					float x = Float.parseFloat(xVal);
					RegresionLineal.CalcularX( beta0,  beta1, x);
					xField.setText("");
//					priceField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(slrGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		

		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
